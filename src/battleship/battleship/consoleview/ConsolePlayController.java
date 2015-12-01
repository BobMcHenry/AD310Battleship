package battleship.battleship.consoleview;

import battleship.model.BattleshipGame;

import java.util.Scanner;

/**
 * Created by bob on 12/1/15.
 */
public class ConsolePlayController {

    Scanner input;
    BattleshipGame bg;

    public ConsolePlayController(BattleshipGame bg){
        input = new Scanner(System.in);
        this.bg = bg;
    }

    public void fireShot(){

    }
}
