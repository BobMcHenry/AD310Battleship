package battleship.test;

import battleship.model.BattleshipGame;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Write a description of class BattleshipGameTest here.
 *
 * @author Jesse
 * @version 11/30/2015
 */
public class BattleshipGameTest {
    private final String PLAYER1 = "Player 1";
    private final String PLAYER2 = "Player 2";

    private BattleshipGame game;

    private final int[][] P1_AIRCRAFT_CARRIER = new int[][]{{0, 0}, {4, 4}};
    private final int[][] P1_DESTROYER1_LOCATION = new int[][]{{9, 1}, {9, 0}};
    private final int[][] P1_DESTROYER2_LOCATION = new int[][]{{9, 9}, {8, 8}};
    private final int[][] P1_BATTLESHIP_LOCATION = new int[][]{{4, 6}, {7, 6}};
    private final int[][] P1_CRUISER_LOCATION = new int[][]{{0, 3}, {0, 5}};

    private final int[][] P2_AIRCRAFT_CARRIER = new int[][]{{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}};
    private final int[][] P2_DESTROYER1_LOCATION = new int[][]{{9, 0}, {9, 1}};
    private final int[][] P2_DESTROYER2_LOCATION = new int[][]{{8, 2}, {8, 3}};
    private final int[][] P2_BATTLESHIP_LOCATION = new int[][]{{4, 6}, {5, 6}, {6, 6}, {7, 6}};
    private final int[][] P2_CRUISER_LOCATION = new int[][]{{3, 7}, {2, 8}, {1, 9}};

    private final int[] P1_MISS = new int[]{10, 0};
    private final int[] P2_MISS = new int[]{0, 0};

    private void placeP1ShipsValid() {
        assertTrue("Placing player 1 aircraft carrier should return true.", game.placeShip("aircraft carrier", P1_AIRCRAFT_CARRIER[0][0], P1_AIRCRAFT_CARRIER[0][1], P1_AIRCRAFT_CARRIER[1][0], P1_AIRCRAFT_CARRIER[1][1]));
        assertTrue("Placing player 1 destroyer 1 should return true.", game.placeShip("destroyer", P1_DESTROYER1_LOCATION[0][0], P1_DESTROYER1_LOCATION[0][1], P1_DESTROYER1_LOCATION[1][0], P1_DESTROYER1_LOCATION[1][1]));
        assertTrue("Placing player 1 destroyer 2 should return true.", game.placeShip("destroyer", P1_DESTROYER2_LOCATION[0][0], P1_DESTROYER2_LOCATION[0][1], P1_DESTROYER2_LOCATION[1][0], P1_DESTROYER2_LOCATION[1][1]));
        assertTrue("Placing player 1 battleship should return true.", game.placeShip("battleship", P1_BATTLESHIP_LOCATION[0][0], P1_BATTLESHIP_LOCATION[0][1], P1_BATTLESHIP_LOCATION[1][0], P1_BATTLESHIP_LOCATION[1][1]));
        assertTrue("Placing player 1 cruiser should return true.", game.placeShip("cruiser", P1_CRUISER_LOCATION[0][0], P1_CRUISER_LOCATION[0][1], P1_CRUISER_LOCATION[1][0], P1_CRUISER_LOCATION[1][1]));
    }

    private void placeP1ShipsInvalid() {
        // Try to place two destroyers at same location
        assertTrue("Placing player 1 destroyer 1 should return true.", game.placeShip("destroyer", P1_DESTROYER1_LOCATION[0][0], P1_DESTROYER1_LOCATION[0][1], P1_DESTROYER1_LOCATION[1][0], P1_DESTROYER1_LOCATION[1][1]));
        assertFalse("Placing player 1 destroyer 2 over destroyer 1 should return false.", game.placeShip("destroyer", P1_DESTROYER1_LOCATION[0][0], P1_DESTROYER1_LOCATION[0][1], P1_DESTROYER1_LOCATION[1][0], P1_DESTROYER1_LOCATION[1][1]));

        // Try to place two larger ships at same location
        assertTrue("Placing player 1 battleship should return true.", game.placeShip("battleship", P1_BATTLESHIP_LOCATION[0][0], P1_BATTLESHIP_LOCATION[0][1], P1_BATTLESHIP_LOCATION[1][0], P1_BATTLESHIP_LOCATION[1][1]));
        assertFalse("Placing player 1 cruiser over battleship should return false.", game.placeShip("cruiser", P1_BATTLESHIP_LOCATION[0][0], P1_BATTLESHIP_LOCATION[0][1], P1_BATTLESHIP_LOCATION[1][0], P1_BATTLESHIP_LOCATION[1][1]));


        // Try to place intersecting ships
        assertTrue("Placing player 1 aircraft carrier should return true.", game.placeShip("aircraft carrier", P1_AIRCRAFT_CARRIER[0][0], P1_AIRCRAFT_CARRIER[0][1], P1_AIRCRAFT_CARRIER[1][0], P1_AIRCRAFT_CARRIER[1][1]));
        assertFalse("Placing player 1 destroyer 2 over aircraft carrier should return false.", game.placeShip("destroyer", 0, 1, 1, 0));
    }

    private void placeP2ShipsValid() {
        //player 2
        assertTrue("Placing player 2 aircraft carrier should return true.", game.placeShip("aircraft carrier", P2_AIRCRAFT_CARRIER[0][0], P2_AIRCRAFT_CARRIER[0][1], P2_AIRCRAFT_CARRIER[4][0], P2_AIRCRAFT_CARRIER[4][1]));
        assertTrue("Placing player 2 destroyer 1 should return true.", game.placeShip("destroyer", P2_DESTROYER1_LOCATION[0][0], P2_DESTROYER1_LOCATION[0][1], P2_DESTROYER1_LOCATION[1][0], P2_DESTROYER1_LOCATION[1][1]));
        assertTrue("Placing player 2 destroyer 2 should return true.", game.placeShip("destroyer", P2_DESTROYER2_LOCATION[0][0], P2_DESTROYER2_LOCATION[0][1], P2_DESTROYER2_LOCATION[1][0], P2_DESTROYER2_LOCATION[1][1]));
        assertTrue("Placing player 2 battleship should return true.", game.placeShip("battleship", P2_BATTLESHIP_LOCATION[0][0], P2_BATTLESHIP_LOCATION[0][1], P2_BATTLESHIP_LOCATION[3][0], P2_BATTLESHIP_LOCATION[3][1]));
        assertTrue("Placing player 2 cruiser should return true.", game.placeShip("cruiser", P2_CRUISER_LOCATION[0][0], P2_CRUISER_LOCATION[0][1], P2_CRUISER_LOCATION[2][0], P2_CRUISER_LOCATION[2][1]));
    }

    private void sinkP2AircraftCarrier() {
        game.makeShot(P2_AIRCRAFT_CARRIER[0][0], P2_AIRCRAFT_CARRIER[0][1]);
        game.makeShot(P2_AIRCRAFT_CARRIER[1][0], P2_AIRCRAFT_CARRIER[1][1]);
        game.makeShot(P2_AIRCRAFT_CARRIER[2][0], P2_AIRCRAFT_CARRIER[2][1]);
        game.makeShot(P2_AIRCRAFT_CARRIER[3][0], P2_AIRCRAFT_CARRIER[3][1]);
        game.makeShot(P2_AIRCRAFT_CARRIER[4][0], P2_AIRCRAFT_CARRIER[4][1]);

    }
    private void sinkP2Destroyer1() {
        game.makeShot(P2_DESTROYER1_LOCATION[0][0], P2_DESTROYER1_LOCATION[0][1]);
        game.makeShot(P2_DESTROYER1_LOCATION[1][0], P2_DESTROYER1_LOCATION[1][1]);
    }
    private void sinkP2Destroyer2() {
        game.makeShot(P2_DESTROYER2_LOCATION[0][0], P2_DESTROYER2_LOCATION[0][1]);
        game.makeShot(P2_DESTROYER2_LOCATION[1][0], P2_DESTROYER2_LOCATION[1][1]);
    }

    private void sinkP2BattleShip() {
        game.makeShot(P2_BATTLESHIP_LOCATION[0][0], P2_BATTLESHIP_LOCATION[0][1]);
        game.makeShot(P2_BATTLESHIP_LOCATION[1][0], P2_BATTLESHIP_LOCATION[1][1]);
        game.makeShot(P2_BATTLESHIP_LOCATION[2][0], P2_BATTLESHIP_LOCATION[2][1]);
        game.makeShot(P2_BATTLESHIP_LOCATION[3][0], P2_BATTLESHIP_LOCATION[3][1]);
    }

    private void sinkP2Cruiser() {
        game.makeShot(P2_CRUISER_LOCATION[0][0], P2_CRUISER_LOCATION[0][1]);
        game.makeShot(P2_CRUISER_LOCATION[1][0], P2_CRUISER_LOCATION[1][1]);
        game.makeShot(P2_CRUISER_LOCATION[2][0], P2_CRUISER_LOCATION[2][1]);
    }

    @Before
    public void setUp() throws Exception {
        game = new BattleshipGame(PLAYER1, PLAYER2);
    }

    @Test
    public void testGetActivePlayer() throws Exception {
        String player = game.getActivePlayerName();
        assertEquals(player, PLAYER1);
    }

    @Test
    public void testGetDefensePlayer() throws Exception {
        String player = game.getDefensePlayerName();
        assertEquals(player, PLAYER2);
    }

    @Test
    public void testSwitchActivePlayer() throws Exception {
        String activePlayer1 = game.getActivePlayerName();
        String defensivePlayer1 = game.getDefensePlayerName();
        game.switchActivePlayer();
        String activePlayer2 = game.getActivePlayerName();
        String defensivePlayer2 = game.getDefensePlayerName();
        assertNotEquals(activePlayer1, activePlayer2);
        assertNotEquals(defensivePlayer1, defensivePlayer2);
        assertEquals(activePlayer1, defensivePlayer2);
        assertEquals(activePlayer2, defensivePlayer1);
    }

    @Test
    public void testResetGame() throws Exception {
        game.resetGame(PLAYER1, PLAYER2);
        assertFalse("isGameOver should be false", game.isGameOver());

        //assertTrue("Player 1 should not have ships", game.getShipLocations(game.getP1()).length == 0);
        //assertTrue("Player 2 should not have ships", game.getShipLocations(game.getP2()).length == 0);

        boolean[] p1Board = game.getBoard(game.getP1());
        for (boolean status : p1Board) {
            assertFalse("Player1 board should be empty", status);
        }

        boolean[] p2Board = game.getBoard(game.getP1());
        for (boolean status : p2Board) {
            assertFalse("Player1 board should be empty", status);
        }
    }

    @Test
    public void testPlaceShipValid() throws Exception {
        placeP1ShipsValid();
        placeP2ShipsValid();
    }

    @Test
    public void testPlaceShipInvalid() throws Exception {
        placeP1ShipsInvalid();
    }

    @Test
    public void testGetShipLocations() throws Exception {

    }

    @Test
    public void testMakeShot() throws Exception {

    }

    @Test
    public void testIsGameOver() throws Exception {
        placeP1ShipsValid();
        placeP2ShipsValid();
        assertFalse("isGameOver should be false at the start of a game", game.isGameOver());

        sinkP2AircraftCarrier();
        assertFalse("isGameOver should be false while the defense player has unsunk ships", game.isGameOver());

        sinkP2Destroyer1();
        sinkP2Destroyer2();
        sinkP2BattleShip();
        sinkP2Cruiser();
        assertTrue("isGameOver should be true when all defense ships are sunk", game.isGameOver());
    }
}