Feature: Point Checker API Tests

  Background:
    * url 'http://localhost:8080/web-lab-1.0-SNAPSHOT'

  Scenario: Check point in circle area (hit)
    Given path '/controllerServlet'
    And form field x-value = 0
    And form field y-value = 1
    And form field r-value = 2
    When method post
    Then status 200
    And match response contains { x: 0, y: 1, r: 2, status: "True" }

  Scenario: Check point in triangle area (hit)
    Given path '/controllerServlet'
    And form field x-value = 1
    And form field y-value = -1
    And form field r-value = 2
    When method post
    Then status 200
    And match response contains { x: 1, y: -1, r: 2, status: "True" }

  Scenario: Check point in square area (hit)
    Given path '/controllerServlet'
    And form field x-value = -1
    And form field y-value = -1
    And form field r-value = 2
    When method post
    Then status 200
    And match response contains { x: -1, y: -1, r: 2, status: "True" }

  Scenario: Check point outside all areas (miss)
    Given path '/controllerServlet'
    And form field x-value = 2
    And form field y-value = 2
    And form field r-value = 2
    When method post
    Then status 200
    And match response contains { x: 2, y: 2, r: 2, status: "False" }

  Scenario: Check point with invalid R value
    Given path '/controllerServlet'
    And form field x-value = 1
    And form field y-value = 1
    And form field r-value = 6
    When method post
    Then status 400
    And match response == { error: "R should be between 2 and 5" }

  Scenario: Check point with invalid X value (non-number)
    Given path '/controllerServlet'
    And form field x-value = 'abc'
    And form field y-value = 1
    And form field r-value = 2
    When method post
    Then status 400
    And match response == { error: "Invalid number format" }

  Scenario: Check point with missing Y value
    Given path '/controllerServlet'
    And form field x-value = 1
    And form field r-value = 2
    When method post
    Then status 400
    And match response == { error: "Missing required parameters" }

  Scenario: Clear points collection
    Given path '/clean'
    When method delete
    Then status 200

  Scenario: Get points collection after clear
    Given path '/controllerServlet'
    And form field x-value = 0
    And form field y-value = 0
    And form field r-value = 2
    When method post
    Then status 200
    And match response contains { x: 0, y: 0, r: 2, status: "True" }
    Given path '/clean'
    When method delete
    Then status 200
