/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.viewcon;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author c-dub
 */
public class PreBoard {
    GridPane grid;   
    private boolean turn; // field to determine which players name to put into which board
    private String player;   
    private TextField userText;   
    private String[] playerNames;
    private ViewCon viewLink;
    
    
  public void buildTheGrid() {
        playerNames = new String[2];               
        turn = false;       
        
        grid = new GridPane();
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
                if(!turn) {
                    String temp1 = userTextField.getText();
                        if(temp1.equals("")) {
                            player = "Player1";
                        } else {
                            player = temp1;
                        }
                        playerNames[0] = player;                                                
                        turn = true;                       
                        grid.getChildren().remove(userTextField);
                        userText = new TextField();
                        userText.setId("text-field");
                        grid.add(userText, 0, 2);
                        userText.requestFocus();
                        userName.setText("Enter Player2 username:");                        
                } else {                    
                    String temp2 = userText.getText();
                        if(temp2.equals("")) {
                            player = "Player2";
                        } else {
                            player = temp2;
                        }
                        playerNames[1] = player;                        
                        viewLink.setPlayers(playerNames[0], playerNames[1]);                        
                        viewLink.exitPre();
                }                        
            }
        });              
    }

    /**
     *
     * @param v
     */
    public void setLink(ViewCon v) {        
        this.viewLink = v;        
    } 
    
    public GridPane getGrid() {
        return grid;
    }
    
        
}
