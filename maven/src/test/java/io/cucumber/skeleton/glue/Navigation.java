package io.cucumber.skeleton.glue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.skeleton.core.Context;
import io.cucumber.skeleton.core.Manager;
import io.cucumber.skeleton.pages.NavPage;
import org.junit.jupiter.api.Assertions;

public class Navigation extends Context {

  public Navigation(Manager manager) {
    super(manager);
  }

  @Given("^the page under test is '(.+)'$")
  public void navToPage(String url) {
    manager.getDriver().get(url);
    stash("exampleKey1", "exampleValue1");
  }

  @And("valid credentials are supplied")
  public void validCredentialsAreSupplied() {
    new NavPage(manager.getDriver()).enterCred();
  }

  @Then("Congratulations should be displayed")
  public void congratulationsShouldBeDisplayed() {
    NavPage navPage= new NavPage(manager.getDriver());
    Assertions.assertEquals("Congratulations! You must have the proper credentials.",navPage.verifyPage(),"The message displayed is not correct.");
  }
}