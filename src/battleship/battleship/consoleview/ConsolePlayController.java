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

        System.out.println(getAttackGrid(bg.getBoard(bg.getActivePlayer())));

        System.out.println(ap + " attacking " + dp + ". Enter attack coords: ");
        String coords = input.nextLine();

        // Validate coords

        int row = ConsoleSetupController.stringToRow(coords);
        int col = ConsoleSetupController.stringToCol(coords);

        System.out.println(bg.makeShot(row, col));


    }

    String getAttackGrid(boolean[] grid){
        String out = "";
        for (int i = 0; i < 100; i++) {
            // Print Offensive Board
            if (i % 10 == 0) {
                out += "\n" + (char) (65 + i / 10);
            }
            if (!grid[i]) {
                // Status == INITIAL
                out += "  .";
            } else {
                out+="  h";
            }
        }
        return (out += "\n  01 02 03 04 05 06 07 08 09 10 \n");

    }
}
