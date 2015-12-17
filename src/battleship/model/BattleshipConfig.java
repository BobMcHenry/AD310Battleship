package battleship.model;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jbernoudy on 12/16/2015.
 */
class BattleshipConfig {
    private int boardSize;
    int getBoardSize(){
        return boardSize;
    }

    private int boardSizeSquared;
    int getBoardSizeSquared(){
        return  boardSizeSquared;
    }

    private ArrayList<ShipType> availableShips;
    ArrayList<ShipType> getAvailableShips(){
        return availableShips;
    }

    private HashMap<ShipType, Integer> shipSizes;
    HashMap<ShipType, Integer> getShipSizes(){
        return shipSizes;
    }

    private boolean diagonalsAllowed;
    boolean getDiagonalsAllowed(){
        return diagonalsAllowed;
    }

    private boolean switchPlayerOnHit;
    boolean getSwitchPlayerOnHit(){
        return switchPlayerOnHit;
    }

    private final String configFileName = "battleship.xml";
    private Document document;

    public BattleshipConfig() {
        availableShips = new ArrayList<>();
        shipSizes = new HashMap<>();

        parseXmlFile();
        parseDocument();
    }

    private void parseXmlFile(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            // Parse using builder to get DOM representation of the XML file
            document = db.parse(configFileName);

        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch(SAXException se) {
            se.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void parseDocument(){
        document.getDocumentElement().normalize();

        // Get the root element
        Element documentElement = document.getDocumentElement();

        // Get the attributes
        String boardSizeAttribute = documentElement.getAttribute("BoardSize");
        String diagonalsAllowedAttribute = documentElement.getAttribute("IsDiagonalAllowed");
        String switchPlayerOnHitAllowedAttribute = documentElement.getAttribute("SwitchPlayerOnHit");

        // Convert and store the values
        boardSize = Integer.parseInt(boardSizeAttribute);
        boardSizeSquared = boardSize * boardSize;
        diagonalsAllowed = Boolean.parseBoolean(diagonalsAllowedAttribute);
        switchPlayerOnHit =  Boolean.parseBoolean(switchPlayerOnHitAllowedAttribute);

        // Get a nodelist of ships
        NodeList shipNodes = documentElement.getElementsByTagName("Ship");
        if(shipNodes != null) {
            for(int i = 0 ; i < shipNodes.getLength();i++) {
                // Get the ships attributes
                NamedNodeMap shipNodeAttributes = shipNodes.item(i).getAttributes();
                String shipTypeAttribute = shipNodeAttributes.getNamedItem("ShipType").getNodeValue();
                String shipSizeAttribute = shipNodeAttributes.getNamedItem("Size").getNodeValue();

                // Convert the values
                ShipType shipType = ShipType.valueOf(shipTypeAttribute);
                int shipSize = Integer.parseInt(shipSizeAttribute);
                // Store
                availableShips.add(shipType);
                if(!shipSizes.containsKey(shipType)) {
                    shipSizes.put(shipType, shipSize);
                }
            }
        }
    }
}
