package io.cucumber.skeleton.pages;

import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class NavPage extends Page {

  public NavPage(ChromeDriver driver) {
    super(driver);
  }

  @FindBy(css = "#content > div > p")
  private WebElement pageText;

  @FindBy(css = "#table1")
  private WebElement exampleTable;

  public void enterCred() {
    String username = "admin";
    String password = "admin";
    String baseUrl = "the-internet.herokuapp.com/basic_auth";
    try {
      String urlWithAuth = "https://" + username + ":" + password + "@" + baseUrl;
      driver.get(urlWithAuth);
      waitForPageLoad();
    } catch (NoAlertPresentException e) {
      System.out.println("No alert is present:" + e.getMessage());
    }
  }

  public String verifyPage() {
    return pageText.getText();
  }

  public List<List<String>> verifyTable(DataTable dataTable) {
    List<List<String>> expectedData = dataTable.asLists(String.class);
    // Get all rows from the table
    List<WebElement> rows = exampleTable.findElements(By.tagName("tr"));
    //Get the headers from the table
    WebElement headerRow = exampleTable.findElement(By.xpath(".//thead/tr"));
    List<WebElement> headerColumns = headerRow.findElements(By.tagName("th"));
    List<String> headerValues = new ArrayList<>();
    // Check headers
    List<String> expectedHeaders = expectedData.getFirst();
    List<List<String>> tableData = new ArrayList<>();
    for (int i = 0; i < expectedHeaders.size(); i++) {
      String expectedHeader = expectedHeaders.get(i);
      String actualHeader = headerColumns.get(i).getText();
      if (expectedHeader.equals(actualHeader)) {
        headerValues.add(actualHeader); // Add matching header to the list
      }
    }
    tableData.add(headerValues);
    for (int i = 1; i < rows.size(); i++) {
      WebElement row = rows.get(i);
      List<WebElement> cells = row.findElements(By.tagName("td"));
      List<String> expectedRow = expectedData.get(i);
      List<String> rowData = new ArrayList<>();
      for (int j = 0; j < expectedRow.size(); j++) {
        rowData.add(cells.get(j).getText());
      }
      tableData.add(rowData);
    }
    return tableData;
  }
}
