
package battleship.viewcon;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * P1Board class
 * @author Chris Wilson
 */
public class P1Board {
    ViewCon viewLink;
    String player;    
    boolean isShipClicked;
    boolean isSetupComplete;   
    public GridPane gridInner;
    GridPane gridMain;
    Button bs, ac, ds, sb, cr, hideBtn, showBtn, nextMove;
    Label bsL, acL, dsL, sbL, crL;
    VBox shipButtons;
    Label playerLabel;
    Label shipStatsLabel;
    Label shipButtonSize;
    Label moveStatus;
    Label playerTurn;
    String activeShip;    
    String[] twoLocations; // Field for two different string locations to pass to model for validation
    boolean[] shipsValidated; // Boolean array, when all ships are validated(true) and placed, move forward
    ObservableList<Node> list;
    
    
    
    
    public void buildTheGrid() {        
        isShipClicked = true;
        isSetupComplete = false;
        twoLocations = new String[]{".", "."};       
        buildBoard();   // Build the whole board, primaryStage is a field and can be accessed inside this               
    } 
    
    /**
     * Method to the the link to the ViewCon controller class
     * @param v 
     */
    public void setLink(ViewCon v) {        
        this.viewLink = v;
    }
    
    public GridPane getGrid() {
        return gridMain;
    }
    
    /**
     * Actual build method for player1's board. Sets up the grids and children nodes
     */
    public void buildBoard() {
        //Instantiate and set this array to false until all ships are validated
        shipsValidated = new boolean[5];
        
        // Setup the gridPane for all the children components/nodes       
        gridMain = new GridPane();
        gridMain.setHgap(0);
        gridMain.setVgap(0);
        gridMain.setPadding(new Insets(220, 25, 25, 100));
        
        // Setup inner gridPane to house the board buttons
        gridInner = new GridPane();
        gridInner.setId("inner");
        gridInner.setHgap(2);
        gridInner.setVgap(2);
        gridInner.setPadding(new Insets(0, 0, 0, 0));
        
        // Player name above the board buttons       
        playerLabel = new Label("");
        playerLabel.setId("player");
        playerLabel.setPadding(new Insets(0, 0, 0, 100));
        playerTurn = new Label("");
        playerTurn.setId("otherLabel");
        playerTurn.setPadding(new Insets(0, 0, 0, 40));
        gridMain.add(playerLabel, 0, 0);
        gridMain.add(playerTurn, 1, 0);
        
        // VBox to house the board Letters
        VBox boardLetters = new VBox();
        boardLetters.setId("VBox");
        boardLetters.setPrefWidth(40);       
        boardLetters.setPadding(new Insets(5, 10, 0, 10));
            Text a = new Text("A");
            a.setId("letters");
            a.setFill(Color.GREY);
            Text b = new Text("B");
            b.setId("letters");
            b.setFill(Color.GREY);
            Text c = new Text("C");
            c.setId("letters");
            c.setFill(Color.GREY);
            Text d = new Text("D");
            d.setId("letters");
            d.setFill(Color.GREY);
            Text e = new Text("E");
            e.setId("letters");
            e.setFill(Color.GREY);
            Text f = new Text("F");
            f.setId("letters");
            f.setFill(Color.GREY);
            Text g = new Text("G");
            g.setId("letters");
            g.setFill(Color.GREY);
            Text h = new Text("H");
            h.setId("letters");
            h.setFill(Color.GREY);            
        boardLetters.getChildren().addAll(a, b, c, d, e, f, g, h);
        gridMain.add(boardLetters, 1, 1);
        
        // HBox to house the board numbers
        HBox boardNumbers = new HBox();        
        boardNumbers.setId("HBox");
        boardNumbers.setPrefWidth(40);
        boardNumbers.setPadding(new Insets(0, 0, 0, 20));
            Text one = new Text("1");
            one.setId("numbers");
            one.setFill(Color.GREY);
            Text two = new Text("2");
            two.setId("numbers");
            two.setFill(Color.GREY);
            Text three = new Text("3");
            three.setId("numbers");
            three.setFill(Color.GREY);
            Text four = new Text("4");
            four.setId("numbers");
            four.setFill(Color.GREY);
            Text five = new Text("5");
            five.setId("numbers");
            five.setFill(Color.GREY);
            Text six = new Text("6");
            six.setId("numbers");
            six.setFill(Color.GREY);
            Text seven = new Text("7");
            seven.setId("numbers");
            seven.setFill(Color.GREY);
            Text eight = new Text("8");
            eight.setId("numbers");
            eight.setFill(Color.GREY);           
        boardNumbers.setSpacing(23);
        boardNumbers.getChildren().addAll(one, two, three, four, five, six, seven, eight);
        gridMain.add(boardNumbers, 0, 2);
        
        // Label to notify user to place ship
        shipStatsLabel = new Label("Select a ship");
        shipStatsLabel.setId("blueLabel");
        shipStatsLabel.setPadding(new Insets(0, 0, 0, 140));
        gridMain.add(shipStatsLabel, 2, 0);
        // VBox to hold the ship buttons for user selection of ship to place
        shipButtons = new VBox();        
        shipButtonSize = new Label("");
        shipButtonSize.setId("blueLabel");
        moveStatus = new Label("");
        moveStatus.setId("otherLabel");
        gridMain.add(shipButtonSize, 2, 2);
        gridMain.add(moveStatus, 2, 3);
        
        
        // Actual buttons to select ship to place
        shipButtons.setSpacing(5);
        shipButtons.setId("shipBox");
        shipButtons.setPrefWidth(100);
        shipButtons.setPadding(new Insets(0, 0, 0, 200));
            bs = new Button("BattleShip");
            ac = new Button("Aircraft Carrier");
            cr = new Button("Cruiser");
            ds = new Button("Destroyer");
            sb = new Button("Submarine");
        
        // Set event handlers
        bs.setId("BATTLESHIP");
            bs.setMinWidth(120);
            bs.setMinHeight(30);
            bs.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                        // Get the object fired, cast it to a Button from Object, then get String
                        Object source = e.getSource();
                        if (source instanceof Button) { // This should always be true because its a Button fire
                            Button clickedBtn = (Button) source; 
                            String shipBtnId = clickedBtn.getId(); 
                            handleShipBtnSetup(shipBtnId);
                        }
                   }                
            });
            
        ac.setId("AIRCRAFT CARRIER");
            ac.setMinWidth(120);
            ac.setMinHeight(30);
            ac.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // Get the object fired, cast it to a Button from Object, then get String
                    Object source = e.getSource();                    
                    if (source instanceof Button) { 
                        Button clickedBtn = (Button) source;                        
                        String shipBtnId = clickedBtn.getId(); 
                        handleShipBtnSetup(shipBtnId);
                    }
                    
                }
            });
            
        cr.setId("CRUISER");
            cr.setMinWidth(120);
            cr.setMinHeight(30);
            cr.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // Get the object fired, cast it to a Button from Object, then get String
                    Object source = e.getSource();
                    if (source instanceof Button) { 
                        Button clickedBtn = (Button) source; 
                        String shipBtnId = clickedBtn.getId(); 
                        handleShipBtnSetup(shipBtnId);
                    }
                }
            });
            
        ds.setId("DESTROYER");
            ds.setMinWidth(120);
            ds.setMinHeight(30);
            ds.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // Get the object fired, cast it to a Button from Object, then get String
                    Object source = e.getSource();
                    if (source instanceof Button) { 
                        Button clickedBtn = (Button) source; 
                        String shipBtnId = clickedBtn.getId();                        
                        handleShipBtnSetup(shipBtnId);
                    }
                }
            });
            
        sb.setId("SUBMARINE");
            sb.setMinWidth(120);
            sb.setMinHeight(30);
            sb.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // Get the object fired, cast it to a Button from Object, then get String
                    Object source = e.getSource();
                    if (source instanceof Button) { 
                        Button clickedBtn = (Button) source; 
                        String shipBtnId = clickedBtn.getId();                        
                        handleShipBtnSetup(shipBtnId);
                    }
                }
            });
        
        // Add the children node buttons to the VBox, add the VBox to the main grid
        shipButtons.getChildren().addAll(ac, bs, ds, sb, cr);        
        gridMain.add(shipButtons, 2, 1);
        
        // String list to add ID's to all board buttons
        String[] btnList = new String[] {"A", "B", "C", "D", "E", "F", "G", "H"};
        int counter = 0;
        while(counter < btnList.length) {
            for(int z = 0; z < 8; z++) {
                Button btn = new Button();
                btn.setId(btnList[counter] + Integer.toString(z));
                btn.setMaxWidth(40);
                btn.setMaxHeight(40);
                btn.setMinWidth(40);
                btn.setMinHeight(40);
                btn.setPrefWidth(40);
                btn.setPrefHeight(40);                               
                btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
               Object source = e.getSource();               
                    if (source instanceof Button) { 
                        Button clickedBtn = (Button) source;                        
                        String shipBtnId = clickedBtn.getId();
                        // Event handler method for either setup mode or game mode
                        if(viewLink.isP1SetupMode) {       // possible fuckup, add true back................
                        handleGridBtnSetup(shipBtnId, clickedBtn);
                        } else {                            
                            viewLink.handleGridBtnGameP1(shipBtnId);
                        }
                    }
               }            
        });
                // Add all child button nodes to inner grid pane
                gridInner.add(btn, z, counter);
            }            
            counter++;
        }      
        
        // Add the inner grid Pane to the main grid Pane
        gridMain.add(gridInner, 0, 1);
        hideBoardButtons();
    }
    
    
    
    /************************************************************************************************************************************/
    
    /**
     * Event handler for choosing a button for ship placement
     * @param btnId
     * @param btn
     */
    public void handleShipBtnSetup(String btnId) {        
        // Set the active ship
        activeShip = btnId;
        //activeShipPasser = activeShip;
        // Show the board buttons because we know we are choosing a ship
        if(isShipClicked == true); {
        showBoardButtons();        
        }
        isShipClicked = false;
            // Check switch statement, disable all buttons and change labels
            switch(btnId) {
            case "AIRCRAFT CARRIER" :   shipStatsLabel.setText("Place bow of ship");
                                        shipButtonSize.setText("Size: 5 squares");
                                        ac.setDisable(true);                                        
                                        bs.setDisable(true);
                                        cr.setDisable(true);
                                        ds.setDisable(true);
                                        sb.setDisable(true);
                                        break;
                 
                    case "BATTLESHIP" : shipStatsLabel.setText("Place bow of ship");
                                        shipButtonSize.setText("Size: 4 squares");
                                        bs.setDisable(true);                                 
                                        ac.setDisable(true);
                                        cr.setDisable(true);
                                        ds.setDisable(true);
                                        sb.setDisable(true);
                                        break;
            
                    case "CRUISER" :    shipStatsLabel.setText("Place bow of ship");
                                        shipButtonSize.setText("Size: 3 squares");
                                        cr.setDisable(true);                                        
                                        ac.setDisable(true);
                                        bs.setDisable(true);
                                        ds.setDisable(true);
                                        sb.setDisable(true);
                                        break;
                                        
                    case "DESTROYER" : shipStatsLabel.setText("Place bow of ship");
                                        shipButtonSize.setText("Size: 2 squares");
                                        ds.setDisable(true);                                       
                                        ac.setDisable(true);
                                        cr.setDisable(true);
                                        bs.setDisable(true);
                                        sb.setDisable(true);
                                        break;
                                        
                    case "SUBMARINE" : shipStatsLabel.setText("Place bow of ship");
                                        shipButtonSize.setText("Size: 3 squares");
                                        ds.setDisable(true);                                        
                                        ac.setDisable(true);
                                        cr.setDisable(true);
                                        sb.setDisable(true);
                                        bs.setDisable(true);
                                        break;
            default: break;
        }
    } 
    
    
    
    
    
    /**
     * Method to reset all the buttons after a ship has been validated. Removes the last validated ship button
     */
    public void resetButtons() {
        if(activeShip.equals("AIRCRAFT CARRIER")) {
            bs.setDisable(false);
            cr.setDisable(false);
            ds.setDisable(false);
            sb.setDisable(false);            
            shipButtons.getChildren().remove(ac);
            acL = new Label("Carrier");
            acL.setId("sunkShips");
            shipButtons.getChildren().addAll(acL);
            acL.setVisible(false);
        }
        if(activeShip.equals("BATTLESHIP")) {
            ac.setDisable(false);
            cr.setDisable(false);
            ds.setDisable(false);
            sb.setDisable(false);
            shipButtons.getChildren().remove(bs);
            bsL = new Label("Battleship");
            bsL.setId("sunkShips");
            shipButtons.getChildren().addAll(bsL);
            bsL.setVisible(false);
        }
        if(activeShip.equals("CRUISER")) {
            bs.setDisable(false);
            ac.setDisable(false);
            ds.setDisable(false);
            sb.setDisable(false);
            shipButtons.getChildren().remove(cr);
            crL = new Label("Cruiser");
            crL.setId("sunkShips");
            shipButtons.getChildren().addAll(crL);
            crL.setVisible(false);
        }
        if(activeShip.equals("DESTROYER")) {
            bs.setDisable(false);
            cr.setDisable(false);
            ac.setDisable(false);
            sb.setDisable(false);
            shipButtons.getChildren().remove(ds);
            dsL = new Label("Destroyer");
            dsL.setId("sunkShips");
            shipButtons.getChildren().addAll(dsL);
            dsL.setVisible(false);
        }
        if(activeShip.equals("SUBMARINE")) {
            bs.setDisable(false);
            cr.setDisable(false);
            ds.setDisable(false);
            ac.setDisable(false);
            shipButtons.getChildren().remove(sb);
            sbL = new Label("Submarine");
            sbL.setId("sunkShips");
            shipButtons.getChildren().addAll(sbL);
            sbL.setVisible(false);
        }
        hideBoardButtons();        
        isShipClicked = true;
    }   
    
     /**
     * Event handler method for board button fires
     * @param btnId
     * @param btn
     */
    public void handleGridBtnSetup(String btnId, Button btn) {                       
        int index = 0;
        int size;
        switch(activeShip) {
           // Establish placement of each ship in the validation array
           case "AIRCRAFT CARRIER" : index = 0;
                                     size = 5;
                                     break;
                                     
           case "BATTLESHIP" : index = 1;
                               size = 4;
                               break;
                               
           case "CRUISER" : index = 2;
                            size = 3;
                            break;
                            
           case "DESTROYER" : index = 3;
                               size = 2;
                               break;
                               
           case "SUBMARINE" : index = 4;
                               size = 3;
                               break;           
       }
       // If there is no String location in the head/tail array, place the parameter in index 0
       if(twoLocations[0].equals(".")) {           
           twoLocations[0] = btnId;                             
           shipStatsLabel.setText("Place stern");          
       }
       // If there is a String location in index 0, put the parameter in index 1
       else {           
           twoLocations[1] = btnId;           
           boolean placeIt = viewLink.placeShip(activeShip, twoLocations);           
           if(placeIt == true) {               
               String[] buttonList = viewLink.changeButtonsSetup();               
               viewLink.saveP1Defense(buttonList, activeShip);
               for(int i = 0; i < buttonList.length; i++) {                   
                   String tmp ="#" + buttonList[i];
                   viewLink.changeButtonsP1(tmp, activeShip);
               }
                twoLocations[0] = ".";
                twoLocations[1] = ".";           
                // Reset the buttons and remove activeShip button
                resetButtons();           
                shipButtonSize.setText("");
                shipsValidated[index] = true;           
                // Dummy conditional - If all ships have been validated, we are going to hide this window
                //  and switch over to the player2 setup board
                if(shipsValidated[0] == true && shipsValidated[1] == true && shipsValidated[2] == true
                    && shipsValidated[3] == true && shipsValidated[4] == true) {
                     viewLink.isP1SetupMode = false;           
                     gridMain.getChildren().remove(shipButtonSize);
                     shipStatsLabel.setText("Sunken ships:");
                     nextMove = new Button("Switch Players");
                     nextMove.setId("moveBtn");
                     nextMove.setOnAction(new EventHandler<ActionEvent>() { 
                      @Override
                      public void handle(ActionEvent e) {
                         Object source = e.getSource();
                              if (source instanceof Button) { 
                                  Button clickedBtn = (Button) source; 
                                  String shipBtnId = clickedBtn.getId();
                                  viewLink.handlePlayerSwitchBtnP1(shipBtnId);
                              }
                      }
                  });
                     gridMain.add(nextMove, 2, 1);
                     nextMove.setVisible(false);                     
                     viewLink.callP2(list);
            } else {
         // Reset ship label
            //resetButtons();
           shipStatsLabel.setText("Select a ship");  
        }
           } else {               
               shipStatsLabel.setText("Invalid: re-place bow");
               twoLocations[0] = ".";
               twoLocations[1] = ".";
               shipsValidated[index] = false;
           }           
       }
       
    }  
    
    

    public void showBoardButtons() {
        list = gridInner.getChildren();
        for(int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            n.setDisable(false);
        }
    }
    
    public void hideBoardButtons() {
        list = gridInner.getChildren();
        for(int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            n.setDisable(true);
        }
    }
    
    public void setNameLabel(String n) {        
        playerLabel.setText(n);
    }
    
}


