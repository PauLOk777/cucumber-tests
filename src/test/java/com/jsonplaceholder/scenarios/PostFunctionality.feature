@PostModel
Feature: Test the post functionality of jsonplaceholder REST API

  Scenario: REST API should return existing post by id
    Given post id is 1
    When I try to get post by id
    Then I receive status code 200
    And I receive post with id 1

  Scenario: REST API should create new post
    Given post with next data:
      | userId | title    | body    |
      | 1      | My title | My body |
    When I try to create new post
    Then I receive status code 201
    And I receive post with id 101

  Scenario: REST API should modify existing post
    Given post with next data:
      | id | userId | title    | body    |
      | 1  | 1      | My title | My body |
    When I try to modify post
    Then I receive status code 200
    And I receive post with id 1

  Scenario: REST API should delete existing post
    Given post id is 1
    When I try to delete post
    Then I receive status code 200
