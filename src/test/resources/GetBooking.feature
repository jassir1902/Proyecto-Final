Feature: Get Booking
  Background: Booking endpoint should allow to get a booking by id

    Scenario: /booking/{id} should return an specific booking
      Given I perform a GET call to the booking endpoint with id "1"
      Then I verify that the status code is 200
      And I verify that the body does not have size 0

    Scenario: /booking/{id} with a non-existent should return 404 code
      Given I perform a GET call to the booking endpoint with id "999999999"
      Then I verify that the status code is 404

    Scenario: /booking/{id} with a bad id should return 404 code
      Given I perform a GET call to the booking endpoint with id "aa@@"
      Then I verify that the status code is 404

