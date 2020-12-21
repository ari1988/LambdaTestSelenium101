package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//div[@class='submitform']/button");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void setUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public UserDetailsPage clickLoginButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement loginBtn = driver.findElement(loginButton);
        js.executeScript("arguments[0].click();", loginBtn); 
        return new UserDetailsPage(driver);
    }
}
