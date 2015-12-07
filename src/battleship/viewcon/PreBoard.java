/*
 * PreBoard object. This is the starter class for the different JavaFX stages (different windows - username screen,
 *  and each players window)
 * After first username input, this class hides and calls on a new P1Board object
 */
package battleship.viewcon;
import battleship.model.*;

import javafx.geometry.Insets;
import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;

/**
 * 
 *
 * @author c-dub
 */
public class PreBoard extends Application {
    private boolean turn; // field to determine which players name to put into which board
    private String player;       
    private Button hideBtn;
    private Button showBtn;
    private TextField userText;
    private ViewCon controller;    
    private P1Board p1B;
    private P2Board p2B;
    private BattleshipGame game;
    private String[] playerNames;
    private MainApp mainApp;
      
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    @Override public void handle(WindowEvent t) {
        System.out.println("CLOSING");
        exit();
    }
});
        playerNames = new String[2];
        System.out.println("starting, stage is: " + primaryStage);        
        turn = false;       
        p1B = new P1Board();
        p2B = new P2Board();
        controller = new ViewCon();
        controller.setp1(p1B);
        controller.setp2(p2B);
        controller.setPreB(this);        
        
        primaryStage.setTitle("Battleship setup"); //Main stage (window container)      
        //Gridpane for using rows/columns for child node placement
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(5);
        grid.setPadding(new Insets(100, 25, 25, 25));
        
        // label in window
        Text sceneTitle = new Text("Setup");
        sceneTitle.setId("setup-text");
        grid.add(sceneTitle, 0, 0, 2, 1);
        
        // label and textfield
        Label userName = new Label("Enter Player1 UserName:");
        userName.setId("user-name");
        grid.add(userName, 0, 1);        
        TextField userTextField = new TextField();
        userTextField.setId("text-field");
        grid.add(userTextField, 0, 2);
        
        // button for setup, with actionListener to save player name or default if its left blank
        Button setupBtn = new Button("Setup Board");        
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(setupBtn);
        grid.add(hbBtn, 0, 3);
        
        setupBtn.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent e) {
                // determine which player name to use to pass into which player board
                if(turn == false) {            
                    String temp1 = userTextField.getText();
                        if(temp1.equals("")) {
                            player = "Player1";
                        } else {
                            player = temp1;
                        }
                        playerNames[0] = player;
                        controller.setPlayer1(player);                       
                        turn = true;                        
                        p1B.start(new Stage());
                        grid.getChildren().remove(userTextField);
                        userText = new TextField();
                        userText.setId("text-field");
                        grid.add(userText, 0, 2);
                        userName.setText("Enter Player2 username:");
                        //hideBtn.fire();
                        //controller.hideP1();
                } else {                    
                    String temp2 = userText.getText();
                        if(temp2.equals("")) {
                            player = "Player2";
                        } else {
                            player = temp2;
                        }
                        playerNames[1] = player;
                        System.out.println("p1 is: " + playerNames[0] + ", p2 is: " + playerNames[1]);
                        controller.setPlayer2(player);                                               
                        p2B.start(new Stage());
                        hideBtn.fire();
                        //controller.hideP2();
                        //controller.showP1();
                        p1B.primeShow();
                }                        
            }
        });
        
        
        hideBtn = new Button();        
        hideBtn.setId("hideBtn");
        hideBtn.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent e) {
                primaryStage.hide();
            }
        });
        
        showBtn = new Button();
        showBtn.setId("showBtn");
        showBtn.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent e) {
                primaryStage.show();
            }
        });
        
        controller.setPreShowBtn(showBtn);
        controller.setPreHideBtn(hideBtn);
        // Add the entire scene into the main window(stage) after setting the scene dimensions
        Scene scene = new Scene(grid, 580, 200);
        primaryStage.setScene(scene);
        // Attach css stylesheet
        scene.getStylesheets().add(PreBoard.class.getResource("styles/PreBoardStyle.css").toExternalForm());
        // Show this window(stage) upon instantiation
        primaryStage.show();        
    }

    public void setLink(ViewCon v) {        
        this.controller = v;
    }   
    
    public void setBattleshipGame(BattleshipGame b) {
        this.game = b;        
    }
    
    public void setMainAppConnection(MainApp main) {
        this.mainApp = main;
    }
    
    public MainApp getMainConnection() {
        return mainApp;
    }
    
    public ViewCon getVcon() {
        return controller;
    }
    
    
   
    
    
}
