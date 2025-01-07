package com.utilHandler;

import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileCapabilities {


        // Method to get Android capabilities
        public static DesiredCapabilities androidCapabilities() {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "Android Emulator"); // Update this with your device's name if needed
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("appPackage", "your.app.package"); // Replace with your app's package
            capabilities.setCapability("appActivity", "your.app.activity"); // Replace with your app's main activity
            capabilities.setCapability("noReset", true); // This prevents the app from resetting state between sessions
            return capabilities;
        }

        // Method to get iOS capabilities
        public static DesiredCapabilities iOSCapabilities() {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone Simulator"); // Update this with your device's name if needed
            capabilities.setCapability("automationName", "XCUITest");
            capabilities.setCapability("bundleId", "your.app.bundleid"); // Replace with your app's bundle ID
            capabilities.setCapability("noReset", true); // Prevents app reset between sessions
            return capabilities;
        }
}
