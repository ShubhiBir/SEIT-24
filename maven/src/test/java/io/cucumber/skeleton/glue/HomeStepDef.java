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

  @When("the user navigates to example Home Page step")
  public void exampleHomePageStep() {
    System.out.println("printing shared stash :" + getTestStash().toString());
  }

  @Then("the user verifies the displayed list as below")
  public void theUserVerifiesTheDisplayedListOfAvailableExamples(DataTable dataTable) {
    HomePage homePage = new HomePage(manager.getDriver());
    List<String> expectedList = dataTable.asList();
    Assertions.assertTrue(expectedList.stream().allMatch(homePage.exampleList()::contains),"The list is not matching");
  }
}