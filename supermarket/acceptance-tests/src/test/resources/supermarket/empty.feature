

Background: create all products
    Given This Products and offeriungs



    Scenario: buy one article

    Given 2 pieces of Schwarzbrot in my cart
    When I checkout
    Then I want to see the check
"""
             BEST SHOPPING

Your purchase from 2019-09-17
----------------------------------------
EUR
Schwarzbrot
2 x 2,49                            4,98
10% Rabatt                         -0,50
----------------------------------------
Total :                             4,98
"""
