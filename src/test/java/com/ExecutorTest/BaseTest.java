package com.ExecutorTest;

import com.config.ConfigManager;
import com.drivers.DriverFactory;
import com.enums.BrowserType;
import com.enums.PlatformType;
import com.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

public class BaseTest {
    protected LoginPage loginPage;

    @BeforeClass
    @Parameters({"platform", "browser"})
    public void setup(String platform, String browser) throws MalformedURLException {
        // Determine platform and browser types from parameters or configuration
        PlatformType platformType = platform != null
                ? PlatformType.valueOf(platform.toUpperCase())
                : PlatformType.valueOf(ConfigManager.getProperty("default.platform").toUpperCase());
        BrowserType browserType = browser != null
                ? BrowserType.valueOf(browser.toUpperCase())
                : BrowserType.valueOf(ConfigManager.getProperty("default.browser").toUpperCase());

        // Initialize the driver
        DriverFactory.getInstance().initializeDriver(platformType, browserType);

        // Initialize the LoginPage object
        loginPage = new LoginPage(platformType, browserType);

        // Navigate to base URL for web platform
        if (platformType == PlatformType.WEB) {
            loginPage.navigateToBaseUrl();
        }
    }

    @AfterClass
    public void tearDown() {
        // Quit the driver after tests are completed
        DriverFactory.getInstance().quitDriver();
    }
}
