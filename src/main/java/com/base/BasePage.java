package com.base;

import com.drivers.DriverFactory;
import com.enums.BrowserType;
import com.enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.logging.Logger;

public abstract class BasePage {
    protected WebDriver driver;
    protected AppiumDriver mobileDriver;
    private static final Logger logger = Logger.getLogger(BasePage.class.getName());

    public BasePage(PlatformType platform, BrowserType browser) throws MalformedURLException {
        initializeDriver(platform, browser);  // Initialize driver as soon as the page object is created
    }
    // Abstract method for quitting the driver
    public abstract void quitDriver();

    // Abstract method for performing page actions
    public abstract void performAction();

    // Log common message (this can be extended to proper logging if needed)
    public void log(String message) {
        System.out.println("LOG: " + message);
    }

    // Initialize the driver based on the platform
    public void initializeDriver(PlatformType platform, BrowserType browser) throws MalformedURLException {
        DriverFactory driverFactory = DriverFactory.getInstance();

        switch (platform) {
            case WEB:
                this.driver = driverFactory.getWebDriver();
                break;
            case MOBILE_ANDROID:
            case MOBILE_IOS:
                this.mobileDriver = driverFactory.getMobileDriver();
            break;
            case API:
                driverFactory.createAPIDriver(); // API doesn't need a WebDriver or AppiumDriver
                break;
            default:
            throw new IllegalArgumentException("Invalid platform: " + platform);
        }
    }
}
