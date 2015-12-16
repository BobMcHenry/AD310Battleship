package battleship.consoleview;

import battleship.model.*;

public class TestApp {

    public static void main(String[] args){
        //Scanner input = new Scanner(System.in);
        ConsoleSetupController setup = new ConsoleSetupController();
        BattleshipGame bg = setup.runSetup();
        //BattleshipGame bg = setup.testSetup();
        //System.out.print(bg);

        ConsolePlayController play = new ConsolePlayController(bg);

        play.beginPlay();


    }
}
