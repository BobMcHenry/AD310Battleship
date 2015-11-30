package battleship.model;

/**
 * Created by bobm on 11/29/15.
 */
public class Temp {

    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame("Bob", "Mario");

        //player 1
        game.placeShip("aircraft carrier", 0, 0, 4, 4);
        game.placeShip("destroyer", 9, 1, 9, 0);
        game.placeShip("destroyer", 9, 9, 8, 8);
        game.placeShip("battleship", 4, 6, 7, 6);
        game.placeShip("cruiser", 0, 3, 0, 5);


        //player 2
        game.placeShip("aircraft carrier", 1, 1, 1, 5);
        game.placeShip("destroyer", 9, 0, 9, 1);
        game.placeShip("destroyer", 8, 2, 8, 3);
        game.placeShip("battleship", 4, 6, 7, 6);
        game.placeShip("cruiser", 3, 7, 1, 9);

        System.out.println(game.getActivePlayer().getName() + " firing on B2");
        System.out.println(game.makeShot(1,1));
        System.out.println(game.getActivePlayer().getName() + " firing on A2");
        System.out.println(game.makeShot(0,1));
        System.out.println(game.getActivePlayer().getName() + " firing on J10");
        System.out.println(game.makeShot(9,9));
        System.out.println(game.getActivePlayer().getName() + " firing on J1");
        System.out.println(game.makeShot(9,0));
        System.out.println(game.getActivePlayer().getName() + " firing on J2");
        System.out.println(game.makeShot(9,1));

        System.out.println(game.getActivePlayer());
        System.out.println(game.getDefensePlayer());
        System.out.println("\n\n-----------------------\n" + game);//.activePlayer);
        //System.out.println(game.getActivePlayer().getShips()[4]);

    }
}

