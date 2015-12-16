package battleship.consoleview;

import battleship.model.*;


import java.util.*;

public class ConsoleSetupController {

    Scanner input;
    BattleshipGame bg;
    String p1;
    String p2;

    public ConsoleSetupController(){
        System.out.println("Welcome to BATTLESHIP!");
        input = new Scanner(System.in);
        System.out.println("Enter Player one name: ");
        String p1Name = input.nextLine();
        p1 = p1Name.length() > 0 ? p1Name : "Player 1";

        System.out.println("Enter Player two name: ");
        String p2Name = input.nextLine();
        p2 = p2Name.length() > 0 ? p2Name : "Player 2";
        bg = new BattleshipGame(p1,p2);

    }

    public BattleshipGame runSetup(){
        placeShips(p1);

        placeShips(p2);

        return bg;
    }

    void placeShips(String playerName){

        String[] ships = {"aircraft carrier", "battleship", "cruiser", "destroyer", "destroyer"};
        System.out.println(playerName + " Ship Setup. \nEnter coordinates as Row letter followed by Column number. Ex. \"A3\"\n");

        for (String s: ships){
            System.out.print(playerName + " placing " + s + "\nEnter Coordinates for Ship Head: \n");
            String head = input.nextLine().toUpperCase();


            //validate coords and parse to int pair.
            while (!validateCoords(head)){
                System.out.println("INVALID COORDINATES");
                System.out.println("Enter Coordinates for Ship Head: ");
                head = input.nextLine().toUpperCase();
            }
            int headx = stringToRow(head);
            int heady = stringToCol(head);


            System.out.print("\nEnter Coordinates for Ship Tail: \n");
            String tail = input.nextLine().toUpperCase();

            //validate coords and parse to int pair.
            while (!validateCoords(tail)){
                System.out.println("INVALID COORDINATES");
                System.out.println("Enter Coordinates for Ship Head: ");
                tail = input.nextLine().toUpperCase();
            }
            int tailx = stringToRow(tail);
            int taily = stringToCol(tail);

            //validate placement in the model, reprompt for coords if fails
            while ( !(bg.placeShip(s, headx, heady, tailx, taily)) ) {
                System.out.println("Invalid placement. Check coordinates and re-enter");

                System.out.println(playerName + " placing " + s);
                System.out.println("Enter Coordinates for Ship Head: ");
                head = input.nextLine().toUpperCase();

                while (!validateCoords(head)){
                    System.out.println("INVALID COORDINATES");
                    System.out.println("Enter Coordinates for Ship Head: ");
                    head = input.nextLine().toUpperCase();
                }
                headx = stringToRow(head);
                heady = stringToCol(head);

                System.out.print("Enter Coordinates for Ship Tail: ");
                tail = input.nextLine().toUpperCase();

                while (!validateCoords(tail)){
                    System.out.println("INVALID COORDINATES");
                    System.out.println("Enter Coordinates for Ship Head: ");
                    tail = input.nextLine().toUpperCase();
                }
                tailx = stringToRow(tail);
                taily = stringToCol(tail);
            }
        System.out.println(s + " has been placed.");
        }
        System.out.println(playerName + " has completed setup.");
    }

    static boolean validateCoords(String s){
        return checkLength(s) && checkRow(s) && checkCol(s);

    }

    static int stringToRow(String coords){
        return (int)coords.charAt(0) - 65;
    }

    static int stringToCol(String coords){
        return Integer.parseInt(coords.substring(1)) - 1 ;
    }

    static boolean checkLength(String s){
        return s.length() >= 2 && s.length() <= 3;
    }
    static boolean checkRow(String s){
        return s.charAt(0) >= 65 && s.charAt(0) <= 74;
    }
    static boolean checkCol(String s){
        String col;
        if (s.length() > 1) {
            col = s.substring(1);
        } else {
            return false;
        }

        if (col.length() == 1){
            return col.charAt(0) >=49 && col.charAt(0) <= 57;
        } else if(col.length() == 2) {
            return col.charAt(0) >=49 && col.charAt(0) <= 57 && col.charAt(1) == 48;
        } else {
            return false;
        }

    }

    BattleshipGame testSetup(){
        BattleshipGame bg = new BattleshipGame("P1", "P2");
        String[] ships = {"aircraft carrier", "battleship", "cruiser", "destroyer", "destroyer"};
        bg.placeShip(ships[0], 0,0,0,4);
        bg.placeShip(ships[1], 1,0,1,3);
        bg.placeShip(ships[2], 2,0,2,2);
        bg.placeShip(ships[3], 3,0,3,1);
        bg.placeShip(ships[4], 4,0,4,1);

        bg.placeShip(ships[0], 0,0,0,4);
        bg.placeShip(ships[1], 1,0,1,3);
        bg.placeShip(ships[2], 2,0,2,2);
        bg.placeShip(ships[3], 3,0,3,1);
        bg.placeShip(ships[4], 4,0,4,1);
        return bg;
    }
}
