/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.viewcon;
import battleship.model.*;

import javafx.scene.control.Button;

/**
 *
 * @author foolishklown
 */
public class ViewCon {
    
   private P1Board p1B;
   private P2Board p2B;  
   private PreBoard preB;   
   private String player1;
   private String player2;
   private Button preShow;
   private Button preHide;
   private BattleshipGame gameConnect; //this is our connection directly to BattleshipGame class for
   // direct communication
   boolean isP1SetupMode;
   boolean isP2SetupMode;
   private MainApp main;
   
   public ViewCon() {
       isP1SetupMode = true;
       isP2SetupMode = true;
   }
   
   public void setGame(BattleshipGame bg) {
       this.gameConnect = bg;
   }

    public void setMain(MainApp m) {
        this.main = preB.getMainConnection();
    }
   
   
   public void setPreB(PreBoard p) {
       this.preB = p;
   }
   
   public PreBoard getPreB() {
       return preB;
   }
   
   public void setPreShowBtn(Button s) {
       this.preShow = s;
   }   
   
   public void setPreHideBtn(Button s) {
       this.preHide = s;
   }   
   
   public ViewCon getCon() {
       return this;
   }
  
   public void setp1(P1Board p1) {
       this.p1B = p1;
       p1B.setLink(this);
   }
   
   public void setp2(P2Board p2) {
       this.p2B = p2;
       p2B.setLink(this);
   }   
  
   
   public void setPlayer1(String p) {
      this.player1 = p;
      p1B.setPlayer(player1);
   }  
   
   public void setPlayer2(String p) {
       this.player2 = p;
       p2B.setPlayer(player2);
   }
   
   public void setP1BoardLink() {
       p1B.setLink(this);
   }
   
   public void setP2BoardLink() {
       p2B.setLink(this);
   }
   
   public void preShow() {
       System.out.println(preShow);
       preShow.fire();
   }
   
   public void preHide() {
       preHide.fire();
   }     
   
   // Connection directly across to the BattleshipGame object - this is how view communicates with model
   public void setBattleshipGame(BattleshipGame b) {
       this.gameConnect = b;
   }
   
   public BattleshipGame getBattleshipGame() {
       return gameConnect;       
   }
   
   public void showP1() {
       p1B.showBtn.fire();
   }
   
   public void hideP1() {
       p1B.hideBtn.fire();
   }
   
   public void showP2() {
       p2B.showBtn.fire();
   }
   
   public void hideP2() {
       p2B.hideBtn.fire();
   }
   
   public void prime2show() {
       p2B.primeShow();
   }
   
   
    
}