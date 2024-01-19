# My Personal Project #1

## Basketball Team Manager

This system is for recording the player roster list for a basketball team. It allows
users to enter their team roster with their jersey numbers, positions, height, age, and name.
At the end of every season, users can remove any players and add any new playing who are
joining the team.


#### What will the application do?
It is a system for coaches / players to keep a list of their players in their team

#### Who will use it?
Coach & Team: Able to use their system to keep track of their players and availability
Other users: Able to view the team’s roster and lineups

#### Why is this project of interest to you?
I believe that every team should have an interface for keeping track of their players.
Thus, it has led my interest into making a program for coaches and scouts to keep track of their
team’s roster to make future decisions on acquiring future players and creating different
play styles based on their players’ information that is displayed in a neat interface.

<br>

## User Stories <Phase 1>
- As a user, I want to be able to add a player to the team
- As a user, I want to be able to remove a player from the team
- As a user, I want to be able to display active players who are ready to play
- As a user, I want to be able to display inactive players who are injured and unable to play
- As a user, I want to be able to display information of the players of the team

<br>

## User Stories <Phase 2>
As a user, I want to be able to save the players and game data list inputted
As a user, I want to be able to be able to load my previously saved data from my files

<br>

## User Stories <Phase 3>
As a user, I want to be able to save the players to the roster through the interface
As a user, I want to be able to load the team roster and display it in a tidy matter through an interface
As a user, I want to be able to load my previously saved data from my files in the interface
As a user, I want to be able to remove players if the roster is full or if a player is graduating and leaving

<br>

## Instructions for Grader

- You can add Players by choosing the first option (1) Add Players

- You can remove any Players by..
- 1) Select the second option 2) Remove Players
- 2) An interface will pop up with the list of player names.
-    To remove a player, simply click on their names and click the button remove

- You can display the player roster through the third option 3) Display Players
- You can see the information of each player through the third option 3) Display Players 
- by clicking onto their names on the left panel

- To save your players added, you can choose the fourth option 4) save data to save data,
- a pop up message will let you know that you have saved your progress.

- To load your previous data, you should choose the fifth option 5) load data to load
- previously added data

- Once you have reached the max capacity for the team roster, you WILL NOT be able
- to add anymore players until you remove players for additional space.

- If you decide to remove players although there are no existing players,
- you will receive an error pop up message that prohibits you from accessing

- You can REMOVE ALL DATA by choosing the fourth option 4) save data before choosing 
- the fifth option 5) load data

## Phase 4: Task 2
 - The Event Log interface in my project works. It logs the interaction of whenever a user
 - decides to add a player or remove a player. The interaction includes the date and a
 - short description of the action (Added Player to Roster: Name & Removed Player from Roster: Name).
 
 - For the QUIT Program button in the main interface, it will print out the interactions
 - from the event log into the console before closing the program.

## Phase 4: Task 3
- Upon finishing my UML Diagram, I realised that there were open opportunities
- where I could implement different classes to create another project
- with similar Idea. Furthermore, it has made me realise the relationship between
- the classes and how to properly correlate each class.

- If I had more time to consider for refactoring, I would consider adjusting my
- Player class such that it would recognise Players that were removed from the
- Team class (Maybe save in a Json file). Furthermore, I believe I would consider
- creating multiple team objects for the project such that It could branch out to
- creating different college teams or UBC thunderbird's male & female basketball team
- roster for each year.
