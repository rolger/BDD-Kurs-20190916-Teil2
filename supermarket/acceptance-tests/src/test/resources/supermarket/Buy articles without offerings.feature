Feature: buy article with offerings

  Background: create all products
    Given My shop "BEST SHOPPING" sells this products
      | product | unit  | price |
      | apple   | kg    | 0.90  |
      | bread   | kg    | 2.49  |
      | milk    | piece | 1.01  |

  Scenario: buy one article
    Given I add 2.0 kg of bread to my cart

    When I checkout on 2019-09-17

    Then I want to see the check
"""
             "BEST SHOPPING"

Your purchase from 2019-09-17
----------------------------------------
                                     EUR
bread x 2.0 kg                      4,98
----------------------------------------
Total :                             4,98
"""

  Scenario: buy multiple articles
    Given I add to my cart
      | product | quantity |
      | apple   | 0.73     |
      | bread   | 1.0      |
      | milk    | 2        |

    When I checkout on 2019-09-17

    Then I want to see the check
"""
             "BEST SHOPPING"

Your purchase from 2019-09-17
----------------------------------------
                                     EUR
apple x 0.73 kg                     0,66
bread x 1.0 kg                      2,49
milk x 2                            2,02
----------------------------------------
Total :                             5,17
"""