package battleship.app;
import battleship.consoleview.*;
import battleship.model.BattleshipGame;

public class MainApp {
    public static void main(String[] args){
        ConsoleSetupController setup = new ConsoleSetupController();
        BattleshipGame bg = setup.runSetup();
        ConsolePlayController play = new ConsolePlayController(bg);

        play.beginPlay();
    }
}
