package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Team falcons;
    private Player p1;
    private Player p2;
    private Player p3;


    @BeforeEach
    void runBefore() {
        falcons = new Team("Langara Falcons");
        p1 = new Player(1, "Sam", Position.SHOOTINGGUARD, true, 190, 1);
        p2 = new Player(3, "Royce", Position.POINTGUARD, false, 180, 2);
        p3 = new Player(12, "Bay", Position.POWERFORWARD, true, 195, 3);
    }

    @Test
    void testConstructor() {
        assertTrue(p1.getIsActive());
        assertFalse(p2.getIsActive());

        assertEquals(1, p1.getJerseyNum());
        assertEquals(3, p2.getJerseyNum());
        assertEquals(12, p3.getJerseyNum());

        assertEquals(Position.SHOOTINGGUARD, p1.getPosition());
        assertEquals(Position.POINTGUARD, p2.getPosition());
        assertEquals(Position.POWERFORWARD, p3.getPosition());

        assertEquals(190, p1.getHeight());
        assertEquals(180, p2.getHeight());

        assertEquals(1, p1.getYear());
        assertEquals(2, p2.getYear());
    }

    @Test
    void testSetPlayerName() {
        p1.setPlayerName("Aaron");
        assertEquals("Aaron", p1.getPlayerName());
    }

    @Test
    void testSetJerseyNum(){
        p1.setJerseyNum(25);
        assertEquals(25, p1.getJerseyNum());
    }

    @Test
    void testSetPosition(){
        p1.setPosition(Position.CENTER);
        assertEquals(Position.CENTER, p1.getPosition());
    }

    @Test
    void testToString(){
        assertEquals("Sam", p1.toString());
        assertEquals("Royce", p2.toString());
    }

    @Test
    void testSetAgeAndHeight() {
        p1.setYear(4);
        assertEquals(4, p1.getYear());
        p1.setHeight(170);
        assertEquals(170, p1.getHeight());
    }

}
