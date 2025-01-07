/*
package com.pages;

import com.config.ConfigManager;
import com.drivers.DriverFactory;
import com.enums.PlatformType;
import com.utilHandler.WaitUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.net.MalformedURLException;
import java.util.logging.Logger;

public abstract class BasePage {
    private static final Logger logger = Logger.getLogger(BasePage.class.getName());
    protected  WebDriver driver;
    protected AppiumDriver mobileDriver;
    protected final PlatformType platformType;
    DriverFactory driverFactory = DriverFactory.getInstance();
    public BasePage(PlatformType platformType) throws MalformedURLException {
        this.platformType = platformType;
        this.driver = driverFactory.getWebDriver(); // Retrieve the WebDriver initialized in Test class
        if (this.driver == null) {
            throw new IllegalStateException("Driver is not initialized. Make sure the driver is initialized in the Test class.");
        }
        PageFactory.initElements(driver, this); // Initialize page elements (PageFactory)
    }
    public WebDriver navigateToBaseUrl() {
        if (platformType != PlatformType.WEB) {
            throw new UnsupportedOperationException("navigateToBaseUrl is only supported for WEB platform.");
        }
        String url = ConfigManager.getProperty("web.base.url");
        logger.info("Navigating to URL: " + url);
        driver.manage().window().maximize();
        logger.info("Waiting for the page to load");
        WaitUtils.waitForPageLoad(driver);
        driver.get(url);
        return driver;
    }

    public String getUrl() {
        if (driver == null) {
            throw new UnsupportedOperationException("getUrl is only supported for WEB platform.");
        }

        String currentUrl = driver.getCurrentUrl();
        logger.info("Current URL: " + currentUrl);
        return currentUrl;
    }

    public void sendApiRequest(String endpoint, String method, String payload) {
        if (platformType != PlatformType.API) {
            throw new UnsupportedOperationException("sendApiRequest is only supported for API platform.");
        }
        // Add API request logic here (e.g., using RestAssured or similar library)

    }
}
*/
