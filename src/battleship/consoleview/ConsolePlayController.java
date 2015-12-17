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

        String shipHit = String.valueOf(bg.getShipFromLocation(bg.getActivePlayer(), row, col));
        Status status = bg.makeShot(row, col);
        if (status != Status.SUNK) {
            System.out.println(status);
        } else {
            System.out.println(shipHit + " " + status);
        }


    }

    String getAttackGrid(Status[] grid){
        String out = "";
        for (int i = 0; i < bg.getBoardSize()*bg.getBoardSize(); i++) {
            // Print Offensive Board
            if (i % bg.getBoardSize() == 0) {
                out += "\n" + (char) (65 + i / bg.getBoardSize());
            }
            if (grid[i]==Status.INITIAL) {
                // Status == INITIAL
                out += "  .";
            } else {
                if (grid[i] == Status.HIT){
                    out+= "  H";
                } else if (grid[i] == Status.SUNK){
                    out+= "  S";
                } else {
                    out+= "  M";
                }
            }
        }
        String nums = "\n ";
        for (int i = 1; i <= bg.getBoardSize(); i++){
            if (i < 10){
                nums += " 0" + i;
            } else {
                nums += " " + i;
            }
        }
        out += nums + "\n";
        return out;

    }
}
