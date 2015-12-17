package battleship.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by bob on 12/16/15.
 */

public class BattleshipConfig {
    int boardSize;
    boolean isDiagonalAllowed, switchPlayerOnHit;
    ArrayList<ShipType> availableShips;
    HashMap<ShipType, Integer> shipSizes;

    static final File ORIGINAL = new File("/home/bob/IdeaProjects/AD310Battleship/src/battleship/model/battleship.conf");
    static final File OPTION_1 = new File("/home/bob/IdeaProjects/AD310Battleship/src/battleship/model/battleshipOption1.conf");
    static final File OPTION_2 = new File("/home/bob/IdeaProjects/AD310Battleship/src/battleship/model/battleshipOption2.conf");

    public BattleshipConfig(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);

        availableShips = new ArrayList<ShipType>();
        shipSizes = new HashMap<ShipType, Integer>();

        boardSize = s.nextInt();
        isDiagonalAllowed = s.nextBoolean();
        switchPlayerOnHit = s.nextBoolean();

        while (s.hasNext()){
            String st = s.next();
            ShipType ship = stringToShipType(st);

            int size = Integer.valueOf(s.next());

            availableShips.add(ship);
            shipSizes.put(ship, size);
        }
    }

    public BattleshipConfig(String path) throws FileNotFoundException {
        this(getFileFromPath(path));
    }

    public static File getOriginalGame(){
        return ORIGINAL;
    }
    public static File getOption1Game(){
        return OPTION_1;
    }
    public static File getOption2Game(){
        return OPTION_2;
    }
    public ShipType stringToShipType(String s) {
        if (s.toLowerCase().equals("aircraft carrier")
                || s.toLowerCase().equals("aircraft_carrier")) {
            return ShipType.AIRCRAFT_CARRIER;
        } else if (s.toLowerCase().equals("battleship")) {
            return ShipType.BATTLESHIP;
        } else if (s.toLowerCase().equals("cruiser")) {
            return ShipType.CRUISER;
        } else if (s.toLowerCase().equals("destroyer")) {
            return ShipType.DESTROYER;
        } else if (s.toLowerCase().equals("submarine")) {
            return ShipType.SUBMARINE;
        }
        throw new IllegalArgumentException("Not a valid ShipType");
    }

    public static File getFileFromPath(String pathname){
        File f = new File(pathname);
        return f;
    }

}
