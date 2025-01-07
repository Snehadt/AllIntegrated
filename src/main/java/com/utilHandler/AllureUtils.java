package com.utilHandler;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureUtils {

    /**
     * Logs a step in Allure report.
     * @param stepDescription Description of the test step.
     */
    @Step("{0}")
    public static void logStep(String stepDescription) {
        // No implementation needed - @Step annotation takes care of logging
    }

    /**
     * Adds a screenshot attachment to Allure report.
     * @param driver WebDriver instance for taking screenshots.
     * @param screenshotName Name of the screenshot.
     */
    @Attachment(value = "{screenshotName}", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver, String screenshotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Adds a text attachment to Allure report.
     * Useful for attaching request or response data.
     * @param attachmentName Name of the text attachment.
     * @param message Content of the text attachment.
     */
    @Attachment(value = "{attachmentName}", type = "text/plain")
    public static String attachText(String attachmentName, String message) {
        return message;
    }

    /**
     * Adds custom label to categorize tests in the Allure report.
     * Example: adding severity level.
     * @param labelName Name of the label (e.g., "severity").
     * @param labelValue Value of the label (e.g., "critical").
     */
    public static void addLabel(String labelName, String labelValue) {
        Allure.label(labelName, labelValue);
    }

    /**
     * Adds a tag to the test, useful for grouping tests by tags.
     * @param tag The tag name to add.
     */
    public static void addTag(String tag) {
        Allure.label("tag",tag);
    }

    /**
     * Attaches a file as a byte stream, useful for attaching binary data.
     * @param fileName Name of the file to be displayed in the report.
     * @param fileContent Content of the file as a byte array.
     */
    @Attachment(value = "{fileName}", type = "application/octet-stream")
    public static byte[] attachFile(String fileName, byte[] fileContent) {
        return fileContent;
    }
}
