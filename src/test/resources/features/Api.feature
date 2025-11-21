Feature: Dummy JSON API with JWT Authentication
  This feature tests login and CRUD operations with JWT-protected endpoints.

  Background:
    Given the API base URL is "https://dummyjson.com"

  Scenario: Obtain valid JWT token
    When I send a POST request to "/auth/login" with body:
    """
    {
      "username": "emilys",
      "password": "emilyspass"
    }
    """
    Then the response status code should be 200
    And I store the value of "accessToken" as "authToken"


  Scenario: Get user profile with JWT
    Given I use the stored token "authToken"
    When I send a GET request to "/auth/me"
    Then the response status code should be 200
    And the response should contain "emilys" in the "username" field
    Then the response should match the schema "userSchema.json"

