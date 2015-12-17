/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.viewcon;

import battleship.model.BattleshipGame;
import battleship.model.Status;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BattleshipMain extends Application {
    
    private ViewCon vc;
    private Scene preScene;
    private Scene p1Scene;
    private Scene p2Scene;
    private Stage preStage;
    private Stage p1Stage;
    private Stage p2Stage;
    private String player1;
    private String player2;
    private BattleshipGame game;
    private Image img;
    private ShotProcessor shotP;
    
    @Override
    public void start(Stage primaryStage) {
        vc = new ViewCon();
        vc.setMainConnect(this);
        vc.setShotProcessor();
        preScene = new Scene(vc.getPreGrid(), 570, 190);
        preScene.getStylesheets().add(BattleshipMain.class.getResource("styles/PreBoardStyle.css").toExternalForm());
        preStage = new Stage();
        preStage.setTitle("Player setup");
        preStage.setScene(preScene);
        preStage.setResizable(false);
        preStage.show();       
    }
    
    public void setShotProcessor(ShotProcessor s) {
        this.shotP = s;
    }
    
    public void instantiateP1() {
        p1Scene = new Scene(vc.getP1Grid(), 890, 720);
        p1Scene.getStylesheets().add(BattleshipMain.class.getResource("styles/PlayerBoardStyle.css").toExternalForm());
        p1Stage = new Stage();
        p1Stage.setTitle("Player1 Board");        
        p1Stage.setScene(p1Scene);
        p1Stage.setResizable(false);
        p1Stage.show();
    }
    
    public void instantiateP2() {
        p2Scene = new Scene(vc.getP2Grid(), 890, 720);
        System.out.println("grid didnt fail");
        p2Scene.getStylesheets().add(BattleshipMain.class.getResource("styles/PlayerBoardStyle.css").toExternalForm());
        p2Stage = new Stage();
        p2Stage.setTitle("Player2 Board");
        p2Stage.setScene(p2Scene);
        p2Stage.setResizable(false);
        p2Stage.show();
    }
    
    public void switchToP1() {
        p2Stage.hide();
        p1Stage.show();
    }
    
    public void switchToP2() {
        p1Stage.hide();
        p2Stage.show();
    }
    
    
    public void setPlayer1(String p) {
        this.player1 = p;
        vc.setP1Label(p);
    }
    
    public void setPlayer2(String p) {
        this.player2 = p;        
        vc.setP2Label(p);
    }
    
    public void launchGame() {
        preStage.close();
        game = new BattleshipGame(player1, player2);
        vc.setGameConnect(game);
        instantiateP1();
    }
    
    public boolean placeShip(String id, int row1, int col1, int row2, int col2) {
        return game.placeShip(id, row1, col1, row2, col2);
    }
    
    
    public void changeButtonsP1(String btn, String ship) {        
       if(ship.equals("AIRCRAFT CARRIER")) {
           img = new Image(getClass().getResourceAsStream("img/acGrid.jpg"));
       }
       if(ship.equals("BATTLESHIP")) {
           img = new Image(getClass().getResourceAsStream("img/bsGrid.jpg"));
       }
       if(ship.equals("CRUISER")) {
           img = new Image(getClass().getResourceAsStream("img/crGrid.jpg"));
       }
       if(ship.equals("DESTROYER")) {
           img = new Image(getClass().getResourceAsStream("img/dsGrid.jpg"));
       }
       if(ship.equals("SUBMARINE")) {
           img = new Image(getClass().getResourceAsStream("img/sbGrid.jpg"));
       }       
       ImageView imgV = new ImageView(img);
       Node n = p1Scene.lookup(btn);
       Button b = (Button)n;
       b.setGraphic(imgV);       
    }
    
    public void changeButtonsP2(String btn, String ship) {
       if(ship.equals("AIRCRAFT CARRIER")) {
           img = new Image(getClass().getResourceAsStream("img/acGrid.jpg"));
       }
       if(ship.equals("BATTLESHIP")) {
           img = new Image(getClass().getResourceAsStream("img/bsGrid.jpg"));
       }
       if(ship.equals("CRUISER")) {
           img = new Image(getClass().getResourceAsStream("img/crGrid.jpg"));
       }
       if(ship.equals("DESTROYER")) {
           img = new Image(getClass().getResourceAsStream("img/dsGrid.jpg"));
       }
       if(ship.equals("SUBMARINE")) {
           img = new Image(getClass().getResourceAsStream("img/sbGrid.jpg"));
       } 
       ImageView imgV = new ImageView(img);
       Node n = p2Scene.lookup(btn);
       Button b = (Button)n;
       b.setGraphic(imgV);       
    }
    
    public void resetP1SetupButtons(ObservableList<Node> list) {
        Image resetImg = new Image(getClass().getResourceAsStream("img/waterSquare.jpg"));
        ImageView imgV = new ImageView(resetImg);
        for(int i = 0; i < list.size(); i++) {
            Button b = (Button)list.get(i);
            b.setGraphic(imgV);
        }
    }
    
    public void resetP2SetupButtons(ObservableList<Node> list) {
        Image resetImg = new Image(getClass().getResourceAsStream("img/waterSquare.jpg"));
        ImageView imgV = new ImageView(resetImg);
        for(int i = 0; i < list.size(); i++) {
            Button b = (Button)list.get(i);
            b.setGraphic(imgV);
        }
    }

    public void processP2Hit(String id) {
        String newId = "#" + id;
        Node x = p2Scene.lookup(newId);
        Button b = (Button)x;
        for(int i = 0; i < shotP.mapList1.size();i++) {
            if(shotP.mapList1.get(i).containsKey(id)) {
                shotP.mapList1.get(i).replace(id, true);
                String shipType = shotP.getShipTypeP1(id);
                switch(shipType) {
                    case "AIRCRAFT CARRIER" : img = new Image(getClass().getResourceAsStream("img/acHit.jpg"));
                        break;
                    case "BATTLESHIP" : img = new Image(getClass().getResourceAsStream("img/bsHit.jpg"));
                        break;
                    case "CRUISER" : img = new Image(getClass().getResourceAsStream("img/crHit.jpg"));
                        break;
                    case "SUBMARINE" : img = new Image(getClass().getResourceAsStream("img/sbHit.jpg"));
                        break;
                    case "DESTROYER" : img = new Image(getClass().getResourceAsStream("img/dsHit.jpg"));
                        break;
                }
                ImageView imgV = new ImageView(img);
                b.setGraphic(imgV);
                Status isSunk = vc.getStatus(id);
                if(isSunk.equals(Status.SUNK)) {
                    vc.showSunk("p1", shipType + "Label");
                }
            }
        }
    }

    public void processP1Hit(String id) {
        String newId = "#" + id;
        Node x = p1Scene.lookup(newId);
        Button b = (Button)x;
        for(int i = 0; i < shotP.mapList2.size();i++) {
            if(shotP.mapList2.get(i).containsKey(id)) {
                shotP.mapList2.get(i).replace(id, true);
                String shipType = shotP.getShipTypeP2(id);
                switch(shipType) {
                    case "AIRCRAFT CARRIER" : img = new Image(getClass().getResourceAsStream("img/acHit.jpg"));
                        break;
                    case "BATTLESHIP" : img = new Image(getClass().getResourceAsStream("img/bsHit.jpg"));
                        break;
                    case "CRUISER" : img = new Image(getClass().getResourceAsStream("img/crHit.jpg"));
                        break;
                    case "SUBMARINE" : img = new Image(getClass().getResourceAsStream("img/sbHit.jpg"));
                        break;
                    case "DESTROYER" : img = new Image(getClass().getResourceAsStream("img/dsHit.jpg"));
                        break;
                }
                ImageView imgV = new ImageView(img);
                b.setGraphic(imgV);
                Status isSunk = vc.getStatus(id);
                if(isSunk.equals(Status.SUNK)) {
                    vc.showSunk("p2", shipType + "Label");
                }
            }
        }
    }
    
    public void processP1Miss(String id) {
        id = "#" + id;
        img = new Image(getClass().getResourceAsStream("img/miss.jpg"));
            ImageView imgV = new ImageView(img);
            Node n = p1Scene.lookup(id);
            Button b = (Button)n;
            b.setGraphic(imgV);
    }
    
    public void processP2Miss(String id) {
        id = "#" + id;
        img = new Image(getClass().getResourceAsStream("img/miss.jpg"));
            ImageView imgV = new ImageView(img);
            Node n = p2Scene.lookup(id);
            Button b = (Button)n;
            b.setGraphic(imgV);
    }

    public void exitApp() {
        Platform.exit();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
