package com.pages;


import com.base.WebBasePage;
import com.enums.BrowserType;
import com.enums.PlatformType;
import com.utilHandler.WaitUtils;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class LoginPage extends WebBasePage {

    private final By usernameField = By.id("login-username");
    private final By passwordField = By.id("login-password");
    private final By loginButton = By.xpath(".//*[text()='Log In']");
    private final By webPlayer = By.xpath(".//*[text()='Web Player']");

    private PlatformType platform;
    private BrowserType browser;

    public LoginPage(PlatformType platform, BrowserType browser) throws MalformedURLException {
        super(platform, browser);
        this.platform = platform;
        this.browser = browser;
    }

    public boolean isLoginPageDisplayed() {
       return !getUrl().isEmpty();
    }

    public LoginPage enterUsername(String username){
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public HomePage clickLogin() throws MalformedURLException {
        driver.findElement(loginButton).click();
        WaitUtils.waitForElementToBeClickable(driver,webPlayer);
        driver.findElement(webPlayer).click();

         return new HomePage(platform, browser);
    }

    public HomePage loginAs(String username, String password) throws MalformedURLException {
        return enterUsername(username).enterPassword(password).clickLogin();
    }
}
