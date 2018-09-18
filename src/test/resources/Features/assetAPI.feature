Feature: Asset rest api tests

  @regression
  Scenario: To test the GET call for /sample1 endpoint
    Given that I am connected to the test environment
    When I perform a GET request to the "/sample1" endpoint
    Then I recieve a "200" status
    And the response contains the id, geometry, coordinates and type fields

  Scenario: To test the GET call for /sample2 endpoint
    Given that I am connected to the Connvex environment
    When I perform a GET request to the "/sample2" endpoint
    Then I recieve a "500" status
    And the response contains the "another" field