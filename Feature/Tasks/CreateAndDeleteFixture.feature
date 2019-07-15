Feature: Create and delete a fixture

  Scenario: Create a new Fixture
  	Given The EndPointUrl is up and running
  	When a user performs a post request to "/fixture" with below details
      """
     {"fixtureId":"5","fixtureStatus": {"displayed":true,"suspended":true},"footballFullState": {"homeTeam":"test string","awayTeam":"test string","finished":true,"gameTimeInSeconds":7,"goals":[ {"clockTime":7,"confirmed":true,"id":7,"ownGoal":true,"penalty":true,"period":"test string","playerId":7,"teamId":"test string"}],"period":"test string","possibles":[ {}],"corners":[ {}],"redCards":[ {}],"yellowCards":[ {}],"startDateTime":"test string","started":true,"teams":[ {"association":"test string","name":"test string","teamId":"test string"}]}}  
      """
  	 And response contains "Fixture has been added"
  	 
  Scenario: Delete a Fixture
  	Given The EndPointUrl is up and running
  	When a user performs a delete request to "/fixture/5" with below details
	And response contains "Fixture has been deleted"
  