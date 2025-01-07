package com.utilHandler;

import com.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtils {

    public static String getApiProperty(String key) {
        return ConfigManager.getProperty(key);
    }

    public static String getBaseUri() {
        return "https://api.shopify.com/v1";  // Replace with actual base URI if different
    }

    // Provides a common request specification for API calls
    public static RequestSpecification getRequestSpecification() {
        return RestAssured
                .given()
                .baseUri(getBaseUri())
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + getAuthToken());  // Token-based auth example
    }

    public static Response get(String endpoint) {
        return RestAssured.given()
                .auth().preemptive().basic( getApiProperty("api_key"),getApiProperty("api_password"))
                .get(getApiProperty(("base_uri") + endpoint));
    }

    public static Response post(String endpoint, Object body) {
        return RestAssured.given()
                .auth().preemptive().basic(getApiProperty("api_key"),getApiProperty("api_password"))
                .contentType("application/json")
                .body(body)
                .post(getApiProperty(("base_uri") + endpoint));
    }

    public static Response put(String endpoint, Object body) {
        return RestAssured.given()
                .auth().preemptive().basic(getApiProperty("api_key"),getApiProperty("api_password"))
                .contentType("application/json")
                .body(body)
                .put(getApiProperty(("base_uri") + endpoint));
    }

    public static Response delete(String endpoint) {
        return RestAssured.given()
                .auth().preemptive().basic(getApiProperty("api_key"),getApiProperty("api_password"))
                .delete(getApiProperty(("base_uri") + endpoint));
    }

    private static String getAuthToken() {
        // Example token retrieval logic; you might replace this with an actual login call
        return "your-static-or-dynamic-token";  // Replace with logic to retrieve dynamic token if required
    }

    // Method to perform a login and return the response
    public static Response login(String email, String password) {
        return getRequestSpecification()
                .body("{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }")
                .post("/auth/login");  // Replace with actual login endpoint
    }
}
