package battleship.consoleview;

import battleship.model.*;

import java.io.FileNotFoundException;

public class TestApp {

    public static void main(String[] args) throws FileNotFoundException {
        BattleshipConfig bc = new BattleshipConfig("/home/bob/IdeaProjects/AD310Battleship/src/battleship/model/battleship.conf");
        //Scanner input = new Scanner(System.in);
        ConsoleSetupController setup = new ConsoleSetupController();
        //BattleshipGame bg = setup.runSetup();
        BattleshipGame bg = setup.testSetup();
        //System.out.print(bg);

        ConsolePlayController play = new ConsolePlayController(bg);

        play.beginPlay();


    }
}
