package com.drivers;

import com.config.ConfigManager;
import com.enums.BrowserType;
import com.enums.PlatformType;
import com.utilHandler.MobileCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class DriverFactory {
    private volatile static DriverFactory instance = null;
    private ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();
    private ThreadLocal<AppiumDriver> threadLocalMobileDriver = new ThreadLocal<>();
    private AppiumDriver mobileDriver;

    // Private constructor to prevent instantiation
    private DriverFactory() {}

    // Singleton instance of DriverFactory
    public static DriverFactory getInstance() {
        if (instance == null) {
            synchronized (DriverFactory.class) {
                if (instance == null) {
                    instance = new DriverFactory();
                }
            }
        }
        return instance;
    }

    // Method to initialize the driver based on platform and browser type
    public Object initializeDriver(PlatformType platform, BrowserType browser) throws MalformedURLException {
        switch (platform) {
            case WEB:
                return createWebDriver(browser);
            case MOBILE_ANDROID:
                return createMobileDriver(PlatformType.MOBILE_ANDROID);
            case MOBILE_IOS:
                return createMobileDriver(PlatformType.MOBILE_IOS);
            case API:
                return createAPIDriver();
            default:
                throw new IllegalStateException("Invalid platform type");
        }
    }

    // WebDriver initialization
    private WebDriver createWebDriver(BrowserType browser) {
        if (threadLocalWebDriver.get() == null) {
            WebDriver driver;
            switch (browser) {
                case CHROME:
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
                case EDGE:
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalStateException("Unsupported browser type: " + browser);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                    Integer.parseInt(ConfigManager.getProperty("web.timeout.implicit", "10"))
            ));
            threadLocalWebDriver.set(driver);
        }
        return threadLocalWebDriver.get();
    }
    public WebDriver getWebDriver() {
        return threadLocalWebDriver.get();
    }

    // MobileDriver initialization
    public AppiumDriver createMobileDriver(PlatformType platform) throws MalformedURLException {
        if (threadLocalMobileDriver.get() == null) {
            URL appiumServerUrl = new URL(ConfigManager.getProperty("appium.server.url", "http://localhost:4723/wd/hub"));
            AppiumDriver driver;
            switch (platform) {
                case MOBILE_ANDROID:
                    driver = new AndroidDriver(appiumServerUrl, MobileCapabilities.androidCapabilities());
                    break;
                case MOBILE_IOS:
                    driver = new IOSDriver(appiumServerUrl, MobileCapabilities.iOSCapabilities());
                    break;
                default:
                    throw new IllegalStateException("Unsupported mobile platform: " + platform);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                    Integer.parseInt(ConfigManager.getProperty("mobile.timeout.implicit", "10"))
            ));
            threadLocalMobileDriver.set(driver);
        }
        return threadLocalMobileDriver.get();
    }

    // Get current MobileDriver
    public AppiumDriver getMobileDriver() {
        return threadLocalMobileDriver.get();
    }
    public RequestSpecification createAPIDriver() {
        String baseUrl = ConfigManager.getProperty("api.base.url");
        RestAssured.baseURI = baseUrl;
        return RestAssured.given(); // Returns a RestAssured RequestSpecification
    }
    // Quit driver
    public void quitDriver() {
        if (threadLocalWebDriver.get() != null) {
            threadLocalWebDriver.get().quit();
            threadLocalWebDriver.remove();
        }
        if (threadLocalMobileDriver.get() != null) {
            threadLocalMobileDriver.get().quit();
            threadLocalMobileDriver.remove();
        }
    }
}
