/*
 * PreBoard object. This is the starter class for the different JavaFX stages (different windows - username screen,
 *  and each players window)
 * After first username input, this class hides and calls on a new P1Board object
 */
package battleship.viewcon;

import javafx.geometry.Insets;
import javafx.application.Application;
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
    TextField userText;
    ViewCon controller;    
    P1Board p1B;
    P2Board p2B;
    
    @Override
    public void start(Stage primaryStage) {
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
        Label userName = new Label("Enter UserName:");
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
                        controller.setPlayer1(player);                                        
                        turn = true;
                        Stage stage = new Stage();
                        p1B.start(stage);
                        grid.getChildren().remove(userTextField);
                        userText = new TextField();
                        userText.setId("text-field2");
                        grid.add(userText, 0, 2);
                        hideBtn.fire();                        
                } else {
                    
                    String temp2 = userText.getText();
                        if(temp2.equals("")) {
                            player = "Player2";
                        } else {
                            player = temp2;
                        }
                        System.out.println("Player 2 should be: " + player);
                        controller.setPlayer2(player);
                        Stage stage2 = new Stage();
                        p2B.start(stage2);
                        hideBtn.fire();
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
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    
    
}
