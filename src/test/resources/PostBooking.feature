Feature: Post Booking

  Background: Booking endpoint should allow to put a booking

    Scenario Outline: /booking should allow to create a booking
      Given I perform a POST call to the create endpoint with the following data
        | <FirstName> | <LastName> | <TotalPrice> | <DepositPaid> | <CheckIn> | <CheckOut> | <AditionalNeeds> |
      And I verify the following data in the body response
        | <FirstName> | <LastName> | <TotalPrice> | <DepositPaid> | <CheckIn> | <CheckOut> | <AditionalNeeds> |
      Examples:
        | FirstName | LastName | TotalPrice | DepositPaid | CheckIn    | CheckOut   | AditionalNeeds |
        | Jassir    | Guzman   | 150        | True        | 1999-02-04 | 1999-02-06 | TV             |

    Scenario Outline: /booking should allow to create a booking
      Given I perform a POST call to the create endpoint with the following data
        | <FirstName> | <LastName> | <TotalPrice> | <DepositPaid> | <CheckIn> | <CheckOut> | <AditionalNeeds> |
      Examples:
        | FirstName | LastName | TotalPrice | DepositPaid | CheckIn | CheckOut | AditionalNeeds |
        | Jassir    | Guzman   | 150        | True        | a       | a        | TV             |
