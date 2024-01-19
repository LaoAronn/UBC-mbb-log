package persistence;

import model.Team;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Team t = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Team t = reader.read();
            assertEquals("UBC Thunderbirds", t.getTeamName());
            assertEquals(0, t.getNumOfPlayers());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Team t = reader.read();
            assertEquals("UBC Thunderbirds", t.getTeamName());
            List<Player> players = t.getPlayers();
            assertEquals(2, players.size());
            assertEquals(6, players.get(0).getJerseyNum());
            assertEquals(23, players.get(1).getJerseyNum());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}