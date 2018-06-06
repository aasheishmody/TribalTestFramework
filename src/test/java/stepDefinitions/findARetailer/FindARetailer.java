package stepDefinitions.findARetailer;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import pageObjects.FindARetailerPage;
import pageObjects.Page;

import static util.LoggerHelper.*;

public class FindARetailer extends Page {

    private final FindARetailerPage findARetailerPage;

    public FindARetailer() {

        findARetailerPage = PageFactory.initElements(getDriver(), FindARetailerPage.class);
    }

    @Then("^the services filter is not displayed$")
    public void theServicesFilterIsNotDisplayed() throws Throwable {
        asserting("that the services filter is not displayed", () -> Assert.assertFalse(findARetailerPage.isServicesFilterDisplayed()));
    }

    @When("^I click on the 'Refine search by service' link toggle$")
    public void iClickOnTheRefineSearchByServiceLink() throws Throwable {
        clicking("‘Refine search by service’ link toggle on the 'Find a retailer' page", findARetailerPage::clickRefineSearchByServiceLinkToggle);
    }

    @Then("^the services filter is displayed$")
    public void theServicesFilterIsDisplayed() throws Throwable {
        asserting("that the services filter is displayed on the 'Find a retailer' page", () -> Assert.assertTrue(findARetailerPage.isServicesFilterDisplayed()));
    }

    @And("^I display the services filter$")
    public void iDisplayTheServicesFilter() throws Throwable {
        if (!findARetailerPage.isServicesFilterDisplayed())
            clicking("‘Refine search by service’ link toggle on the 'Find a retailer' page to display the services filter", findARetailerPage::clickRefineSearchByServiceLinkToggle);
        asserting("that the services filter is displayed on the 'Find a retailer' page", () -> Assert.assertTrue(findARetailerPage.isServicesFilterDisplayed()));
    }

    @Then("^the services are not editable$")
    public void theServicesAreNotEditable() throws Throwable {
        asserting("that the services are not editable on the ‘Find a retailer’ page", () -> Assert.assertFalse(findARetailerPage.areServicesEditable()));
    }

    @Then("^the services are selected$")
    public void servicesAreSelected(DataTable table) throws Throwable {
        int totalNumberofRows = table.cells(0).size();
        int totalNumberOfColumns = table.cells(0).get(0).size();
        for (int i = 0; i < totalNumberofRows; i++)
            for (int j = 0; j < totalNumberOfColumns; j++) {
                int finalI = i;
                int finalJ = j;
                asserting("the service - " + table.cells(i).get(j) + " is selected in the service filter", () -> Assert.assertTrue(findARetailerPage.areChosenServicesSelected(table.cells(finalI).get(finalJ))));
            }
    }

    @And("^I do not select any service in the services filter$")
    public void iDoNotSelectAnyServiceInTheServicesFilter() throws Throwable {
        if (!findARetailerPage.isServicesFilterDisplayed())
            iDisplayTheServicesFilter();
        asserting("that no service is selected in the services filter", () -> Assert.assertFalse(findARetailerPage.isAnyServiceSelected()));
    }

    @Then("^the search result is displayed$")
    public void theSearchResultIsDisplayed() throws Throwable {
        asserting("that the search result is displayed", () -> Assert.assertTrue(findARetailerPage.isSearchResultDisplayed()));
    }

    @And("^I do not enter a location in the 'Search by location' textbox$")
    public void iDoNotEnterALocationInTheSearchByLocationTextbox() throws Throwable {
        asserting("that the 'Search by location' textbox is empty", () -> Assert.assertTrue(findARetailerPage.isSearchByLocationTextBoxEmpty()));
    }

    @When("^I select the 'New Cars' service filter$")
    public void iSelectTheNewCarsService() throws Throwable {
        if (!findARetailerPage.isServicesFilterDisplayed())
            iDisplayTheServicesFilter();
        selecting("the 'New Cars' service filter", findARetailerPage::selectNewCarsServiceFilter);
        asserting("that the 'New Cars' service filter is selected", () -> Assert.assertTrue(findARetailerPage.isNewCarServiceFilterSelected()));
    }

    @Then("^the search result is not displayed$")
    public void theSearchResultIsNotDisplayed() throws Throwable {
        asserting("that the search result is not displayed", () -> Assert.assertFalse(findARetailerPage.isSearchResultDisplayed()));
    }

    @When("^I enter a postcode in the 'Search by location' textbox$")
    public void iEnterAPostcodeInTheSearchByLocationTextbox(DataTable table) throws Throwable {
        int totalNumberofRows = table.cells(0).size();
        int totalNumberOfColumns = table.cells(0).get(0).size();
        for (int i = 0; i < totalNumberofRows; i++)
            for (int j = 0; j < totalNumberOfColumns; j++) {
                int finalI = i;
                int finalJ = j;
                entering("the postcode - " + table.cells(i).get(j) + " in the 'Search by location' textbox", () -> findARetailerPage.enterPostCodeInSearchByLocationTextBox(table.cells(finalI).get(finalJ)));
                asserting("that the 'Search by location' textbox contains the postcode - " + table.cells(finalI).get(finalJ), () -> Assert.assertTrue(findARetailerPage.searchByLocationTextboxHasEnteredPostcode(table.cells(finalI).get(finalJ))));
            }
    }

    @And("^I click the 'Search by location' button$")
    public void iClickTheSearchByLocationButton() throws Throwable {
        clicking("the 'Search by location' button", findARetailerPage::clickSearchByLocationButton);
    }

    @And("^I select multiple services in the services filter$")
    public void iSelectMultipleServicesInTheServicesFilter() throws Throwable {
        if (!findARetailerPage.isServicesFilterDisplayed())
            iDisplayTheServicesFilter();
        selecting("multiple services in the services filter", findARetailerPage::selectMultipleServicesInTheServicesFilter);
        asserting("that multiple services in the services filter is selected", () -> Assert.assertTrue(findARetailerPage.areMultipleServiceFiltersSelected()));
    }

    @Then("^the retailers displayed in the search result offer the selected services$")
    public void theRetailersDisplayedInTheSearchResultOfferTheSelectedServices(DataTable table) throws Throwable {
        int totalNumberofRows = table.cells(0).size();
        int totalNumberOfColumns = table.cells(0).get(0).size();
        for (int i = 0; i < totalNumberofRows; i++)
            for (int j = 0; j < totalNumberOfColumns; j++) {
                int finalI = i;
                int finalJ = j;
                asserting("that each of the retailers displayed in the search result offer the selected services - " + table.cells(i).get(j), () -> Assert.assertTrue(findARetailerPage.searchResultRetailersOfferSelectedServices(table.cells(finalI).get(finalJ))));
            }
    }

    @And("^I select one service in the services filter$")
    public void iSelectOneServiceInTheServicesFilter() throws Throwable {
        if (!findARetailerPage.isServicesFilterDisplayed())
            iDisplayTheServicesFilter();
        selecting("one service in the services filter", findARetailerPage::selectOneServiceInTheServicesFilter);
        asserting("that one service in the services filter is selected", () -> Assert.assertTrue(findARetailerPage.isOneServiceFilterSelected()));
    }

    @Then("^the retailers displayed in the search result offer the selected service$")
    public void theRetailersDisplayedInTheSearchResultOfferTheSelectedService() throws Throwable {
        asserting("the retailers displayed in the search result offer the selected service", () -> Assert.assertTrue(findARetailerPage.doRetailersInSearchResultOfferNewCarsService()));
    }

    @Given("^I am on the 'Find a retailer' page with preselected services$")
    public void iAmOnTheFindARetailerPageWithPreselectedServices(DataTable table) throws Throwable {
        int totalNumberofRows = table.cells(0).size();
        int totalNumberOfColumns = table.cells(0).get(0).size();
        for (int i = 0; i < totalNumberofRows; i++)
            for (int j = 0; j < totalNumberOfColumns; j++) {
                int finalI = i;
                int finalJ = j;
                navigating("to the ‘Find a retailer’ page with preselected services - " + table.cells(i).get(j), () -> findARetailerPage.openWithPreselectedServices(table.cells(finalI).get(finalJ)));
            }
    }

    @Then("^the 'Search by location' textbox is prepopulated with the location$")
    public void theSearchByLocationTextboxIsPrepopulatedWithTheLocation(DataTable table) throws Throwable {
        int totalNumberofRows = table.cells(0).size();
        int totalNumberOfColumns = table.cells(0).get(0).size();
        for (int i = 0; i < totalNumberofRows; i++)
            for (int j = 0; j < totalNumberOfColumns; j++) {
                int finalI = i;
                int finalJ = j;
                asserting("that the 'Search by location' textbox contains the postcode - " + table.cells(finalI).get(finalJ), () -> Assert.assertTrue(findARetailerPage.searchByLocationTextboxHasEnteredPostcode(table.cells(finalI).get(finalJ))));
            }
    }

    @And("^the services are preselected in the services filter$")
    public void theServicesArePreselectedInTheServicesFilter(DataTable table) throws Throwable {
        servicesAreSelected(table);
    }

    @When("^I click the services in the services filter$")
    public void iClickTheServicesInTheServicesFilter(DataTable table) throws Throwable {
        int totalNumberofRows = table.cells(0).size();
        int totalNumberOfColumns = table.cells(0).get(0).size();
        for (int i = 0; i < totalNumberofRows; i++)
            for (int j = 0; j < totalNumberOfColumns; j++) {
                int finalI = i;
                int finalJ = j;
                selecting("the service - " + table.cells(i).get(j) + " in the service filter", () -> findARetailerPage.selectServiceInServiceFilter(table.cells(finalI).get(finalJ)));
            }
    }

    @And("^I select the services in the services filter$")
    public void iSelectTheServicesInTheServiceFilter(DataTable table) throws Throwable {
        if (!findARetailerPage.isServicesFilterDisplayed())
            iDisplayTheServicesFilter();
        iClickTheServicesInTheServicesFilter(table);
        servicesAreSelected(table);
    }
}