/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.viewcon;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author c-dub
 */
public class ShotProcessor {
    
    public Map<String, Boolean> acMapP1, bsMapP1, crMapP1, ds1MapP1, ds2MapP1, sbMapP1, acMapP2, bsMapP2, crMapP2, ds1MapP2, ds2MapP2, sbMapP2; 
    
    public ShotProcessor() {
        acMapP1 = new HashMap<>();
        bsMapP1 = new HashMap<>();
        crMapP1 = new HashMap<>();
        ds1MapP1 = new HashMap<>();
        ds2MapP1 = new HashMap<>();
        sbMapP1 = new HashMap<>();
        acMapP2 = new HashMap<>();
        bsMapP2 = new HashMap<>();
        crMapP2 = new HashMap<>();
        ds1MapP2 = new HashMap<>();
        ds2MapP2 = new HashMap<>();
        sbMapP2 = new HashMap<>();
    }
    
    public void saveP1Defense(String[] coordsList, String ship) {
            System.out.println("coordinate list being passed into maps. size is: " + coordsList.length);
            switch(ship) {
                case "AIRCRAFT CARRIER" : setP1AcMap(coordsList);
                break;
                case "BATTLESHIP" : setP1BsMap(coordsList);
                break;
                case "CRUISER" : setP1CrMap(coordsList);
                break;
                case "DESTROYER" : if(ds1MapP1.size() == 0) {
                    System.out.println("setting up first destroyer");
                    setP1Ds1Map(coordsList);
                } else {
                    System.out.println("setting up second destroyer");
                    setP1Ds2Map(coordsList);
                };
                break;
                case "SUBMARINE" : setP1SbMap(coordsList);
                break;
                
            }   
    }
    
    public void saveP2Defense(String[] coordsList, String ship) {
            System.out.println("coordinate list being passed into maps. size is: " + coordsList.length);
             switch(ship) {
                case "AIRCRAFT CARRIER" : setP2AcMap(coordsList);
                break;
                case "BATTLESHIP" : setP2BsMap(coordsList);
                break;
                case "CRUISER" : setP2CrMap(coordsList);
                break;
                case "DESTROYER" : if(ds1MapP2.size() == 0) {
                    System.out.println("setting up first destroyer");
                    setP2Ds1Map(coordsList);
                } else {
                    setP2Ds2Map(coordsList);
                };
                break;
                case "SUBMARINE" : setP2SbMap(coordsList);
                break;
                
            }
    }
    
    public void setP1AcMap(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            acMapP1.put(coords[i], false);
            System.out.println("coordinate going into ac map: " +coords[i]);
            System.out.println("value going into ac map: " + acMapP1.get(coords[i]));
        }
    }
    
    public void setP1BsMap(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            bsMapP1.put(coords[i], false);
        }
    }
    
    public void setP1CrMap(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            crMapP1.put(coords[i], false);
        }
    }
    
    public void setP1Ds1Map(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            ds1MapP1.put(coords[i], false);
        }
    }
    
    public void setP1Ds2Map(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            ds2MapP1.put(coords[i], false);
        }
    }
    
    public void setP1SbMap(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            sbMapP1.put(coords[i], false);
        }
    }
    
    public void setP2AcMap(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            acMapP2.put(coords[i], false);
        }
    }
    
    public void setP2BsMap(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            bsMapP2.put(coords[i], false);
        }
    }
    
    public void setP2CrMap(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            crMapP2.put(coords[i], false);
        }
    }
    
    public void setP2Ds1Map(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            ds1MapP2.put(coords[i], false);
        }
    }
    
    public void setP2Ds2Map(String[] coords) {
         for(int i = 0; i < coords.length; i++) {
            ds2MapP2.put(coords[i], false);
        }
    }
    
    public void setP2SbMap(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            sbMapP2.put(coords[i], false);
        }
    }
    
    public boolean checkSunk(Map<String, Boolean> map) {
        System.out.println("Map received, checking " + map);
        int count = 0;
        boolean sunk = false;
        Collection c = map.values();
        Iterator itr = c.iterator();
        while(itr.hasNext()) {
            if(itr.next().equals(true)) {
                count++;
            }
            if(count == map.size()) {
                sunk = true;
            }
        }
        return sunk;
    }
    
}
