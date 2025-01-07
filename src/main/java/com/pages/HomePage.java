package com.pages;

import com.base.WebBasePage;
import com.enums.BrowserType;
import com.enums.PlatformType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;

public class HomePage extends WebBasePage
{

    private PlatformType platform;
    private BrowserType browser;

    private final By musicLink = By.xpath("//span[contains(text(), 'Music')]");

    public HomePage(PlatformType platform, BrowserType browser) throws MalformedURLException {
        super(platform, browser);
        this.platform = platform; // Initialize instance variables
        this.browser = browser;
    }

    public String isMusicDisplayed(){
        return driver.findElement(musicLink).getText();
    }
    public MusicPage goToMusicPage() throws MalformedURLException {
        driver.findElement(musicLink).click();
        return new MusicPage(platform, browser);  // Navigate to ProductPage
    }
}
