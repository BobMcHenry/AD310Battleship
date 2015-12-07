/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.model;
import battleship.viewcon.*;
import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author foolishklown
 */
public class MainApp extends Application {
    Player player1;
    Player player2;
    BattleshipGame theGame;
    PreBoard theGamePreBoard;
    ViewCon viewConnector;
    
    // Starts the javafx stage, this is the javafx thread class
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override public void handle(WindowEvent t) {
            System.out.println("CLOSING");
            exit();
    }
});
        theGamePreBoard = new PreBoard();
        theGamePreBoard.setMainAppConnection(this);
        theGamePreBoard.start(primaryStage);
        viewConnector = theGamePreBoard.getVcon();
    }
    
    public void startBsGame(String[] names) {
        theGame = new BattleshipGame(names[0], names[1]);
        viewConnector.setGame(theGame);
    }
    
    public BattleshipGame getGame() {
        return theGame;
    }
    
    public void setConnection(ViewCon vc) {
        this.viewConnector = vc;
    }
    
    public static void main(String[] args) {
        MainApp app = new MainApp();       
        Application.launch();
        app.start(new Stage());        
    }
    
    
    
    
}
