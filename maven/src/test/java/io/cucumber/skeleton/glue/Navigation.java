package io.cucumber.skeleton.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.skeleton.core.Context;
import io.cucumber.skeleton.core.Manager;
import io.cucumber.skeleton.pages.BasicAuthPage;
import io.cucumber.skeleton.pages.SortableDataTablePage;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class Navigation extends Context {

  public Navigation(Manager manager) {
    super(manager);
  }

  /**
   * Use this step definition to navigate to the provided url.
   * @param url is the WebURL user wish to go to.
   */
  @Given("^the page under test is '(.+)'$")
  public void navToPage(String url) {
    manager.getDriver().get(url);
  }

  /**
   * Use this step definition to provide valid Login Credentials.
   */
  @And("valid credentials are supplied")
  public void validCredentialsAreSupplied() {
    new BasicAuthPage(manager.getDriver()).enterCred();
  }

  /**
   * Use this step definition to validate the Congratulations message upon login.
   */
  @Then("Congratulations should be displayed")
  public void congratulationsShouldBeDisplayed() {
    BasicAuthPage basicAuthPage= new BasicAuthPage(manager.getDriver());
    Assertions.assertEquals("Congratulations! You must have the proper credentials.",basicAuthPage.verifyPage(),"The message displayed is not correct.");
  }

  /**
   * Use this step definition to validate Example 1 table has listed rows and columns.
   * @param dataTable is the Example table to be verified.
   */
  @Then("the table should be displayed as below")
  public void theBelowTableShouldBeDisplayedAsBelow(DataTable dataTable) {
    SortableDataTablePage sortableDataTablePage = new SortableDataTablePage(manager.getDriver());
    List<List<String>> dataList = dataTable.asLists(String.class);
    List<List<String>> listExpectedValues = new ArrayList<>(dataList);
    Assertions.assertEquals(listExpectedValues, sortableDataTablePage.verifyTable(dataTable),"Not all Expected Values are available: ");
  }
}