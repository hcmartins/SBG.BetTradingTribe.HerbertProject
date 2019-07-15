Feature: Store a new fixture

  Scenario: Store a new fixture and assert that it contains teamId
  	Given The EndPointUrl is up and running
  	When a user performs a post request to "/fixture" with below details
      """
     {"fixtureId":"6","fixtureStatus": {"displayed":true,"suspended":true},"footballFullState": {"homeTeam":"test string","awayTeam":"test string","finished":true,"gameTimeInSeconds":7,"goals":[ {"clockTime":7,"confirmed":true,"id":7,"ownGoal":true,"penalty":true,"period":"test string","playerId":7,"teamId":"test string"}],"period":"test string","possibles":[ {}],"corners":[ {}],"redCards":[ {}],"yellowCards":[ {}],"startDateTime":"test string","started":true,"teams":[ {"association":"test string","name":"test string","teamId":"HOME"}]}}  
      """
  	 Then response contains "Fixture has been added"
  	 #Given The EndPointUrl is up and running
  	 When a user performs a get request to "/fixture" with parameters "/6"
  	 And response contains 1 "footballFullState.teams.teamId" tag
  	 
  Scenario: Delete a Fixture
  	Given The EndPointUrl is up and running
  	When a user performs a delete request to "/fixture/6" with below details
	And response contains "Fixture has been deleted"
  