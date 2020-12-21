package UserScenario;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserDetailsPage;
import static org.testng.Assert.*;
import java.awt.AWTException;
import java.io.IOException;

public class UserDetailsTest extends BaseTests {
    @Test
    public void fillUserDetails() throws AWTException, IOException {
        LoginPage loginPage = homePage.checkLoginPage();
        loginPage.setUsername("lambda");
        loginPage.setPassword("lambda123");
        UserDetailsPage userDetailsPage = loginPage.clickLoginButton();
        assertEquals(userDetailsPage.isPopulatePresent(), true, "Login not successful.");

        //Fill Email details
        userDetailsPage.acceptCookies();
        userDetailsPage.setEmailAddress("arvindsingh.cs@hotmail.com");
        userDetailsPage.clickPopulate();
        userDetailsPage.clickOkInPopup();
        userDetailsPage.selectPurchaseFrequency("3 months");
        userDetailsPage.selectDecisiveFactors("Customer Service");
        userDetailsPage.selectDecisiveFactors("Discounts");
        userDetailsPage.selectPaymentMethod("Cash on delivery");
        userDetailsPage.selectEPurchase();
        userDetailsPage.clickOnSlider();
        userDetailsPage.selectSliderValue("left: 88.8889%;");
        assertEquals(userDetailsPage.sliderVal(), "left: 88.8889%;", "Slider Value");
        userDetailsPage.setFeedback("Awesome Feedback");
        // userDetailsPage.openNewUrlAndDownloadImage("https://www.lambdatest.com/selenium-automation");
        userDetailsPage.uploadFile();
        userDetailsPage.clickSubmit();
        assertEquals(userDetailsPage.submissionMessage(), "You have successfully submitted the form.", "Successful submission");
    }

    

}
