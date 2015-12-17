/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.viewcon;

import java.util.*;

/**
 *
 * @author c-dub
 */
public class ShotProcessor {

    public ArrayList<HashMap<String, Boolean>> mapList1, mapList2;
    public HashMap<String, String[]> p1ShipCoordsMap;
    public HashMap<String, String[]> p2ShipCoordsMap;

    public ShotProcessor() {
        mapList1 = new ArrayList<>();
        mapList2 = new ArrayList<>();
        p1ShipCoordsMap = new HashMap<>();
        p2ShipCoordsMap = new HashMap<>();
    }
    
    public void saveP1Defense(String[] coordsList, String ship) {
            p1ShipCoordsMap.put(ship, coordsList);
            HashMap<String, Boolean> tempMap = new HashMap<>();
            int count = 0;
            for(int i = 0; i < coordsList.length; i++) {
                tempMap.put(coordsList[count], false);

                count++;
            }
            mapList1.add(tempMap);
    }
    
    public void saveP2Defense(String[] coordsList, String ship) {
            p2ShipCoordsMap.put(ship, coordsList);
            HashMap<String, Boolean> tempMap = new HashMap<>();
            int count = 0;
            for(int i = 0; i < coordsList.length; i++) {
                tempMap.put(coordsList[count], false);
                count++;
            }
            mapList2.add(tempMap);
    }


    public String getShipTypeP1(String id) {
        for(Map.Entry<String, String[]> entry: p1ShipCoordsMap.entrySet()) {
            String[] temp = entry.getValue();
            String ship = entry.getKey();
            for(int i = 0; i < temp.length; i++) {
                if(temp[i].equals(id)) {
                    return ship;
                }
            }
        }
        return null;
    }

    public String getShipTypeP2(String id) {
        for(Map.Entry<String, String[]> entry: p2ShipCoordsMap.entrySet()) {
            String[] temp = entry.getValue();
            String ship = entry.getKey();
            for(int i = 0; i < temp.length; i++) {
                if(temp[i].equals(id)) {
                    return ship;
                }
            }
        }
        return null;
    }
    
}
