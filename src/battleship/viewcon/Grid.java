package battleship.viewcon;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by c-dub on 12/10/2015.
 */
class Grid {
    private ViewCon viewLink;
    private P1Board p1;
    private P2Board p2;
    private int boardSize;
    private String[] letters;
    private int[] numbers;

    Grid(int size) {
        this.boardSize = size;
        letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                        "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        numbers = new int[boardSize];
        for(int i = 0; i < boardSize; i++) {
            numbers[i] = i + 1;
        }
    }

    /**
     * Method to set connection to ViewCon for event handling
     * @param v
     */
    void setLink(ViewCon v) {
        this.viewLink = v;
    }

    void setP1(P1Board p) {
        this.p1 = p;
    }

    void setP2(P2Board p) {
        this.p2 = p;
    }

    /**
     * Method to build the gameboard letters next to the grid
     * @return boardLetters
     */
    VBox buildLetters() {
        // VBox to house the board Letters
        VBox boardLetters = new VBox();
        boardLetters.setId("VBox");
        boardLetters.setPrefWidth(40);
        boardLetters.setPadding(new Insets(5, 10, 0, 10));

        for(int i = 0; i < boardSize; i++) {
            Text temp = new Text(letters[i]);
            temp.setId("letters");            
            boardLetters.getChildren().addAll(temp);
        }
        return boardLetters;
    }

    /**
     * Method to build gameboard numbers under the gamegrid
     * @return boardNumbers
     */
    HBox buildNumbers() {
        // HBox to house the board numbers
        HBox boardNumbers = new HBox();
        boardNumbers.setId("HBox");
        boardNumbers.setPrefWidth(40);
        boardNumbers.setPadding(new Insets(0, 0, 0, 20));
        for(int i = 0; i < boardSize; i++) {            
            Text temp = new Text(Integer.toString(numbers[i]));
            temp.setId("numbers");
            boardNumbers.getChildren().addAll(temp);
        }        
        boardNumbers.setSpacing(23);
        return boardNumbers;
    }

    /**
     * Method to build player 1's actual buttons in the game grid and to specify event handling
     * @return gridInner
     */
    GridPane buildBoard1() {
        // String list to add ID's to all board buttons
        GridPane gridInner = new GridPane();
        int counter = 0;
        while(counter < boardSize) {
            for(int z = 0; z < boardSize; z++) {
                Button btn = new Button();
                btn.setId(letters[counter] + Integer.toString(z));
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
                            if(viewLink.isP1SetupMode) {
                                p1.handleGridBtnSetup(shipBtnId, clickedBtn);
                            } else {
                                viewLink.handleGridBtnGameP1(shipBtnId);
                            }
                        }
                    }
                });
                gridInner.add(btn, z, counter);
            }
            counter++;
        }
        return gridInner;
    }

    /**
     * Method to build player 2's game grid buttons
     * @return gridInner
     */
    GridPane buildBoard2() {
        // String list to add ID's to all board buttons
        GridPane gridInner = new GridPane();
        int counter = 0;
        while(counter < boardSize) {
            for(int z = 0; z < boardSize; z++) {
                Button btn = new Button();
                btn.setId(letters[counter] + Integer.toString(z));
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
                            if(viewLink.isP2SetupMode) {       // possible fuckup, add true back................
                                p2.handleGridBtnSetup(shipBtnId, clickedBtn);
                            } else {
                                viewLink.handleGridBtnGameP2(shipBtnId);
                            }
                        }
                    }
                });
                gridInner.add(btn, z, counter);
            }
            counter++;
        }
        return gridInner;
    }



}
