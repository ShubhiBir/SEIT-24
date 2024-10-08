package io.cucumber.skeleton.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

  public HomePage(ChromeDriver driver) {
    super(driver);
    System.out.println("Homepage title is : " + getTitle().getText());
  }

  @FindBy(css = "h1")
  private WebElement title;

  @FindBy(xpath = ".//div[@id='content']//li")
  private List<WebElement> availableExamples;

  public static String exampleLink = ".//div[@id='content']//a[contains(text(),'%s')]";

  public WebElement getTitle() {
    return title;
  }

  public void refresh() {
    driver.navigate().refresh();
    System.out.println("Refreshed page");
  }

  public List<String> exampleList() {
    return availableExamples.stream()
        .map(tab -> tab.getAttribute("innerText")).toList();
  }

  public void openLink(String link) {
    driver.findElement(By.xpath(String.format(exampleLink, link))).click();
    waitForPageLoad();
  }

}
