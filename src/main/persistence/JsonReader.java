package persistence;

import model.Player;
import model.Position;
import model.Team;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Team read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeam(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses team from JSON object and returns it
    private Team parseTeam(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Team team = new Team(name);
        addPlayers(team, jsonObject);
        return team;
    }

    // MODIFIES: team
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addPlayers(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(team, nextPlayer);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses players from JSON object and adds it to team roster
    private void addPlayer(Team team, JSONObject jsonObject) {
        int playerNum = jsonObject.getInt("Jersey Number");
        String name = jsonObject.getString("name");
        Position playerPos = Position.valueOf(jsonObject.getString("Position"));
        boolean playerHealth = jsonObject.getBoolean("Playable");
        int playerHeight = jsonObject.getInt("Height");
        int playerAge = jsonObject.getInt("Year");

        Player player = new Player(playerNum, name, playerPos, playerHealth, playerHeight, playerAge);
        team.addPlayer(player);
    }
}

