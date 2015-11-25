/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.viewcon;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author foolishklown
 */
public class ViewCon {
    
   private P1Board p1B;
   private P2Board p2B;  
   private StackPane stack;
   private PreBoard preB;
   private Stage theStage;
   private String player1;
   private String player2;
   private Button preShow;
   private Button preHide;
   
   
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
    
}