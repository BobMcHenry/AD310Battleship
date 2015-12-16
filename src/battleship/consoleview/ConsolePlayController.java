package battleship.consoleview;

import battleship.model.*;

import java.util.Scanner;

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
        System.out.println("GAME OVER! " + bg.getActivePlayerName() + " Wins!");
    }

    public void fireShot(){
        String ap = bg.getActivePlayerName();
        String dp = bg.getDefensePlayerName();

        System.out.println(getAttackGrid(bg.getBoard(bg.getActivePlayer())));

        System.out.println(ap + " attacking " + dp + ". Enter attack coordinates: ");
        String coords = input.nextLine().toUpperCase();

        // Validate coords //Temporarily using input validation from setup.
        while(!ConsoleSetupController.validateCoords(coords)){
            System.out.println("INVALID COORDINATES");
            System.out.println("Enter attack coordinates: ");
            coords = input.nextLine().toUpperCase();
        }
        int row = ConsoleSetupController.stringToRow(coords);
        int col = ConsoleSetupController.stringToCol(coords);

        System.out.println(bg.makeShot(row, col));


    }

    String getAttackGrid(Status[] grid){
        String out = "";
        for (int i = 0; i < 100; i++) {
            // Print Offensive Board
            if (i % 10 == 0) {
                out += "\n" + (char) (65 + i / 10);
            }
            if (grid[i] == Status.INITIAL) {
                // Status == INITIAL
                out += "  .";
            } else if (grid[i] == Status.HIT){
                    out+= "  H";
                } else {
                out += "  M";
            }

        }
        out += "\n  01 02 03 04 05 06 07 08 09 10 \n";
        return out;

    }
}
