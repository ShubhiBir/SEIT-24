package io.cucumber.skeleton.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.skeleton.core.Context;
import io.cucumber.skeleton.core.Manager;
import io.cucumber.skeleton.pages.NavPage;
import java.util.ArrayList;
import java.util.List;
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

  @Then("the table should be displayed as below")
  public void theBelowTableShouldBeDisplayedAsBelow(DataTable dataTable) {
    NavPage navPage= new NavPage(manager.getDriver());
    List<List<String>> dataList = dataTable.asLists(String.class);
    List<List<String>> listExpectedValues = new ArrayList<>(dataList);
    System.out.println("From Step Def : "+listExpectedValues);
    Assertions.assertEquals(listExpectedValues, navPage.verifyTable(dataTable),"Not all Expected Values are available: ");
  }
}