package com.pages;


import com.base.WebBasePage;
import com.enums.BrowserType;
import com.enums.PlatformType;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class MusicPage extends WebBasePage {

    private PlatformType platform;
    private BrowserType browser;

    private final By popularArtist = By.xpath("//a[contains(text(), 'Popular artists')]");

    public MusicPage(PlatformType platform, BrowserType browser) throws MalformedURLException {
        super(platform, browser);
        this.platform = platform;
        this.browser = browser;
    }

    public MusicPage verifyText() {
        driver.findElement(popularArtist).isDisplayed();
        return this;
    }

    public HomePage returnToHomePage() throws MalformedURLException {
        driver.navigate().back();
        return new HomePage(platform, browser);  // Navigate back to HomePage
    }
}
