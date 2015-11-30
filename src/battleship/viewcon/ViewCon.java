package battleship.viewcon;
import battleship.model.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import javafx.scene.control.Button;

/**
 * ViewController class. Communicates between model and view/control objects
 * @author Chris Wilson
 */
public class ViewCon {
    
   private P1Board p1B;
   private P2Board p2B;  
   private PreBoard preB;   
   private String player1;
   private String player2;
   private Button preShow;
   private Button preHide;
   private BattleshipGame gameConnect; //this is our connection directly to BattleshipGame class   
   boolean isP1SetupMode;
   boolean isP2SetupMode;
   private MainApp main;
   private ObservableList<Node> buttonList1;
   private ObservableList<Node> buttonList2;
   
   
    /**
     * Constructor, sets players setup mode booleans to true
     */
    public ViewCon() {
       isP1SetupMode = true;
       isP2SetupMode = true;       
   }
   
    /**
     * Method to set a link to the BattleshipGame object
     * @param bg
     */
    public void setGame(BattleshipGame bg) {        
       this.gameConnect = bg;       
   }
    
    /**
     * Method to call on the MainApp's startBsGame, which instantiates a BattleshipGame in the main app 
     */
    public void startGame(String[] players) {        
        main.startBsGame(players);        
    }

    /**
     * Method to set connection to the MainApp class in the model. Asks PreBoard class
     *  for its reference to the MainApp
     * @param m
     */
    public void setMain() {
        this.main = preB.getMainConnection();
    }
   
    /**
     * Method to set a connection to the PreBoard class on the view/controller side
     * @param p
     */
    public void setPreB(PreBoard p) {        
       this.preB = p;
   }
   
    /**
     * Getter method for returning the instance of the PreBoard
     * @return
     */
    public PreBoard getPreB() {
       return preB;
   }
   
    /**
     * Method to connect to the PreBoard show button
     * @param s
     */
    public void setPreShowBtn(Button s) {
       this.preShow = s;
   }   
   
    /**
     * Method to connect to the PreBoard hide button
     * @param s
     */
    public void setPreHideBtn(Button s) {
       this.preHide = s;
   }   
   
    /**
     * Get method to return an instance of this ViewCon
     * @return
     */
    public ViewCon getCon() {
       return this;
   }
  
    /**
     * Method to set a connection to the P1Board class, and then to set a link in the P1Board class 
     *  directly to this ViewCon instance
     * @param p1
     */
    public void setp1(P1Board p1) {        
       this.p1B = p1;
       p1B.setLink(this);
   }
   
    /**
     * Method to set a connection to the P2Board class, and then to set a link in the P2Board class
     *  directly to this ViewCon instance
     * @param p2
     */
    public void setp2(P2Board p2) {        
       this.p2B = p2;
       p2B.setLink(this);
   }   
  
    /**
     * Method called inside the PreBoard class to set the player name field directly inside this class
     *  and then to call the setPlayer method inside the P1Board class, which sets the label name up properly
     * @param p
     */
    public void setPlayer1(String p) {
      this.player1 = p;
      p1B.setPlayer(player1);
   }  
   
    /**
     * Method called inside the PreBoard class to set the player name field directly inside this class
     *  and then to call the setPlayer method inside the P2Board class, which sets the label name up properly
     * @param p
     */
    public void setPlayer2(String p) {
       this.player2 = p;
       p2B.setPlayer(player2);
   }
   
    /**
     * Method to set the connection between the P1Board class and this ViewCon instance
     */
    public void setP1BoardLink() {
       p1B.setLink(this);
   }
   
    /**
     * Method to set the connection between the P2Board class and this ViewCon instance
     */
    public void setP2BoardLink() {
       p2B.setLink(this);
   }
   
    /**
     * Method to show the PreBoard
     */
    public void preShow() {
       System.out.println(preShow);
       preShow.fire();
   }
   
    /**
     * Method to hide the PreBoard
     */
    public void preHide() {
       preHide.fire();
   } 

    /**
     * Method to set the connection directly to the BattleshipGame class in the model, which is the communication module
     *  between the view/controller and the model
     * @param b
     */
   public void setBattleshipGame(BattleshipGame b) {
       this.gameConnect = b;
   }
   
    /**
     * Get method to return the connection the the instance of the BattleshipGame class
     * @return gameConnect
     */
    public BattleshipGame getBattleshipGame() {
       return gameConnect;       
   }
    
    public void setObservableButtonList1(ObservableList<Node> l) {
        this.buttonList1 = l;
    }
    
    public void setObservableButtonList2(ObservableList<Node> l) {
        this.buttonList2 = l;
    }
   
    /**
     * Method to directly hit the show dummy button in P1Board for showing the stage
     */
    public void showP1() {
       p1B.showBtn.fire();
   }
   
    /**
     * Method to directly hit the hide dummy button in P1Board for hiding the stage
     */
    public void hideP1() {
       p1B.hideBtn.fire();
   }
   
    /**
     * Method to directly hit the show dummy button in P2Board for showing the stage
     */
    public void showP2() {
       p2B.showBtn.fire();
   }
   
    /**
     * Method to directly hit the hide dummy button in P2Board for hiding the stage
     */
    public void hideP2() {
       p2B.hideBtn.fire();
   }
   
    /**
     * Method to show the stage in P2Board for setup
     */
    public void prime2show() {
       p2B.primeShow();
   }
   
   /**
    * //STUB
    * @param id
    * @param btn 
    */
   public void handleGridBtn1(String id, Button btn) {
       System.out.println("From board1");
       System.out.println("id is: " + id + " , button is: " + btn);
       this.hideP1();
       this.showP2();
   }

   public void handleGridBtn2(String id, Button btn) {
       System.out.println("From board2");
       System.out.println("id is: " + id + " , button is: " + btn);
       this.hideP2();
       this.showP1();
   }
   
   public int[][] getShipCoords() {
       return gameConnect.getShipCoords(gameConnect.getActivePlayer());
   }
   
   public void resetP1SideElements() {
       p1B.shipStatsLabel.setText("Sunken Enemy ships: ");
       p1B.showBoardButtons();
       p1B.shipButtonSize.setText("");
   }
   
   public void resetP2SideElements() {
       p2B.shipStatsLabel.setText("Sunken Enemy ships: ");
       p2B.showBoardButtons();
       p2B.shipButtonSize.setText("");
   }
   
   
   
   
   
   /**
    * This is our method to communicate directly with the BattleshipGame class, and to talk to the view/controller and update buttons on 
    * the players grids for game play
    * @param s
    * @param headRow
    * @param headColumn
    * @param tailRow
    * @param tailColumn
    * @return boolean value for ship validation
    */
   public boolean placeShip(String s, String headRow, int headColumn, String tailRow, int tailColumn) {       
       int hr = 0;
       int tr = 0;
       if(headRow.equals("A")) {
           hr = 0;
       } else if(headRow.equals("B")) {
           hr = 1;
       } else if(headRow.equals("C")) {
           hr = 2;
       } else if(headRow.equals("D")) {
           hr = 3;
       } else if(headRow.equals("E")) {
           hr = 4;
       } else if(headRow.equals("F")) {
           hr = 5;
       } else if(headRow.equals("G")) {
           hr = 6;
       } else if(headRow.equals("H")) {
           hr = 7;
       } else if(headRow.equals("I")) {
           hr = 8;
       } else if(headRow.equals("J")) {
           hr = 9;
       }
       
       if(tailRow.equals("A")) {
           tr = 0;
       } else if(tailRow.equals("B")) {
           tr = 1;
       } else if(tailRow.equals("C")) {
           tr = 2;
       } else if(tailRow.equals("D")) {
           tr = 3;
       } else if(tailRow.equals("E")) {
           tr = 4;
       } else if(tailRow.equals("F")) {
           tr = 5;
       } else if(tailRow.equals("G")) {
           tr = 6;
       } else if(tailRow.equals("H")) {
           tr = 7;
       } else if(tailRow.equals("I")) {
           tr = 8;
       } else if(tailRow.equals("J")) {
           tr = 9;
       }       
       return gameConnect.placeShip(s, hr, headColumn, tr, tailColumn);
   }
    
}