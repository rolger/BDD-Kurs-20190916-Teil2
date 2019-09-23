Feature: buy article with offerings

  Background: create all products
    Given My shop "BEST SHOPPING" sells this products
      | product | unit  | price |
      | apple   | kg    | 0.90  |
      | bread   | kg    | 2.49  |
      | milk    | piece | 1.01  |

    Given My shop "BEST SHOPPING" offers this discounts
      | product | offering |
      | milk    | 2-1      |
      | bread   | 2-1      |


  Scenario: buy 2 articles with 2-1 discount
    Given I add to my cart
      | product | quantity |
      | apple   | 0.73     |
      | milk    | 2        |

    When I checkout on 2019-09-17

    Then I want to see the check
"""
             "BEST SHOPPING"

Your purchase from 2019-09-17
----------------------------------------
                                     EUR
apple x 0.73 kg                     0,66
milk x 2                            2,02
  buy 2 pay 1                      -1,01
----------------------------------------
Total :                             1,67
"""

  Scenario: buy 3 articles with 2-1 discount
    Given I add to my cart
      | product | quantity |
      | milk    | 3        |

    When I checkout on 2019-09-17

    Then I want to see the check
"""
             "BEST SHOPPING"

Your purchase from 2019-09-17
----------------------------------------
                                     EUR
milk x 3                            3,03
  buy 2 pay 1                      -1,01
----------------------------------------
Total :                             2,02
"""

  Scenario: 2-1 discount can not be apply to products with unit kg
    Given I add to my cart
      | product | quantity |
      | bread   | 3.0      |

    When I checkout on 2019-09-17

    Then I want to see the check
"""
             "BEST SHOPPING"

Your purchase from 2019-09-17
----------------------------------------
                                     EUR
bread x 3.0 kg                      7,47
----------------------------------------
Total :                             7,47
"""