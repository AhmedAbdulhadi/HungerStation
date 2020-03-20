Feature: High level of testing for Pets Model

Scenario: Adding new pet
Given Pet Request payload
When User wants to call "addNewPet" with http method "POST"
Then Status code should be 200

Scenario: Update pet
Given Pet Request payload
When User wants to call "updatePet" with http method "PUT"
Then Status code should be 200

Scenario: Get pet by status
Given Pet status is sold
When User wants to call "getPetByStatus" with http method "GET"
Then Status code should be 200

Scenario: Adding new pet
Given Pet Request Param ID is 1
When User wants to call "getPetById" with http method "GET"
Then Status code should be 200