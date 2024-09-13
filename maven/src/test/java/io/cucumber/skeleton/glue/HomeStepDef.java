package io.cucumber.skeleton.glue;

import org.junit.jupiter.api.Assertions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.core.Context;
import io.cucumber.skeleton.core.Manager;
import io.cucumber.skeleton.pages.HomePage;
import java.util.List;


public class HomeStepDef extends Context {


  public HomeStepDef(Manager manager) {
    super(manager);
  }

  /**
   * Use this step definition to validate user is on the Home Page by validating the title of the page.
   */
  @When("the user navigates to example Home Page step")
  public void exampleHomePageStep() {
    HomePage homePage = new HomePage(manager.getDriver());
    Assertions.assertEquals(homePage.getTitle().getText(),"Welcome to the-internet");
  }

  /**
   * Use this step definition to validate the Expected List of Examples on the Actual Web Page.
   * @param dataTable is the list of Expected Examples.
   */
  @Then("the user verifies the displayed list as below")
  public void theUserVerifiesTheDisplayedListOfAvailableExamples(DataTable dataTable) {
    HomePage homePage = new HomePage(manager.getDriver());
    List<String> expectedList = dataTable.asList();
    Assertions.assertTrue(homePage.exampleList().containsAll(expectedList),"The list is not matching");
  }

  /**
   * Use this step definition to click on the exampleLink.
   * @param exampleLink is the name of the Example User wants to click on.
   */
  @When("the {string} example is opened")
  public void theBasicAuthExampleIsOpened(String exampleLink) {
    new HomePage(manager.getDriver()).openLink(exampleLink);
  }
}