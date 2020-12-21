package pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class UserDetailsPage {
    private WebDriver driver;

    private By btnPopulate = By.xpath("//input[@id='populate']");
    private By edtEmailAddress = By.id("developer-name");
    private String dynamicFrequency = "// input[@type='radio' and @id='%s']";
    private String decisiveFactors = "//input[@type='checkbox' and @id='%s']";
    private By selectPayment = By.id("preferred-payment");
    private By btnAcceptCookies = By.xpath("//a[@class='cbtn btn_accept_ck']");
    private By selectEcomPurchase = By.id("tried-ecom");
    private By edtComments = By.xpath("//textarea[@id='comments']");
    private By edtUploadFile = By.xpath("//input[@id='file']");
    private By submitButton = By.xpath("//button[@id='submit-button']");
    private By successMessageText = By
            .xpath("//div[@id='message']/p[contains(text(),'You have successfully submitted the form.')]");
    private By freq;

    public UserDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopulatePresent() {
        return driver.findElement(btnPopulate).isDisplayed();
    }

    public void setEmailAddress(String email) {
        driver.findElement(edtEmailAddress).sendKeys(email);
        System.out.println("Entered Email Address: "+email);
    }

    public void clickPopulate() {
        driver.findElement(btnPopulate).click();
        System.out.println("Clicked on Populate button.");
    }

    public void clickOkInPopup() {
        driver.switchTo().alert().accept();
        System.out.println("Clicked OK in popup.");
    }

    public void selectPurchaseFrequency(String purchaseFrequency) {
        String frequencyID = purchaseFrequency.replace(" ", "").toLowerCase();
        System.out.println(frequencyID);
        freq = By.xpath(String.format(dynamicFrequency, frequencyID));
        driver.findElement(freq).click();
        System.out.println(purchaseFrequency + " is selected.");
    }

    public void selectDecisiveFactors(String Dfactors) {
        String fact = Dfactors.replace(" ", "-").toLowerCase();
        WebElement facto = driver.findElement(By.xpath(String.format(decisiveFactors, fact)));
        jsClickById(facto);
        if (facto.isSelected()) {
            System.out.println(Dfactors + " is selected.");
        }
    }

    public void selectPaymentMethod(String payment) {
        Select drpPayment = new Select(driver.findElement(selectPayment));
        drpPayment.selectByVisibleText(payment);
        System.out.println("Selected "+payment+" from the dropdown.");
    }

    public void selectEPurchase() {
        WebElement ePurchase = driver.findElement(selectEcomPurchase);
        jsClickById(ePurchase);
        System.out.println("Selected e-purchase checkbox.");
    }

    public void clickOnSlider() {
        driver.findElement(By.xpath("//div[@id='slider']/span[@style='left: 0%;']")).click();
        System.out.println("Clicked on slider.");
    }

    public void selectSliderValue(String d) {
        String i = "";
        while (!(i.equals(d))) {
            driver.findElement(By.xpath("//div[@id='slider']/span")).sendKeys(Keys.ARROW_RIGHT);
            i = driver.findElement(By.xpath("//div[@id='slider']/span")).getAttribute("style");
            // System.out.println(i);
        }
        System.out.println("Selected value : "+d + " from the slider.");
    }

    public String sliderVal() {
        return driver.findElement(By.xpath("//div[@id='slider']/span")).getAttribute("style");
    }

    public void setFeedback(String feedback) {
        driver.findElement(edtComments).sendKeys(feedback);
        driver.findElement(edtComments).click();
        System.out.println("Entered Feedback : "+feedback);
    }

    public void openNewUrlAndDownloadImage(String url) throws AWTException, IOException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Switch focus to new tab
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
        for (String tab : tabs1) {
            if (driver.getTitle().contains("Playground")) {
                driver.switchTo().window(tab);
            }
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        for (String tab : tabs2) {
            if (!driver.getTitle().contains("Playground")) {
                driver.switchTo().window(tab);
                break;
            }
        }
        /* Logo PNG download not working. Downloading seprately and uploading
        WebElement logo = driver.findElement(By.xpath("//a/img[@alt='LambdaTest Jenkins integration']"));
        String logoSRC = logo.getAttribute("src");
        
        URL imageURL;
        try {
            imageURL = new URL(logoSRC);
            BufferedImage saveImage = ImageIO.read(imageURL);
            ImageIO.write(saveImage, "png", new File("logo-image.png"));
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } */

    }

    public void uploadFile() {
        String path = System.getProperty("user.dir") + "\\resources\\jenkins.svg";
        WebElement upFile = driver.findElement(edtUploadFile);
        upFile.sendKeys(path);
        // driver.findElement(edtUploadFile).sendKeys(path);
        String text = driver.switchTo().alert().getText();
        System.out.println(text);
        driver.switchTo().alert().accept();
        System.out.println("File uploaded successfully.");
        // driver.findElement(uploadButton).click();
    }

    public void clickSubmit() {
        WebElement submitBtn = driver.findElement(submitButton);
        jsClickById(submitBtn);
        System.out.println("Clicked on submit button.");
    }
    
    public String submissionMessage() {
        return driver.findElement(successMessageText).getText();
    }

    public String getUploadedFiles() {
        String alertText = driver.switchTo().alert().getText();
        System.out.println(alertText);
        return alertText;

    }

    public String currentDirectoryPath() {
        return System.getProperty("user.dir");
    }

    public void acceptCookies() {
        driver.findElement(btnAcceptCookies).click();
    }

    public void jsClickById(WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", ele);
    }
}

