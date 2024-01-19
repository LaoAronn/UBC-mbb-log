package persistence;

import model.Position;
import model.Team;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Team t = new Team("UBC Thunderbirds");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Team t = new Team("UBC Thunderbirds");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            t = reader.read();
            assertEquals("UBC Thunderbirds", t.getTeamName());
            assertEquals(0, t.getNumOfPlayers());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Position pos1 = Position.CENTER;
            Position pos2 = Position.POWERFORWARD;

            Team t = new Team("UBC Thunderbirds");
            t.addPlayer(new Player(6, "Davis", pos1, true, 208, 3));
            t.addPlayer(new Player(23, "Lebron", pos2, true, 198, 4));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            t = reader.read();
            assertEquals("UBC Thunderbirds", t.getTeamName());
            List<Player> players = t.getPlayers();
            assertEquals(2, players.size());
            assertEquals(6, players.get(0).getJerseyNum());
            assertEquals(23, players.get(1).getJerseyNum());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}