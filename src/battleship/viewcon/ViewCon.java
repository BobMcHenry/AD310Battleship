/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.viewcon;

import battleship.model.BattleshipGame;
import battleship.model.BattleshipMain;
import battleship.model.Status;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 *
 * @author c-dub
 */
public class ViewCon {
    private int configurationOption;
    private BattleshipMain mainConnection;
    private BattleshipGame gameConnection;
    private PreBoard preB;
    private P1Board p1Board;
    private P2Board p2Board;
    private ObservableList<Node> p1Nodes;
    private ObservableList<Node> p2Nodes;
    boolean isP1SetupMode;
    boolean isP2SetupMode;
    private String player1;
    private String player2;
    // we will manipulate the length of the following defensive arrays based upon which configuration
    //  is chosen before setup
    //public Map<String, Boolean> acMapP1, bsMapP1, crMapP1, ds1MapP1, ds2MapP1, sbMapP1, acMapP2, bsMapP2, crMapP2, ds1MapP2, ds2MapP2, sbMapP2;
    ShotProcessor shotP;

    public ViewCon() {
        shotP = new ShotProcessor();        
        isP1SetupMode = true;
        isP2SetupMode = true;
        preB = new PreBoard();
        preB.setLink(this);
        preB.buildTheGrid();        
        p1Board = new P1Board();
        p1Board.setLink(this);
        p1Board.buildTheGrid();        
        p2Board = new P2Board();
        p2Board.setLink(this);
        p2Board.buildTheGrid();        
    }   
    
    /**
     * This method connects this class with the BattleshipMain class and is the controller for the viewcon side of things
     *  The BattleshipRewrite class is direct communication between this (the controller for viewcon), and the BattleshipGame class
     *   which is the Model
     * @param b 
     */
    public void setMainConnect(BattleshipMain b) {
        this.mainConnection = b;
    }
    
    public void setShotProcessor() {
        this.shotP = new ShotProcessor();
        mainConnection.setShotProcessor(shotP);
    }
    
    public void setGameConnect(BattleshipGame g) {
        this.gameConnection = g;
    }
    
    public void setP1Label(String n) {
        p1Board.setNameLabel(n);
    }
    
    public void setP2Label(String n) {
        p2Board.setNameLabel(n);
    }
    
    public void setPlayers(String player1, String player2) {        
        mainConnection.setPlayer1(player1);
        mainConnection.setPlayer2(player2);
    }
    
    public String getPlayer1Name() {
        return player1;
    }
    
    public String getPlayer2Name() {
        return player2;
    }

    public GridPane getPreGrid() {
        return preB.getGrid();
    }

    public GridPane getP1Grid() {
        return p1Board.getGrid();
    }

    public GridPane getP2Grid() {
        return p2Board.getGrid();
    }
    
    public void exitPre() {
        mainConnection.launchGame();
    }   
    
    public boolean placeShip(String id, String[] loc) {        
        int row1 = returnRow(loc[0].substring(0, 1));        
        int row2 = returnRow(loc[1].substring(0, 1));        
        int col1 = Integer.parseInt(loc[0].substring(1, 2));        
        int col2 = Integer.parseInt(loc[1].substring(1, 2));        
        return gameConnection.placeShip(id, row1, col1, row2, col2);
    }
    
    public void saveP1Defense(String[] list, String str) {
        shotP.saveP1Defense(list, str);
    }
    
    public void saveP2Defense(String[] list, String str) {
        shotP.saveP2Defense(list, str);
    }
    
   public int returnRow(String row) {       
       int value = 0;
        switch (row) {
            case "A":
                value = 0;
                break;
            case "B":
                value = 1;
                break;
            case "C":
                value = 2;
                break;
            case "D":
                value = 3;
                break;
            case "E":
                value = 4;
                break;
            case "F":
                value = 5;
                break;
            case "G":
                value = 6;
                break;
            case "H":
                value = 7;
                break;
            case "I":
                value = 8;
                break;
            case "J":
                value = 8;
            default:
                break;
        }       
       return value;
   }
   
   public String returnStringRow(int row) {       
       String value = "";
        switch (row) {
            case 0:
                value = "A";
                break;
            case 1:
                value = "B";
                break;
            case 2:
                value = "C";
                break;
            case 3:
                value = "D";
                break;
            case 4:
                value = "E";
                break;
            case 5:
                value = "F";
                break;
            case 6:
                value = "G";
                break;
            case 7:
                value = "H";
                break;
            case 8:
                value = "I";
                break;
            case 9:
                value = "J";
            default:
                break;
        }       
       return value;
   }  
   
   public String[] changeButtonsSetup() {       
//       int[][] coords = gameConnection.getCurrentShipCoords();
//       String[] convertCoords = new String[coords.length];
//       for(int i = 0; i < coords.length; i++) {
//           String temp = returnStringRow(coords[i][0]) + String.valueOf(coords[i][1]);
//           convertCoords[i] = temp;
//       }
//       return convertCoords;
       return null;
   }
   
   public void callP2(ObservableList<Node> list) {
       p1Board.showBoardButtons();
       mainConnection.resetP1SetupButtons(list);
       mainConnection.instantiateP2();
       mainConnection.switchToP2();
   }
   
   public void startGame(ObservableList<Node> list) {       
       mainConnection.switchToP1();
       mainConnection.resetP2SetupButtons(list);
       p2Board.showBoardButtons();
   }
   
   
   public void changeButtonsP1(String btn, String ship) {
       mainConnection.changeButtonsP1(btn, ship);
       p1Board.shipStatsLabel.setText("");
   }
   
   public void changeButtonsP2(String btn, String ship) {
       mainConnection.changeButtonsP2(btn, ship);
       p2Board.shipStatsLabel.setText("");
   }
   
   public void handleGridBtnGameP1(String id) {
        boolean gameOver = gameConnection.isGameOver();
        if(gameOver == true) {
            // reset everything in view, then get the PreBoard functioning to call reset in model
            System.out.println("Adding view reset logic here soon");
        }
        int row = returnRow(id.substring(0, 1));
        int col = Integer.parseInt(id.substring(1, 2));
        Status attack = gameConnection.makeShot(row, col);
        if(attack.equals(Status.HIT)) {
            p1Board.moveStatus.setText("HIT!!! KEEP ATTACKING");
            mainConnection.processP1Hit(id);
        }        
        else if(attack.equals(Status.MISS)) {
        p1Board.moveStatus.setText("MISS");
        p1Board.hideBoardButtons();
        p1Board.nextMove.setVisible(true);
        p2Board.showBoardButtons();
        mainConnection.processP1Miss(id);
        }
        else if(attack.equals(Status.SUNK) || attack.equals(Status.INVALID)) {
            p1Board.moveStatus.setText("WASTING AMMO? GO AGAIN...");
        }
        else {
            System.out.println("something fucked up with handleGridBtn method");
        }
    }
   
   public void handleGridBtnGameP2(String id) {
        boolean gameOver = gameConnection.isGameOver();
        if(gameOver) {
            // reset everything in view
            System.out.println("Adding view reset logic here soon");
        }
        int row = returnRow(id.substring(0, 1));
        int col = Integer.parseInt(id.substring(1, 2));
        Status attack = gameConnection.makeShot(row, col);
        if(attack.equals(Status.HIT)) {
            p1Board.moveStatus.setText("HIT!!! KEEP ATTACKING");
            mainConnection.processP2Hit(id);
        }        
        else if(attack.equals(Status.MISS)) {
        p2Board.moveStatus.setText("MISS");
        p2Board.hideBoardButtons();
        p2Board.nextMove.setVisible(true);
        p1Board.showBoardButtons();
        mainConnection.processP2Miss(id);
        }
        else if(attack.equals(Status.SUNK) || attack.equals(Status.INVALID)) {
            p1Board.moveStatus.setText("WASTING AMMO? GO AGAIN...");
        }
        else {
            System.out.println("Something went horribly wrong with the handleGridBtn method");
        }
    }
    
    public void handlePlayerSwitchBtnP1(String id) {
        p1Board.nextMove.setVisible(false);
        p1Board.moveStatus.setText("");
        mainConnection.switchToP2();        
    }
    
    public void handlePlayerSwitchBtnP2(String id) {
        p2Board.nextMove.setVisible(false);
        p2Board.moveStatus.setText("");
        mainConnection.switchToP1();        
    }

    public void showSunk(String player, String ship) {
        if(player.equals("p1")) {
            ObservableList<Node> shipBs = p1Board.shipButtons.getChildren();
            for(int i = 0; i < shipBs.size(); i++) {
                if(shipBs.get(i).toString().equals(ship)) {
                    shipBs.get(i).setVisible(true);
                }
            }
            /**
            switch(ship) {
                for(int i = 0; i < p1Board.)
                case "ac" : p1Board.acL.setVisible(true);
                    break;
                case "bs" : p1Board.bsL.setVisible(true);
                    break;
                case "cr" : p1Board.crL.setVisible(true);
                    break;
                case "ds1" : p1Board.dsL.setVisible(true);
                    break;
                case "sb" : p1Board.sbL.setVisible(true);
            }
             */
        } else {
            ObservableList<Node> shipBs = p2Board.shipButtons.getChildren();
            for(int i = 0; i < shipBs.size(); i++) {
                if(shipBs.get(i).toString().equals(ship)) {
                    shipBs.get(i).setVisible(true);
                }
                /**
            switch(ship) {
                case "ac" : p2Board.acL.setVisible(true);
                    break;
                case "bs" : p2Board.bsL.setVisible(true);
                    break;
                case "cr" : p2Board.crL.setVisible(true);
                    break;
                case "ds1" : p2Board.dsL.setVisible(true);
                    break;
                case "sb" : p2Board.sbL.setVisible(true);
                 */
            }

        }
    }
    
    
      
    
}
