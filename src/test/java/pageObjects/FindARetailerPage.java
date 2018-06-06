package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.Iterator;
import java.util.List;

public class FindARetailerPage extends Page {

    @FindBy(css = ".icon.icon--vw-expand.style-scope.find-retailer-service-filters")
    private WebElement RefineSearchByServiceLinkToggle;
    @FindBy(css = ".find-retailer-service-filters__list.find-retailer-service-filters__list--show.style-scope.find-retailer-service-filters")
    private WebElement ServicesFilter;
    @FindBy(xpath = "//li[contains(@class,'icon--vw-car-face')]")
    private WebElement NewCarsServiceFilter;
    @FindBy(xpath = "//li[contains(@class,'icon--vw-car-health')]")
    private WebElement UsedCarsServiceFilter;
    @FindBy(xpath = "//li[contains(@class,'icon--vw-space')]")
    private WebElement AccessoriesAndMerchandiseServiceFilter;
    @FindBy(xpath = "//li[contains(@class,'icon--vw-fleet')]")
    private WebElement Fleet50CarsServiceFilter;
    @FindBy(xpath = "//li[contains(@class,'icon--vw-spare-parts')]")
    private WebElement AccessToBodyShopServiceFilter;
    @FindBy(xpath = "//li[contains(@class,'icon--vw-work')]")
    private WebElement PartsServiceFilter;
    @FindBy(xpath = "//li[contains(@class,'icon--vw-service')]")
    private WebElement ServicingServiceFilter;
    @FindBy(xpath = "//li[contains(@class,'icon--vw-local-business')]")
    private WebElement LocalBusinessServiceFilter;
    @FindBy(xpath = "//li[contains(@class,'icon--vw-mot')]")
    private WebElement MOTServiceFilter;
    @FindBy(xpath = "//li[contains(@class,'icon--vw-motability')]")
    private WebElement MotabilityServiceFilter;
    @FindBy(css = ".input-field__input.style-scope.find-retailer-location-search")
    private WebElement SearchByLocationTextBox;
    @FindBy(css = ".input-field__button.icon.icon--vw-search.style-scope.find-retailer-location-search")
    private WebElement SearchByLocationButton;
    @FindBy(xpath = "//span[text()='New cars' and contains(@class, 'services__list-item-label')]")
    private WebElement NewCarsRetailerService;
    @FindBy(xpath = "//span[text()='Used cars' and contains(@class, 'services__list-item-label')]")
    private WebElement UsedCarsRetailerService;
    @FindBy(xpath = "//span[text()='Motability' and contains(@class, 'services__list-item-label')]")
    private WebElement MotabilityRetailerService;
    @FindBy(xpath = "//span[text()='MOT' and contains(@class, 'services__list-item-label')]")
    private WebElement MOTRetailerService;
    @FindBy(xpath = "//span[text()='Local business' and contains(@class, 'services__list-item-label')]")
    private WebElement LocalBusinessRetailerService;
    @FindBy(xpath = "//span[text()='Servicing' and contains(@class, 'services__list-item-label')]")
    private WebElement ServicingRetailerService;
    @FindBy(xpath = "//span[text()='Parts' and contains(@class, 'services__list-item-label')]")
    private WebElement PartsRetailerService;
    @FindBy(xpath = "//span[text()='Access to bodyshop' and contains(@class, 'services__list-item-label')]")
    private WebElement AccessToBodyShopRetailerService;
    @FindBy(xpath = "//span[text()='Fleet (50+ cars)' and contains(@class, 'services__list-item-label')]")
    private WebElement Fleet50CarsRetailerService;
    @FindBy(xpath = "//span[text()='Accessories & Merchandise' and contains(@class, 'services__list-item-label')]")
    private WebElement AccessoriesAndMerchandiseRetailerService;
    @FindBys({@FindBy(xpath = "//li[contains(@class,'icon')]")})
    private List<WebElement> Services;
    @FindBys({@FindBy(xpath = "//find-retailer-summary-card[contains(@id, 'findRetailerSummaryCard')]")})
    private List<WebElement> SearchResults;

    public boolean isServicesFilterDisplayed() {
        return isElementDisplayed(ServicesFilter);
    }

    public void clickRefineSearchByServiceLinkToggle() {
        click(RefineSearchByServiceLinkToggle, getShortTimeout());
    }

    //isEditable() from RC has been replaced by isEnabled() in Webdriver and has been traditionally used to check if the element is editable but the moethod is not applicable in this case as it returns true
    public boolean areServicesEditable() {
        String actualText = getText(NewCarsServiceFilter, getShortTimeout());
        String actualValue = getAttribute(NewCarsServiceFilter, "value", getShortTimeout());
        changeAttributeValueToRandomlyGeneratedString(NewCarsServiceFilter, getShortTimeout());
        String newText = getText(NewCarsServiceFilter, getShortTimeout());
        String newValue = getAttribute(NewCarsServiceFilter, "value", getShortTimeout());
        return !actualText.equals(newText) && (actualValue == null ? newValue != null : !actualValue.equals(newValue));
    }

    public boolean isAnyServiceSelected() {
        Iterator<WebElement> iterator = Services.iterator();
        boolean result = false;
        while (iterator.hasNext()) {
            WebElement webElement = iterator.next();
            result = getAttribute(webElement, "class", getShortTimeout()).contains("checked");
        }
        return result;
    }

    public void clickSearchByLocationButton() {
        click(SearchByLocationButton, getShortTimeout());
    }

    public void enterPostCodeInSearchByLocationTextBox(List<String> postcode) {
        for (String aPostcode : postcode)
            sendKeys(SearchByLocationTextBox, aPostcode, getShortTimeout());
    }

    public boolean isSearchResultDisplayed() {
        return SearchResults.size() > 0;
    }

    public boolean isSearchByLocationTextBoxEmpty() {
        return getAttribute(SearchByLocationTextBox, "value", getMediumTimeout()).isEmpty();
    }

    public void selectNewCarsServiceFilter() {
        click(NewCarsServiceFilter, getShortTimeout());
    }

    public boolean isNewCarServiceFilterSelected() {
        return getAttribute(NewCarsServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    public void selectMultipleServicesInTheServicesFilter() {
        selectNewCarsServiceFilter();
        selectUsedCarsServiceFilter();
    }

    private void selectUsedCarsServiceFilter() {
        click(UsedCarsServiceFilter, getShortTimeout());
    }

    public boolean areMultipleServiceFiltersSelected() {
        return getAttribute(NewCarsServiceFilter, "class", getShortTimeout()).contains("checked") &&
                getAttribute(UsedCarsServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    public boolean searchResultRetailersOfferSelectedServices(List<String> selectedServices) {
        boolean result = false;
        for (String aSelectedService : selectedServices) {
            switch (aSelectedService) {
                case "New cars":
                    result = doRetailersInSearchResultOfferNewCarsService();
                    break;
                case "Used cars":
                    result = doRetailersInSearchResultOfferUsedCarsService();
                    break;
                case "Motability":
                    result = doRetailersInSearchResultOfferMotabilityService();
                    break;
                case "MOT":
                    result = doRetailersInSearchResultOfferMOTService();
                    break;
                case "Local business":
                    result = doRetailersInSearchResultOfferLocalBusinessService();
                    break;
                case "Servicing":
                    result = doRetailersInSearchResultOfferServicingService();
                    break;
                case "Parts":
                    result = doRetailersInSearchResultOfferPartsService();
                    break;
                case "Access to bodyshop":
                    result = doRetailersInSearchResultOfferAccessToBodyShopService();
                    break;
                case "Fleet (50+ cars)":
                    result = doRetailersInSearchResultOfferFleet50CarsService();
                    break;
                case "Accessories & Merchandise":
                    result = doRetailersInSearchResultOfferAccessoriesAndMerchandiseService();
                    break;
            }

        }
        return result;
    }

    private boolean doRetailersInSearchResultOfferAccessoriesAndMerchandiseService() {
        boolean result = false;
        for (WebElement SearchResult : SearchResults) {
            List<WebElement> servicesOfferedByEachRetailerInTheSearchResults = SearchResult.findElements(By.xpath(".//span[contains(@class, 'services__list-item-label')]"));
            if (servicesOfferedByEachRetailerInTheSearchResults.contains(AccessoriesAndMerchandiseRetailerService))
                result = true;
        }
        return result;
    }

    private boolean doRetailersInSearchResultOfferFleet50CarsService() {
        boolean result = false;
        for (WebElement SearchResult : SearchResults) {
            List<WebElement> servicesOfferedByEachRetailerInTheSearchResults = SearchResult.findElements(By.xpath(".//span[contains(@class, 'services__list-item-label')]"));
            if (servicesOfferedByEachRetailerInTheSearchResults.contains(Fleet50CarsRetailerService))
                result = true;
        }
        return result;
    }

    private boolean doRetailersInSearchResultOfferAccessToBodyShopService() {
        boolean result = false;
        for (WebElement SearchResult : SearchResults) {
            List<WebElement> servicesOfferedByEachRetailerInTheSearchResults = SearchResult.findElements(By.xpath(".//span[contains(@class, 'services__list-item-label')]"));
            if (servicesOfferedByEachRetailerInTheSearchResults.contains(AccessToBodyShopRetailerService))
                result = true;
        }
        return result;
    }

    private boolean doRetailersInSearchResultOfferPartsService() {
        boolean result = false;
        for (WebElement SearchResult : SearchResults) {
            List<WebElement> servicesOfferedByEachRetailerInTheSearchResults = SearchResult.findElements(By.xpath(".//span[contains(@class, 'services__list-item-label')]"));
            if (servicesOfferedByEachRetailerInTheSearchResults.contains(PartsRetailerService))
                result = true;
        }
        return result;
    }

    private boolean doRetailersInSearchResultOfferServicingService() {
        boolean result = false;
        for (WebElement SearchResult : SearchResults) {
            List<WebElement> servicesOfferedByEachRetailerInTheSearchResults = SearchResult.findElements(By.xpath(".//span[contains(@class, 'services__list-item-label')]"));
            if (servicesOfferedByEachRetailerInTheSearchResults.contains(ServicingRetailerService))
                result = true;
        }
        return result;
    }

    private boolean doRetailersInSearchResultOfferLocalBusinessService() {
        boolean result = false;
        for (WebElement SearchResult : SearchResults) {
            List<WebElement> servicesOfferedByEachRetailerInTheSearchResults = SearchResult.findElements(By.xpath(".//span[contains(@class, 'services__list-item-label')]"));
            if (servicesOfferedByEachRetailerInTheSearchResults.contains(LocalBusinessRetailerService))
                result = true;
        }
        return result;
    }

    private boolean doRetailersInSearchResultOfferMOTService() {
        boolean result = false;
        for (WebElement SearchResult : SearchResults) {
            List<WebElement> servicesOfferedByEachRetailerInTheSearchResults = SearchResult.findElements(By.xpath(".//span[contains(@class, 'services__list-item-label')]"));
            if (servicesOfferedByEachRetailerInTheSearchResults.contains(MOTRetailerService))
                result = true;
        }
        return result;
    }

    private boolean doRetailersInSearchResultOfferMotabilityService() {
        boolean result = false;
        for (WebElement SearchResult : SearchResults) {
            List<WebElement> servicesOfferedByEachRetailerInTheSearchResults = SearchResult.findElements(By.xpath(".//span[contains(@class, 'services__list-item-label')]"));
            if (servicesOfferedByEachRetailerInTheSearchResults.contains(MotabilityRetailerService))
                result = true;
        }
        return result;
    }

    private boolean doRetailersInSearchResultOfferUsedCarsService() {
        boolean result = false;
        for (WebElement SearchResult : SearchResults) {
            List<WebElement> servicesOfferedByEachRetailerInTheSearchResults = SearchResult.findElements(By.xpath(".//span[contains(@class, 'services__list-item-label')]"));
            if (servicesOfferedByEachRetailerInTheSearchResults.contains(UsedCarsRetailerService))
                result = true;
        }
        return result;
    }

    public void selectOneServiceInTheServicesFilter() {
        selectNewCarsServiceFilter();
    }

    public boolean isOneServiceFilterSelected() {
        return getAttribute(NewCarsServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    public boolean doRetailersInSearchResultOfferNewCarsService() {
        boolean result = false;
        for (WebElement SearchResult : SearchResults) {
            List<WebElement> servicesOfferedByEachRetailerInTheSearchResults = SearchResult.findElements(By.xpath(".//span[contains(@class, 'services__list-item-label')]"));
            if (servicesOfferedByEachRetailerInTheSearchResults.contains(NewCarsRetailerService))
                result = true;
        }
        return result;
    }

    public void openWithPreselectedServices(List<String> preselectedServices) {
        getDriver().get(getBaseURL() + findARetailerPageURL + preselectedServices);
        for (String aPreselectedServices : preselectedServices)
            getDriver().get(getBaseURL() + findARetailerPageURL + aPreselectedServices);
    }

    public void selectServiceInServiceFilter(List<String> service) {
        for (String aService : service) {
            switch (aService) {
                case "New cars":
                    selectNewCarsServiceFilter();
                    break;
                case "Used cars":
                    selectUsedCarsServiceFilter();
                    break;
                case "Motability":
                    selectMotabilityServiceFilter();
                    break;
                case "MOT":
                    selectMOTServiceFilter();
                    break;
                case "Local business":
                    selectLocalBusinessServiceFilter();
                    break;
                case "Servicing":
                    selectServicingServiceFilter();
                    break;
                case "Parts":
                    selectPartsServiceFilter();
                    break;
                case "Access to bodyshop":
                    selectAccessToBodyShopServiceFilter();
                    break;
                case "Fleet (50+ cars)":
                    selectFleet50CarsServiceFilter();
                    break;
                case "Accessories & Merchandise":
                    selectAccessoriesAndMerchandiseServiceFilter();
                    break;
            }
        }
    }

    private void selectAccessoriesAndMerchandiseServiceFilter() {
        click(AccessoriesAndMerchandiseServiceFilter, getShortTimeout());
    }

    private void selectFleet50CarsServiceFilter() {
        click(Fleet50CarsServiceFilter, getShortTimeout());
    }

    private void selectAccessToBodyShopServiceFilter() {
        click(AccessToBodyShopServiceFilter, getShortTimeout());
    }

    private void selectPartsServiceFilter() {
        click(PartsServiceFilter, getShortTimeout());
    }

    private void selectServicingServiceFilter() {
        click(ServicingServiceFilter, getShortTimeout());
    }

    private void selectLocalBusinessServiceFilter() {
        click(LocalBusinessServiceFilter, getShortTimeout());
    }

    private void selectMOTServiceFilter() {
        click(MOTServiceFilter, getShortTimeout());
    }

    private void selectMotabilityServiceFilter() {
        click(MotabilityServiceFilter, getShortTimeout());
    }

    public boolean areChosenServicesSelected(List<String> service) {
        boolean result = false;
        for (String aService : service) {
            switch (aService) {
                case "New cars":
                    result = isNewCarServiceFilterSelected();
                    break;
                case "Used cars":
                    result = isUsedCarsServiceFilterSelected();
                    break;
                case "Motability":
                    result = isMotabilityServiceFilterSelected();
                    break;
                case "MOT":
                    result = isMOTServiceFilterSelected();
                    break;
                case "Local business":
                    result = isLocalBusinessServiceFilterSelected();
                    break;
                case "Servicing":
                    result = isServicingServiceFilterSelected();
                    break;
                case "Parts":
                    result = isPartsServiceFilterSelected();
                    break;
                case "Access to bodyshop":
                    result = isAccessToBodyShopServiceFilterSelected();
                    break;
                case "Fleet (50+ cars)":
                    result = isFleet50CarsServiceFilterSelected();
                    break;
                case "Accessories & Merchandise":
                    result = isAccessoriesAndMerchandiseServiceFilterSelected();
                    break;
            }

        }
        return result;
    }

    private boolean isAccessoriesAndMerchandiseServiceFilterSelected() {
        return getAttribute(AccessoriesAndMerchandiseServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    private boolean isFleet50CarsServiceFilterSelected() {
        return getAttribute(Fleet50CarsServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    private boolean isAccessToBodyShopServiceFilterSelected() {
        return getAttribute(AccessToBodyShopServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    private boolean isPartsServiceFilterSelected() {
        return getAttribute(PartsServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    private boolean isServicingServiceFilterSelected() {
        return getAttribute(ServicingServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    private boolean isLocalBusinessServiceFilterSelected() {
        return getAttribute(LocalBusinessServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    private boolean isMOTServiceFilterSelected() {
        return getAttribute(MOTServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    private boolean isMotabilityServiceFilterSelected() {
        return getAttribute(MotabilityServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    private boolean isUsedCarsServiceFilterSelected() {
        return getAttribute(UsedCarsServiceFilter, "class", getShortTimeout()).contains("checked");
    }

    public boolean searchByLocationTextboxHasEnteredPostcode(List<String> postcode) {
        boolean result = false;
        for (String aPostcode : postcode)
            result = getAttribute(SearchByLocationTextBox, "value", getShortTimeout()).equals(aPostcode);
        return result;
    }
}