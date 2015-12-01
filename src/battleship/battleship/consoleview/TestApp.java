package battleship.battleship.consoleview;

import battleship.model.BattleshipGame;

/**
 * Created by bob on 12/1/15.
 */
public class TestApp {

    public static void main(String[] args){
        ConsoleSetupController setup = new ConsoleSetupController();
        BattleshipGame bg = setup.runSetup();
        System.out.print(bg);
        ConsolePlayController play = new ConsolePlayController(bg);


    }
}
