package io.cucumber.skeleton.pages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class BasicAuthPage extends Page {

  public BasicAuthPage(ChromeDriver driver) {
    super(driver);
  }

  @FindBy(css = "#content > div > p")
  private WebElement pageText;

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
}
