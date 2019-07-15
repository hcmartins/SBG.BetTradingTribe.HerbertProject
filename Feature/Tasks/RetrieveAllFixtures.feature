Feature: Retrieve all fixtures

  Scenario: Get All fixtures
  	Given The EndPointUrl is up and running
  	 When a user performs a get request to "/fixtures" with parameters ""
  	 And response contains 3 fixtures

  