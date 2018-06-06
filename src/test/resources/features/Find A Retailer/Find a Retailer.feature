@findaretailer
Feature: Find a Retailer

  As a visitor to volkswagen.co.uk
  I want to be able to search for retailers based on their location and the services that they offer
  So that the retailers offering the selected services are displayed

  #Did not use background as not applicable to last scenario

  Scenario: Services filter is hidden by default
    Given I am on the Find a retailer page
    Then the services filter is not displayed

  Scenario: Services filter is displayed
    Given I am on the Find a retailer page
    When I click on the 'Refine search by service' link toggle
    Then the services filter is displayed

  Scenario: Services filter can be hidden
    Given I am on the Find a retailer page
    And I display the services filter
    When I click on the 'Refine search by service' link toggle
    Then the services filter is not displayed

  Scenario: Services are not editable
    Given I am on the Find a retailer page
    When I display the services filter
    Then the services are not editable

  Scenario: Services can be selected
    Given I am on the Find a retailer page
    And I display the services filter
    When I click the services in the services filter
      | New cars  |
      | Used cars |
    Then the services are selected
      | New cars  |
      | Used cars |

  Scenario:  Search can be performed with no services selected
    Given I am on the Find a retailer page
    And I do not select any service in the services filter
    When I enter a postcode in the 'Search by location' textbox
      | E161BQ |
    And I click the 'Search by location' button
    Then the search result is displayed

  Scenario:  Search cannot be performed without providing a location
    Given I am on the Find a retailer page
    And I select the services in the services filter
      | New cars |
    When I do not enter a location in the 'Search by location' textbox
    And I click the 'Search by location' button
    Then the search result is not displayed

  Scenario: Nearest Retailers to the given location offering the selected services are displayed
    Given I am on the Find a retailer page
    And I select the services in the services filter
      | New cars  |
      | Used cars |
    When I enter a postcode in the 'Search by location' textbox
      | E161BQ |
    And I click the 'Search by location' button
    Then the retailers displayed in the search result offer the selected services
      | New cars  |
      | Used cars |

  Scenario:  Search can be performed with one service selected
    Given I am on the Find a retailer page
    And I select the services in the services filter
      | New cars |
    When I enter a postcode in the 'Search by location' textbox
      | E161BQ |
    And I click the 'Search by location' button
    Then the retailers displayed in the search result offer the selected services
      | New cars |

  Scenario: One or more services can be pre-selected in the retailer filter through the url
    Given I am on the 'Find a retailer' page with preselected services
      | ?searchTerm=e161bq&departments=New&departments=Used |
    Then the 'Search by location' textbox is prepopulated with the location
      | e161bq |
    And the services are preselected in the services filter
      | New cars  |
      | Used cars |
    And the retailers displayed in the search result offer the selected services
      | New cars  |
      | Used cars |