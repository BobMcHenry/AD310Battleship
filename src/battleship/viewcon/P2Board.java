
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

import java.util.Map;

/**
 * P1Board class
 * @author Chris Wilson
 */
public class P2Board {
    ViewCon viewLink;
    String player;
    boolean isShipClicked;
    boolean isSetupComplete;
    GridPane gridInner;
    GridPane gridMain;
    Button nextMove;
    Button[] shipBtns;
    Label[] shipLabels;
    Label[] gameShipLabels;
    String[] ships;
    int[] sizes;
    Map<String, Integer> shipsAndSizes;
    VBox shipButtons;
    Label playerLabel;
    Label shipStatsLabel;
    Label shipButtonSize;
    Label moveStatus;
    Label playerTurn;
    String activeShip;
    String[] twoLocations; // Field for two different string locations to pass to model for validation
    boolean[] shipsValidated; // Boolean array, when all ships are validated(true) and placed, move forward
    ObservableList<Node> innerChildList;
    int shipButtonLabelCounter;
    int shipValidationIndexCounter;
    
    
    
    
    public void buildTheGrid() {
        shipValidationIndexCounter = 0;
        shipButtonLabelCounter = 0;
        isShipClicked = true;
        isSetupComplete = false;
        twoLocations = new String[]{".", "."};
        buildBoard();   // Build the whole board, primaryStage is a field and can be accessed inside this
    }   
    
     /**
     * Method to simply set the player variable for display on the board
     * @param p 
     */
    public void setPlayer(String p) {
        this.player = p;
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
        Grid g = new Grid(8); //<---------------------- board size from XML config, will be a field
        g.setLink(viewLink);
        g.setP2(this);
        // Setup the gridPane for all the children components/nodes
        gridMain = new GridPane();
        gridMain.setHgap(0);
        gridMain.setVgap(0);
        gridMain.setPadding(new Insets(220, 25, 25, 100));
        // Letters and Numbers next to and under board grid
        VBox boardLetters = g.buildLetters();
        gridMain.add(boardLetters, 1, 1);
        HBox boardNumbers = g.buildNumbers();
        gridMain.add(boardNumbers, 0, 2);
        // Add the inner grid Pane to the main grid Pane
        GridPane gridInner = g.buildBoard2();
        gridInner.setId("inner");
        gridInner.setHgap(2);
        gridInner.setVgap(2);
        gridInner.setPadding(new Insets(0, 0, 0, 0));
        gridMain.add(gridInner, 0, 1);
        innerChildList = gridInner.getChildren();
        hideBoardButtons();
        // Player name above the board buttons
        playerLabel = new Label("");
        playerLabel.setId("player");
        playerLabel.setPadding(new Insets(0, 0, 0, 100));
        playerTurn = new Label("");
        playerTurn.setId("otherLabel");
        playerTurn.setPadding(new Insets(0, 0, 0, 40));
        gridMain.add(playerLabel, 0, 0);
        gridMain.add(playerTurn, 1, 0);
        // Label to notify user to place ship
        shipStatsLabel = new Label("Select a ship");
        shipStatsLabel.setId("blueLabel");
        shipStatsLabel.setPadding(new Insets(0, 0, 0, 140));
        gridMain.add(shipStatsLabel, 2, 0);
        // VBox to hold the ship buttons for user selection of ship to place
        ships = new String[]{"BATTLESHIP", "AIRCRAFT CARRIER", "SUBMARINE", "DESTROYER", "CRUISER"}; //<---- from XML
        sizes = new int[]{4, 5, 3, 2, 3}; //<---------------------------------------------------------------- from XML
        Buttons b = new Buttons(ships, sizes);
        b.setP2(this);
        shipButtons = b.makeShipButtons2(); // returns a vbox containing all the Ship Buttons
        shipBtns = b.getShipBtns2(); // returns a Button[] containing all the actual ship buttons
        shipsAndSizes = b.getShipMap(); // returns a hashmap containing ships as key, size as value
        gridMain.add(shipButtons, 2, 1);
        gameShipLabels = new Label[ships.length]; // is an array to hold the actual labels after the button has been rem
        // Add the size label for players to notify player of ship size
        shipButtonSize = new Label("");
        shipButtonSize.setId("blueLabel");
        // Add the label to show move status for game mode
        moveStatus = new Label("");
        moveStatus.setId("otherLabel");
        gridMain.add(shipButtonSize, 2, 2);
        gridMain.add(moveStatus, 2, 3);
        shipsValidated = new boolean[ships.length];
    }
    
    
    
    /************************************************************************************************************************************/
    
    /**
     * Event handler for choosing a button for ship placement
     * @param btnId     
     */
    public void handleShipBtnSetup(String btnId) {
        // Set the active ship
        activeShip = btnId;
        // Show the board buttons because we know we are choosing a ship
        if(isShipClicked); {
            showBoardButtons();
        }
        isShipClicked = false;
        int tempSize = 0;
        for(Map.Entry<String, Integer> entry : shipsAndSizes.entrySet()) {
            if(entry.getKey().equals(btnId)) {
                tempSize = entry.getValue();
            }
        }
        shipStatsLabel.setText("Place bow of ship");
        shipButtonSize.setText("Size: " + tempSize + " squares");
        for(int i = 0; i < shipBtns.length; i++) {
            shipBtns[i].setDisable(true);
        }
    } 
    
    
    
    
    
    /**
     * Method to reset all the buttons after a ship has been validated. Removes the last validated ship button
     */
    public void resetButtons() {
        //------------------------------------- experimental, need to fix grid input first before
        //  validating and removing the individual ship buttons in the following loop
        String currentShip = activeShip;
        ObservableList<Node> ship = shipButtons.getChildren();
        int shipListSize = ship.size();
        for(int i = 0; i < shipListSize; i++) {
            ship.get(i).setDisable(false);
            String id = ship.get(i).getId();
            if (id.equals(currentShip)) {
                shipButtonLabelCounter = i;
            }
        }
        ship.remove(ship.get(shipButtonLabelCounter));
        Label temp = new Label(currentShip + "Label");
        temp.setId("sunkShips");
        shipButtons.getChildren().addAll(temp);
        temp.setVisible(false);
        gameShipLabels[shipButtonLabelCounter] = temp;
    }   
    
     /**
     * Event handler method for board button fires
     * @param btnId
     * @param btn
     */
    public void handleGridBtnSetup(String btnId, Button btn) {                       

       // If there is no String location in the head/tail array, place the parameter in index 0
       if(twoLocations[0].equals(".")) {           
           twoLocations[0] = btnId;
           shipStatsLabel.setText("Place stern");          
       }
       // If there is a String location in index 0, put the parameter in index 1
       else {           
           twoLocations[1] = btnId;          
           boolean placeIt = viewLink.placeShip(activeShip, twoLocations);           
           if(placeIt) {
                String[] buttonList = viewLink.changeButtonsSetup();
                viewLink.saveP2Defense(buttonList, activeShip);
               for(int i = 0; i < buttonList.length; i++) {
                   String tmp ="#" + buttonList[i];
                   viewLink.changeButtonsP2(tmp, activeShip);
               }
                twoLocations[0] = ".";
                twoLocations[1] = ".";           
                // Reset the buttons and remove activeShip button
                resetButtons();           
                shipButtonSize.setText("");
                shipsValidated[shipValidationIndexCounter] = true;
                shipValidationIndexCounter++;
                   // Dummy conditional - If all ships have been validated, we are going to hide this window
                   //  and switch over to the player2 setup board
                   boolean checkValid = checkValidationArray(shipsValidated);
                   if(checkValid) {
                        viewLink.isP2SetupMode = false;           
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
                                     viewLink.handlePlayerSwitchBtnP2(shipBtnId);
                                 }
                         }
                     });
                        gridMain.add(nextMove, 2, 1);
                        nextMove.setVisible(false);                       
                        viewLink.startGame(innerChildList);

             } else {
              // Reset ship label
                 //resetButtons();
                shipStatsLabel.setText("Select a ship");  
        }
           } else {               
               shipStatsLabel.setText("Invalid: re-place bow");
               twoLocations[0] = ".";
               twoLocations[1] = ".";
               shipsValidated[shipValidationIndexCounter] = false;
           }
       }
       
    }
    public boolean checkValidationArray(boolean[] ships) {
        for(int i = 0; i < ships.length; i++) {
            if(!ships[i]) {
                return false;
            }
        }
        return true;
    }

    public void showBoardButtons() {
        for(int i = 0; i < innerChildList.size(); i++) {
            Node n = innerChildList.get(i);
            n.setDisable(false);
        }
    }

    public void hideBoardButtons() {
        for(int i = 0; i < innerChildList.size(); i++) {
            Node n = innerChildList.get(i);
            n.setDisable(true);
        }
    }

    public void setNameLabel(String n) {
        playerLabel.setText(n);
    }
    
}


