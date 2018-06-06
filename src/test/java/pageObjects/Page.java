package pageObjects;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebConnector;

public class Page extends WebConnector {

    final String findARetailerPageURL = "/find-a-retailer";

    void click(WebElement element, int timeout) {
        getLogger().info("Waiting for " + element + " to be displayed");
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        getLogger().info(element + " found after waiting for it to be displayed");
        getLogger().info("Waiting for element to be clickable - " + element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        getLogger().info("Clicking " + element);
        element.click();
        getLogger().info("Clicked " + element);
    }

    private WebDriverWait buidWebDriverWait(int timeout) {
        long sleepInMillis = 250L;
        return new WebDriverWait(getDriver(), timeout, sleepInMillis);
    }

    private void waitForElementToBeDisplayed(WebElement element, int timeout) {
        getLogger().info("Waiting for " + element + " to be displayed");
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        getLogger().info(element + " found after waiting for it to be displayed");
    }

    void sendKeys(WebElement element, String text, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Sending text - " + text + " to element - " + element);
        element.sendKeys(text);
        getLogger().info("Sent text - " + text + " to element - " + element);
    }

    private void sendKeysUsingActions(WebElement element, String text, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Sending text - " + text + " to element - " + element + "using actions");
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(text);
        actions.build().perform();
        getLogger().info("Sent text - " + text + " to element - " + element + "using actions");
    }

    String getText(WebElement element, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Getting text from element - " + element);
        getLogger().info("Got text - " + element.getText() + " from element - " + element);
        return element.getText();
    }

    Boolean isElementDisplayed(WebElement element) {
        Boolean displayed = true;
        try {
            if (element.isDisplayed())
                displayed = true;
        } catch (Throwable t) {
            displayed = false;
        }
        return displayed;
    }

    String getAttribute(WebElement element, String attribute, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Getting attribute - " + attribute + " from element - " + element);
        getLogger().info("Attribute - " + attribute + " of the element - " + element + " is" + element.getAttribute(attribute));
        return element.getAttribute(attribute);
    }

    public void open(String pageName) {
        getLogger().info("OPENING PAGE");
        switch (pageName) {
            case "Find a retailer":
                getLogger().info("Opening " + pageName + " page");
                getDriver().get(getBaseURL() + findARetailerPageURL);
                getLogger().info("Opened " + pageName + " page");
                break;
            case "Home":
                getLogger().info("Opening " + pageName + " page");
                getDriver().get(getBaseURL());
                getLogger().info("Opened " + pageName + " page");
                break;
        }
        getLogger().info("PAGE OPENED");
    }

    public boolean isPageDisplayed(String pageName) {
        return getDriver().getTitle().contains(pageName);
    }

    private String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    void changeAttributeValueToRandomlyGeneratedString(WebElement element, int timeout){
        waitForElementToBeDisplayed(element, timeout);
        String newValueToBeInserted = generateRandomString();
        sendKeysUsingActions(element, newValueToBeInserted, getShortTimeout());
    }
}