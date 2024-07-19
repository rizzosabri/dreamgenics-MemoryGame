Feature: memoryGame

  Scenario: testing different api calls

  Scenario: Create a new score
    Given a new score with 5 moves
    When the score is saved
    Then the saved score should have 5 moves
