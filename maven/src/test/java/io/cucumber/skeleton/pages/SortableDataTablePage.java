package io.cucumber.skeleton.pages;

import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class SortableDataTablePage extends Page {

  public SortableDataTablePage(ChromeDriver driver) {
    super(driver);
  }

  @FindBy(css = "#table1")
  private WebElement exampleTable;

  // Get all rows from the table
  public List<WebElement> getRows(){
    return exampleTable.findElements(By.tagName("tr"));
  }
  //Get the headers from the table
  public List<WebElement> getHeaders(){
    WebElement headerRow = exampleTable.findElement(By.xpath(".//thead/tr"));
    return headerRow.findElements(By.tagName("th"));
  }

  public List<String> checkHeaders(List<List<String>> expectedTable){
    List<String> headerValues = new ArrayList<>();
    // Check headers
    List<String> expectedHeaders = expectedTable.getFirst();
    for (int i = 0; i < expectedHeaders.size(); i++) {
      String expectedHeader = expectedHeaders.get(i);
      String actualHeader = getHeaders().get(i).getText();
      if (expectedHeader.equals(actualHeader)) {
        headerValues.add(actualHeader); // Add matching header to the list
      }
    }
    return headerValues;
  }

  public List<List<String>> verifyTable(DataTable dataTable) {
    List<List<String>> expectedData = dataTable.asLists(String.class);
    List<List<String>> tableData = new ArrayList<>();
    tableData.add(checkHeaders(expectedData));
    for (int i = 1; i < getRows().size(); i++) {
      WebElement row = getRows().get(i);
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
