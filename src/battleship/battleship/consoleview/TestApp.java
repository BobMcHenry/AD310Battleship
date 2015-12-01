package battleship.battleship.consoleview;

import battleship.model.*;

import java.util.Scanner;

/**
 * Created by bob on 12/1/15.
 */
public class TestApp {

    public static void main(String[] args){
        //Scanner input = new Scanner(System.in);
        ConsoleSetupController setup = new ConsoleSetupController();
        BattleshipGame bg = setup.testSetup();
        //System.out.print(bg);

        ConsolePlayController play = new ConsolePlayController(bg);

        play.beginPlay();


    }
}
