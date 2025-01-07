package com.base;

import com.enums.BrowserType;
import com.enums.PlatformType;
import com.utilHandler.APIUtils;
import io.restassured.RestAssured;

import java.net.MalformedURLException;

public class ApiBasePage extends BasePage{

    public ApiBasePage(PlatformType platform, BrowserType browser) throws MalformedURLException {
        super(platform, browser);
    }

    public void sendApiRequest(String endpoint, String method, String payload) {
        // Example of how API calls could be made
        // Use request to send the API request
    }

    @Override
    public void quitDriver() {
        // API doesn't need driver quit logic
    }

    @Override
    public void performAction() {
// Perform API-specific actions
        System.out.println("Performing API-specific action...");
    }
}
