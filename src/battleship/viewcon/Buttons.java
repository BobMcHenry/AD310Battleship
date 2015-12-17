package battleship.viewcon;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by c-dub on 12/10/2015.
 */
class Buttons {
    private String[] ships;
    private int[] sizes;
    private Button[] shipBtnIdLetters1;
    private Button[] shipBtnIdLetters2;
    private P1Board p1;
    private P2Board p2;
    private Map<String, Integer> shipsAndSizes;
    private int shipButtonLabelCounter;

    Buttons(String[] s, int[] size) {
        shipsAndSizes = new HashMap<>();
        this.ships = s;
        this.sizes = size;
        shipBtnIdLetters1 = new Button[ships.length];
        shipBtnIdLetters2 = new Button[ships.length];
        for(int i = 0; i < ships.length; i++) {
            shipsAndSizes.put(ships[i], sizes[i]);
        }
        shipButtonLabelCounter = 0;
    }

    void setP1(P1Board p) {
        this.p1 = p;
    }

    void setP2(P2Board p) {
        this.p2 = p;
    }

    Button[] getShipBtns1() {
        return shipBtnIdLetters1;
    }

    Button[] getShipBtns2() {
        return shipBtnIdLetters2;
    }

    Map<String, Integer> getShipMap() {
        return shipsAndSizes;
    }

    //basically we will set a button loop here. Based on the number of ships, and the types of ships in the
    //  ship list, we will build N number of ships with ships[i] type. All the buttons will be added to a container
    //  object which will be returned to the caller (P1 and P2 boards), to then be added to their respective
    //  gridMains. For event handling, we will also define some methods in here to replace what we had in
    //  the P1Board and P2Board classes before expanding the requirements
    VBox makeShipButtons1() {
        // Actual buttons to select ship to place
        VBox shipButtons = new VBox();
        shipButtons.setSpacing(5);
        shipButtons.setId("shipBox");
        shipButtons.setPrefWidth(100);
        shipButtons.setPadding(new Insets(0, 0, 0, 160));

        for (int i = 0; i < ships.length; i++) {
            Button temp = new Button(ships[i]);
            temp.setId(ships[i]);
            temp.setMinWidth(160);
            temp.setMinHeight(30);
            shipBtnIdLetters1[i] = temp;
            temp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // Get the object fired, cast it to a Button from Object, then get String
                    Object source = e.getSource();
                    if (source instanceof Button) { // This should always be true because its a Button fire
                        Button clickedBtn = (Button) source;
                        String shipBtnId = clickedBtn.getId();
                        p1.handleShipBtnSetup(shipBtnId);
                    }
                }
            });
            shipButtons.getChildren().addAll(temp);
        }
        return shipButtons;
    }

        VBox makeShipButtons2() {
            // Actual buttons to select ship to place
            VBox shipButtons = new VBox();
            shipButtons.setSpacing(5);
            shipButtons.setId("shipBox");
            shipButtons.setPrefWidth(100);
            shipButtons.setPadding(new Insets(0, 0, 0, 160));

            for (int i = 0; i < ships.length; i++) {
                Button temp = new Button(ships[i]);
                temp.setId(ships[i]);
                temp.setMinWidth(160);
                temp.setMinHeight(30);
                shipBtnIdLetters2[i] = temp;
                temp.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        // Get the object fired, cast it to a Button from Object, then get String
                        Object source = e.getSource();
                        if (source instanceof Button) { // This should always be true because its a Button fire
                            Button clickedBtn = (Button) source;
                            String shipBtnId = clickedBtn.getId();
                            p2.handleShipBtnSetup(shipBtnId);
                        }
                    }
                });
                shipButtons.getChildren().addAll(temp);
            }
            return shipButtons;
        }

}
