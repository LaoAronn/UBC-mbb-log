package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


// This class represents details of a team
public class Team implements Writable {

    public static final int MAX_PLAYERS_IN_TEAM = 10;
    private final String teamName;
    private final ArrayList<Player> players;

    // REQUIRES: The string name must include at least 1 character
    // MODIFIES: this
    // EFFECTS: Creates a team and assigning the team name according to the given String.
    public Team(String name) {
        this.teamName = name;
        this.players = new ArrayList<>();
    }

    // REQUIRES: An existing player AND team must have less than 10 members
    // MODIFIES: players
    // EFFECTS: Adds player to the roster
    public void addPlayer(Player newMember) {
        if (this.players.size() < MAX_PLAYERS_IN_TEAM) {
            players.add(newMember);
            EventLog.getInstance().logEvent(new Event("Added Player to Roster: " + newMember.getPlayerName()));
        }
    }


    // REQUIRES: Player must be in the team
    // MODIFIES: players
    // EFFECTS: Remove player from roster
    public void removePlayer(Player member) {
        players.remove(member);
        EventLog.getInstance().logEvent(new Event("Removed Player from Roster: " + member.getPlayerName()));
    }

    // EFFECTS: returns max capacity of team
    public int getMaxPlayersInTeam() {
        return MAX_PLAYERS_IN_TEAM;
    }

    // EFFECTS: returns Team Name
    public String getTeamName() {
        return this.teamName;
    }

    // EFFECTS: returns Number of players in the team
    public int getNumOfPlayers() {
        return this.players.size();
    }

    // EFFECTS: return the players in the team
    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", teamName);
        json.put("Players", playersToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : players) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
