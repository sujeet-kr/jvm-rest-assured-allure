Feature: POI rest api tests

  @regression
  Scenario: To test the GET call for /poi endpoint
    Given that I am connected to the Connvex environment
    When I perform a GET request to the "/poi" endpoint
    Then I recieve a "200" status
    And the response contains the id, geometry, coordinates, type, description, name and markForDeletion fields

  Scenario: To test the GET call for /poi endpoint
    Given that I am connected to the Connvex environment
    When I perform a GET request to the "/another" endpoint
    Then I recieve a "500" status
    And the response contains the "another" field