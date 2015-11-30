package battleship.viewcon;

import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * P2Board class
 * @author Chris Wilson
 */
public class P2Board extends Application {
    PreBoard preB;
    Stage prime;
    ViewCon viewLink;
    String player;    
    boolean turn;
    boolean isShipClicked;
    boolean isSetupComplete;    
    Scene scene;
    GridPane gridInner;
    Button bs, ac, ds1, ds2, cr, hideBtn, showBtn;    
    VBox shipButtons;
    Label shipStatsLabel;
    Label shipButtonSize;
    String activeShip;
    String activeShipPasser;
    String[] twoLocations; // Field for two different string locations to pass to model for validation
    boolean[] shipsValidated; // Boolean array, when all ships are validated(true) and placed, move forward
    
    /**
     * Start method for javaFX Application class
     * @param primaryStage 
     */    
      @Override
    public void start(Stage primaryStage) {
        isShipClicked = true;
        isSetupComplete = false;
        twoLocations = new String[]{".", "."};        
        prime = primaryStage;       
        prime.setTitle("Player2 Board");        
        prime.setResizable(false);
        buildBoard();   // Build the whole board, primaryStage is a field and can be accessed inside this        
        prime.setScene(scene);        
    }   
    
    /**
     * Set player method used simply to set the label as the player's name
     * @param p 
     */
    public void setPlayer(String p) {
        this.player = p;
    }
    
    /**
     * Method to set the link to the ViewCon controller class
     * @param v 
     */
    public void setLink(ViewCon v) {
        System.out.println("Connection from viewcon to this P2Board established");
        this.viewLink = v;
    }
    
    /**
     * Actual build method for player2's board. Sets up the grids and children nodes
     */
    public void buildBoard() {
        //Instantiate and set this array to false until all ships are validated
        shipsValidated = new boolean[5];
        
        // Setup the gridPane for all the children components/nodes       
        GridPane gridMain = new GridPane();
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
        Label playerLabel = new Label(player);
        gridMain.add(playerLabel, 0, 0);
        
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
            Text i = new Text("I");
            i.setId("letters");
            i.setFill(Color.GREY);
            Text j = new Text("J");
            j.setId("letters");
            j.setFill(Color.GREY);
        boardLetters.getChildren().addAll(a, b, c, d, e, f, g, h, i, j);
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
            Text nine = new Text("9");
            nine.setId("numbers");
            nine.setFill(Color.GREY);
            Text ten = new Text("10");
            ten.setId("numbers");
            ten.setFill(Color.GREY);
        boardNumbers.setSpacing(23);
        boardNumbers.getChildren().addAll(one, two, three, four, five, six, seven, eight, nine, ten);
        gridMain.add(boardNumbers, 0, 2);
        
        // Label to notify user to place ship
        shipStatsLabel = new Label("Select a ship");
        shipStatsLabel.setPadding(new Insets(0, 0, 0, 65));
        gridMain.add(shipStatsLabel, 2, 0);
        // VBox to hold the ship buttons for user selection of ship to place
        shipButtons = new VBox();        
        shipButtonSize = new Label("");
        gridMain.add(shipButtonSize, 2, 1);
        
        // Actual buttons to select ship to place
        shipButtons.setSpacing(5);
        shipButtons.setId("shipBox");
        shipButtons.setPrefWidth(100);
        shipButtons.setPadding(new Insets(0, 0, 0, 140));
            bs = new Button("BattleShip");
            ac = new Button("Aircraft Carrier");
            cr = new Button("Cruiser");
            ds1 = new Button("Destroyer 1");
            ds2 = new Button("Destroyer 2");
        
        // Set sizes and event handlers for the ship buttons
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
                            handleShipBtn(shipBtnId);
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
                        handleShipBtn(shipBtnId);
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
                        handleShipBtn(shipBtnId);
                    }
                }
            });
            
        ds1.setId("DESTROYER1");
            ds1.setMinWidth(120);
            ds1.setMinHeight(30);
            ds1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // Get the object fired, cast it to a Button from Object, then get String
                    Object source = e.getSource();
                    if (source instanceof Button) { 
                        Button clickedBtn = (Button) source; 
                        String shipBtnId = clickedBtn.getId(); 
                        handleShipBtn(shipBtnId);
                    }
                }
            });
            
        ds2.setId("DESTROYER2");
            ds2.setMinWidth(120);
            ds2.setMinHeight(30);
            ds2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // Get the object fired, cast it to a Button from Object, then get String
                    Object source = e.getSource();
                    if (source instanceof Button) { 
                        Button clickedBtn = (Button) source; 
                        String shipBtnId = clickedBtn.getId(); 
                        handleShipBtn(shipBtnId);
                    }
                }
            });
        
        // Add the children node buttons to the VBox, add the VBox to the main grid
        shipButtons.getChildren().addAll(ac, bs, ds1, ds2, cr);        
        gridMain.add(shipButtons, 2, 1);
        
        // String list to add ID's to all board buttons
        String[] btnList = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        int counter = 0;
        while(counter < btnList.length) {
            for(int z = 0; z < 10; z++) {
                Button btn = new Button();
                btn.setId(btnList[counter] + Integer.toString(z));               
                btn.setMinWidth(40);
                btn.setMinHeight(40);
                btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
               Object source = e.getSource();
                    if (source instanceof Button) { 
                        Button clickedBtn = (Button) source; 
                        String shipBtnId = clickedBtn.getId();
                        // Event handler method for either setup mode or game mode
                        if(viewLink.isP2SetupMode == true) {
                        handleGridBtn(shipBtnId, clickedBtn);
                        } else {
                            viewLink.handleGridBtn2(shipBtnId, clickedBtn);
                        }
                    }
               }            
        });
                // Add all child button nodes to inner grid pane
                gridInner.add(btn, z, counter);
            }            
            counter++;
        }
        // Here we hide the grid buttons until player has selected a ship button for placement
        hideBoardButtons();
        
        viewLink.setObservableButtonList2(gridInner.getChildren());
        
        // Add dummy buttons to show/hide the board
        hideBtn = new Button();
        hideBtn.setId("hideBtn");
        hideBtn.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent e) {
                prime.hide();
            }
        });        
        showBtn = new Button();
        showBtn.setId("showBtn");
        showBtn.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent e) {
                prime.show();
            }
        });
        
        
        // Add the inner grid Pane to the main grid Pane
        gridMain.add(gridInner, 0, 1);
        // Establish a new scene consisting of the main grid Pane, set window size
        scene = new Scene(gridMain, 890, 720);
        // Attach css stylesheet
        scene.getStylesheets().add(P1Board.class.getResource("styles/PlayerBoardStyle.css").toExternalForm());       
    }
    
    /**************************************************************************************************************************/
    
    /**
     * Event handler for choosing a button for ship placement
     * @param btn 
     */
    public void handleShipBtn(String btn) {
        // Set the active ship
        activeShip = btn;
        activeShipPasser = activeShip;
        // Show the board buttons because we know we are choosing a ship
        if(isShipClicked == true); {
        showBoardButtons();        
        }
        isShipClicked = false;
            // Check switch statement, disable all buttons and change labels
            switch(btn) {
            case "AIRCRAFT CARRIER" :   shipStatsLabel.setText("Place bow of ship");
                                        shipButtonSize.setText("Size: 5 squares");
                                        ac.setDisable(true);                                        
                                        bs.setDisable(true);
                                        cr.setDisable(true);
                                        ds1.setDisable(true);
                                        ds2.setDisable(true);
                                        break;
                 
                    case "BATTLESHIP" : shipStatsLabel.setText("Place bow of ship");
                                        shipButtonSize.setText("Size: 4 squares");
                                        bs.setDisable(true);                                 
                                        ac.setDisable(true);
                                        cr.setDisable(true);
                                        ds1.setDisable(true);
                                        ds2.setDisable(true);
                                        break;
            
                    case "CRUISER" :    shipStatsLabel.setText("Place bow of ship");
                                        shipButtonSize.setText("Size: 3 squares");
                                        cr.setDisable(true);                                        
                                        ac.setDisable(true);
                                        bs.setDisable(true);
                                        ds1.setDisable(true);
                                        ds2.setDisable(true);
                                        break;
                                        
                    case "DESTROYER1" : shipStatsLabel.setText("Place bow of ship");
                                        shipButtonSize.setText("Size: 2 squares");
                                        ds1.setDisable(true);                                       
                                        ac.setDisable(true);
                                        cr.setDisable(true);
                                        bs.setDisable(true);
                                        ds2.setDisable(true);
                                        break;
                                        
                    case "DESTROYER2" : shipStatsLabel.setText("Place bow of ship");
                                        shipButtonSize.setText("Size: 2 squares");
                                        ds2.setDisable(true);                                        
                                        ac.setDisable(true);
                                        cr.setDisable(true);
                                        ds1.setDisable(true);
                                        bs.setDisable(true);
                                        break;
            default: break;
        }
    }
    
    /**
     * Method to hide all board buttons so player cant click on grid until
     *  they have selected a ship button for placement
     */
    public void hideBoardButtons() {
        ObservableList<Node> list = gridInner.getChildren();
        for(int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            n.setDisable(true);
        }
    }
    
    /**
     * Method to show all board buttons after a player has selected a ship button
     *  for ship placement
     */
    public void showBoardButtons() {
        ObservableList<Node> list = gridInner.getChildren();
        for(int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            n.setDisable(false);
        }
    } 
    
    /**
     * Method to reset all the buttons after a ship has been validated. Removes the last validated ship button
     */
    public void resetButtons() {
        if(activeShip.equals("AIRCRAFT CARRIER")) {
            bs.setDisable(false);
            cr.setDisable(false);
            ds1.setDisable(false);
            ds2.setDisable(false);
            shipButtons.getChildren().remove(ac);            
        }
        if(activeShip.equals("BATTLESHIP")) {
            ac.setDisable(false);
            cr.setDisable(false);
            ds1.setDisable(false);
            ds2.setDisable(false);
            shipButtons.getChildren().remove(bs);            
        }
        if(activeShip.equals("CRUISER")) {
            bs.setDisable(false);
            ac.setDisable(false);
            ds1.setDisable(false);
            ds2.setDisable(false);
            shipButtons.getChildren().remove(cr);            
        }
        if(activeShip.equals("DESTROYER1")) {
            bs.setDisable(false);
            cr.setDisable(false);
            ac.setDisable(false);
            ds2.setDisable(false);
            shipButtons.getChildren().remove(ds1);            
        }
        if(activeShip.equals("DESTROYER2")) {
            bs.setDisable(false);
            cr.setDisable(false);
            ds1.setDisable(false);
            ac.setDisable(false);
            shipButtons.getChildren().remove(ds2);            
        }
        hideBoardButtons();
        isShipClicked = true;
    }
    
    /**
     * Method to show the entire stage (the whole window)
     */
    public void primeShow() {
        prime.show();
    }
    
    /**
     * Event handler method for board button fires
     * @param btnId
     * @param btn
     */
    public void handleGridBtn(String btnId, Button btn) {        
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
                            
           case "DESTROYER1" : index = 3;
                               size = 2;
                               break;
                               
           case "DESTROYER2" : index = 4;
                               size = 2;
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
           int col1 = Integer.parseInt(twoLocations[0].substring(1, 2));
           int col2 = Integer.parseInt(twoLocations[1].substring(1, 2));
           if(activeShip.equals("DESTROYER1") || activeShip.equals("DESTROYER2")) {
            activeShipPasser = "DESTROYER";            
            }
           boolean placeIt = viewLink.placeShip(activeShipPasser, twoLocations[0].substring(0, 1), col1,  twoLocations[1].substring(0, 1), col2);
           System.out.println(placeIt);
           if(placeIt == true) {
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
           viewLink.isP2SetupMode = false;
           viewLink.resetP2SideElements();
           hideBtn.fire();
           //int[][] finalShipList = viewLink.getShipCoords();
           //System.out.println(finalShipList);
           viewLink.showP1();                  
        } else {
         // Reset ship label
            //resetButtons();
           shipStatsLabel.setText("Select a ship");  
        }
           
           } else {
               shipButtonSize.setText("Invalid placement");
               shipStatsLabel.setText("Re-place bow");
               twoLocations[0] = ".";
               twoLocations[1] = ".";
               shipsValidated[index] = false;
           }
       }
       
    }
        
    
    
    
}

