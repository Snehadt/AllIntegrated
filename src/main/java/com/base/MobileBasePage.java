
package com.base;

import com.enums.BrowserType;
import com.enums.PlatformType;

import java.net.MalformedURLException;

public class MobileBasePage extends BasePage{

    public MobileBasePage(PlatformType platform, BrowserType browser) throws MalformedURLException {
        super(platform, browser);
    }

    @Override
    public void quitDriver() {
        if (mobileDriver != null) {
            mobileDriver.quit();
        }
    }

    @Override
    public void performAction() {
        // Implement mobile-specific actions
        System.out.println("Performing mobile-specific action...");
    }
}
