package battleship.test;

import battleship.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Write a description of class BattleshipGameTest here.
 *
 * @author Jesse
 * @version 11/30/2015
 */
public class BattleshipGameTest {

    private final String ACTIVE_PLAYER = "Active Player";
    private final String DEFENSIVE_PLAYER = "Defensive Player";

    private BattleshipGame bsg;

    private Location[] AIRCRAFT_CARRIER_LOCATION;
    private Location[] DESTROYER1_LOCATION;
    private Location[] DESTROYER2_LOCATION;
    private Location[] BATTLESHIP_LOCATION;
    private Location[] CRUISER_LOCATION;

    @Before
    public void setUp() throws Exception {
        bsg = new BattleshipGame(ACTIVE_PLAYER, DEFENSIVE_PLAYER);
        AIRCRAFT_CARRIER_LOCATION = new Location[Ship.SizeFromShipType(ShipType.AIRCRAFT_CARRIER)];
        DESTROYER1_LOCATION = new Location[Ship.SizeFromShipType(ShipType.DESTROYER)];
        DESTROYER2_LOCATION = new Location[Ship.SizeFromShipType(ShipType.DESTROYER)];
        BATTLESHIP_LOCATION = new Location[Ship.SizeFromShipType(ShipType.BATTLESHIP)];
        CRUISER_LOCATION = new Location[Ship.SizeFromShipType(ShipType.CRUISER)];
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetActivePlayer() throws Exception {
        Player player = bsg.getActivePlayer();
        assertTrue(player.getName().compareTo(ACTIVE_PLAYER)==0);
    }

    @Test
    public void testGetDefensePlayer() throws Exception {
        Player player = bsg.getDefensePlayer();
        assertTrue(player.getName().compareTo(DEFENSIVE_PLAYER)==0);
    }

    @Test
    public void testSwitchActivePlayer() throws Exception {
        Player activePlayer1 = bsg.getActivePlayer();
        Player defensivePlayer1 = bsg.getActivePlayer();
        bsg.switchActivePlayer();
        Player activePlayer2 = bsg.getActivePlayer();
        Player defensivePlayer2 = bsg.getActivePlayer();
        assertNotEquals(activePlayer1, activePlayer2);
        assertNotEquals(defensivePlayer1, defensivePlayer2);
        assertEquals(activePlayer1, defensivePlayer2);
        assertEquals(activePlayer2, defensivePlayer1);
    }

    @Test
    public void testResetGame() throws Exception {

    }

    @Test
    public void testPlaceShip() throws Exception {

    }

    @Test
    public void testGetShipCoords() throws Exception {

    }

    @Test
    public void testGetShipLocations() throws Exception {

    }

    @Test
    public void testMakeShot() throws Exception {

    }

    @Test
    public void testIsGameOver() throws Exception {

    }


    @Test
    public void testGetBoard() throws Exception {

    }

    @Test
    public void testGetStateFromXY() throws Exception {

    }
}