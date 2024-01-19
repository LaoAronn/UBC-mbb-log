package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team falcons;
    private Player p1;
    private Player p2;
    ArrayList<Player> plist;

    @BeforeEach
    void runBefore() {
        falcons = new Team("Langara Falcons");
        plist = new ArrayList<>();
        p1 = new Player(1, "Sam", Position.SHOOTINGGUARD, true, 190, 1);
        p2 = new Player(3, "Royce", Position.POINTGUARD, false, 180, 2);
    }

    @Test
    void testConstructor() {
        assertEquals("Langara Falcons", falcons.getTeamName());
        assertEquals(10, falcons.getMaxPlayersInTeam());
        assertEquals(plist, falcons.getPlayers());
    }

    @Test
    void testAddPlayer() {

        assertEquals(plist, falcons.getPlayers()); // Should be empty

        falcons.addPlayer(p1);
        plist.add(p1);
        assertEquals(1, falcons.getNumOfPlayers());
        assertEquals(plist, falcons.getPlayers());
        falcons.addPlayer(p2);

        plist.add(p2);
        assertEquals(2, falcons.getNumOfPlayers());
        assertEquals(plist, falcons.getPlayers());
    }

    @Test
    void testAddPlayerWhenRosterFull() {
        // Filling up players
        for (int i = 0; i < 10; i++){
            falcons.addPlayer(p1);
            plist.add(p1);
        }

        falcons.addPlayer(p2); // Should Not have added player into roster
        assertEquals(falcons.getMaxPlayersInTeam(), falcons.getNumOfPlayers());
        assertEquals(plist, falcons.getPlayers()); // Player 2 does not exist
    }

    @Test
    void testRemovePlayer() {
        // Set up
        falcons.addPlayer(p1);
        falcons.addPlayer(p2);
        plist.add(p1);
        plist.add(p2);

        // Test
        falcons.removePlayer(p1);
        plist.remove(p1);
        assertEquals(plist, falcons.getPlayers());
        falcons.removePlayer(p2);
        plist.remove(p2);
        assertEquals(plist, falcons.getPlayers());
    }

}
