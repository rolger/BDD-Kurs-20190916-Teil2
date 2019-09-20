Feature: buy article with offerings

    Background: create all products
        Given My shop is called "BEST SHOPPING"
        Given This products in my catalog
            | product | price |
            | apple   | 0.90  |
            | bread   | 2.49  |
            | milk    | 1.01  |

    Scenario: buy one article

        Given I add 2.0 kg of bread to my cart
        When I checkout on 2019-09-17
        Then I want to see the check
"""
             "BEST SHOPPING"

Your purchase from 2019-09-17
----------------------------------------
                                     EUR
bread                               4,98
----------------------------------------
Total :                             4,98
"""

    Scenario: buy one article

        Given I add 0.73 kg of apple to my cart
        Given I add 1.0 kg of bread to my cart
        Given I add 2 pieces of milk to my cart
        When I checkout on 2019-09-17
        Then I want to see the check
"""
             "BEST SHOPPING"

Your purchase from 2019-09-17
----------------------------------------
                                     EUR
apple                               0,66
bread                               2,49
milk                                2,02
----------------------------------------
Total :                             5,17
"""