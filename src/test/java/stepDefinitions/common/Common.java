package stepDefinitions.common;

import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import pageObjects.Page;

import static util.LoggerHelper.asserting;
import static util.LoggerHelper.navigating;

public class Common extends Page {

    private Page page;

    public Common() {

        page = PageFactory.initElements(getDriver(), Page.class);
    }

    @Given("^I am on the (.*) page$")
    public void iAmOnThePage(String pageName) throws Throwable {
        navigating("to the " +pageName+ " page", () -> page.open(pageName));
        asserting("that I am on the " +pageName+ " page", () -> Assert.assertTrue(page.isPageDisplayed(pageName)));
    }
}