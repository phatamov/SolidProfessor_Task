package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LoginStepDefinitions {

    String url = ConfigReader.getProperty("url");

    @Given("The user is on the homepage")
    public void theUserIsOnTheHomepage() {
        Driver.getDriver().get(url);
    }

    @When("The user enters the valid credentials")
    public void theUserEntersTheValidCredentials() {
        new LoginPage().login(ConfigReader.getProperty("username1"), ConfigReader.getProperty("password"));
    }

    @Then("The user should be able to login and land on the homepage")
    public void theUserShouldBeAbleToLoginAndLandOnTheHomepage() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("https://qa-challenge.codesubmit.io/inventory.html"));
    }

    @When("The user enters the invalid credentials as {string} for username and {string} for password")
    public void theUserEntersTheInvalidCredentialsAsForUsernameAndForPassword(String username, String password) {
        new LoginPage().login(username, password);
    }

    @Then("The user should not be able to login and land on the homepage")
    public void theUserShouldNotBeAbleToLoginAndLandOnTheHomepage() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals(url));
    }


    @When("The user enters provided locked_out_user credentials")
    public void theUserEntersProvidedLockedOutUserCredentials() {
        new LoginPage().login(ConfigReader.getProperty("username2"), ConfigReader.getProperty("password"));
    }

    @Then("The user should not be able to login and get error message")
    public void theUserShouldNotBeAbleToLoginAndGetErrorMessage() {
        String lockedOutMessage = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertTrue(Driver.getDriver().getPageSource().contains(lockedOutMessage));
    }

    @When("The user enters provided performance_glitch_user credentials")
    public void theUserEntersProvidedPerformanceGlitchUserCredentials() {
        long start = System.currentTimeMillis();
        new LoginPage().login(ConfigReader.getProperty("username4"), ConfigReader.getProperty("password"));
        long finish = System.currentTimeMillis();
        long totalTime = finish-start;
        System.out.println("Total time for login page load - " + totalTime/1000 + " seconds.");
    }
    @Then("The user should be able to login")
    public void theUserShouldBeAbleToLogin() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("https://qa-challenge.codesubmit.io/inventory.html"));
    }

    @When("The user enters provided problem_user credentials")
    public void theUserEntersProvidedProblemUserCredentials() {
        new LoginPage().login(ConfigReader.getProperty("username3"), ConfigReader.getProperty("password"));
    }

    @Then("Images are not loading for this user")
    public void imagesAreNotLoadingForThisUser() {
        List<String> expectedLinks = Arrays.asList(
                "https://qa-challenge.codesubmit.io/static/media/sauce-backpack-1200x1500.34e7aa42.jpg",
                "https://qa-challenge.codesubmit.io/static/media/bike-light-1200x1500.a0c9caae.jpg",
                "https://qa-challenge.codesubmit.io/static/media/bolt-shirt-1200x1500.c0dae290.jpg",
                "https://qa-challenge.codesubmit.io/static/media/sauce-pullover-1200x1500.439fc934.jpg",
                "https://qa-challenge.codesubmit.io/static/media/red-onesie-1200x1500.1b15e1fa.jpg",
                "https://qa-challenge.codesubmit.io/static/media/red-tatt-1200x1500.e32b4ef9.jpg");

        List<WebElement> listOfWebElements = Driver.getDriver().findElements(By.xpath("//img[@class='inventory_item_img']"));

        List<String> actualLinks = new LinkedList<>();
        for (WebElement each : listOfWebElements) {
           actualLinks.add(each.getAttribute("src"));
        }
        Assert.assertNotEquals(expectedLinks, actualLinks);
    }


}
