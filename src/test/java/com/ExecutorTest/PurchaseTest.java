package com.ExecutorTest;

import com.enums.PlatformType;
import com.pages.HomePage;
import com.pages.MusicPage;
import com.utilHandler.Decoders;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

@Epic("Login Feature")
@Feature("Valid Login")
public class PurchaseTest extends BaseTest {
    private HomePage homePage;
    private MusicPage musicPage;

    @Test(priority = 1)
    @Story("Login with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void testNavigateToHomePage() throws IOException {
        PlatformType platformType = PlatformType.valueOf(
                com.config.ConfigManager.getProperty("platform").toUpperCase()
        );

        if (platformType == PlatformType.WEB) {
            homePage = loginPage
                    .loginAs(Decoders.propertiesDecode("web.username"), Decoders.propertiesDecode("web.password"));
        } else if (platformType == PlatformType.API) {
            System.out.println("API test logic for adding product to cart.");
        } else if (platformType == PlatformType.MOBILE) {
            System.out.println("Mobile test logic for adding product to cart.");
        }

        // Validate HomePage initialization
        Assert.assertNotNull(homePage, "HomePage should be initialized after login.");
    }

    @Test(priority = 2)
    @Story("Verify if Homepage is visible")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyHomePage() throws MalformedURLException {
        // Navigate to MusicPage and back to HomePage
        musicPage = homePage.goToMusicPage();
        homePage = musicPage.returnToHomePage();

        // Validate that the Music section is displayed
        Assert.assertEquals("Music", homePage.isMusicDisplayed());
    }
}
