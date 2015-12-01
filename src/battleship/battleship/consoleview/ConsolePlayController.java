package battleship.battleship.consoleview;

import battleship.model.*;

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

    public void beginPlay(){
        while( !(bg.isGameOver()) ){
            fireShot();
        }
    }

    public void fireShot(){
        String ap = bg.getActivePlayerName();
        String dp = bg.getDefensePlayerName();

        System.out.println(ap + " attacking " + dp + ". Enter attack coords: ");
        String coords = input.nextLine();

        // Validate coords

        int row = ConsoleSetupController.stringToRow(coords);
        int col = ConsoleSetupController.stringToCol(coords);

        System.out.println(bg.makeShot(row, col));


    }
}
