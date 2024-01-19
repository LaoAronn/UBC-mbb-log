package model;

import org.json.JSONObject;
import persistence.Writable;

// This class represents the player details
public class Player implements Writable {

    // variables
    private int jerseyNum;
    private String name;
    private Position position;
    private boolean isActive;
    private int height;
    private int year;


    // REQUIRES: The string name must include at least 1 character
    // MODIFIES: this
    // EFFECTS: Creates a player and make them available to join teams
    public Player(int num, String playerName, Position pos, boolean isHealthy, int height, int year) {
        this.name = playerName;
        this.jerseyNum = num;
        this.position = pos;
        this.year = year;
        this.height = height;
        this.isActive = isHealthy;
    }

    // REQUIRES: Must include 1 or more characters
    // MODIFIES: this
    // EFFECTS: Changing the players' Name
    public void setPlayerName(String name) {
        this.name = name;
    }

    // REQUIRES: Team members should have unique numbers & ranging from 0 to 99
    // MODIFIES: this
    // EFFECTS: Changing the players' jersey number
    public void setJerseyNum(int num) {
        this.jerseyNum = num;
    }

    // EFFECTS: changes the position by the inputted position
    public void setPosition(Position pos) {
        this.position = pos;
    }

    // EFFECTS: setting true if player is not injured and available to play, false otherwise
    public void setIsActive(boolean value) {
        this.isActive = value;
    }

    // EFFECTS: changes the height by the inputted height
    public void setHeight(int height) {
        this.height = height;
    }

    // EFFECTS: changes the age by the inputted age
    public void setYear(int year) {
        this.year = year;
    }

    // EFFECTS: returns the player's name
    public String getPlayerName() {
        return this.name;
    }

    // EFFECTS: returns the player's position
    public Position getPosition() {
        return this.position;
    }

    // EFFECTS: returns true if player is not injured and available to play, false otherwise
    public boolean getIsActive() {
        return this.isActive;
    }

    // EFFECTS: return the jersey number of the player
    public int getJerseyNum() {
        return this.jerseyNum;
    }

    // EFFECTS: return the height of the player
    public int getHeight() {
        return this.height;
    }

    // EFFECTS: return the age of the player
    public int getYear() {
        return this.year;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Year", year);
        json.put("Height", height);
        json.put("Jersey Number", jerseyNum);
        json.put("Position", position);
        json.put("Playable", isActive);
        return json;
    }
}
