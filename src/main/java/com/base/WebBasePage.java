package com.base;

import com.config.ConfigManager;
import com.enums.BrowserType;
import com.enums.PlatformType;
import com.utilHandler.WaitUtils;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.logging.Logger;

public  class WebBasePage extends BasePage{
    private static final Logger logger = Logger.getLogger(BasePage.class.getName());

    public WebBasePage(PlatformType platform, BrowserType browser) throws MalformedURLException {
        super(platform, browser);
    }

    @Override
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public void performAction() {
        // Implement web-specific actions, e.g., navigating to a URL
        System.out.println("Performing web-specific action...");
    }
    public WebDriver navigateToBaseUrl() {
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

}
