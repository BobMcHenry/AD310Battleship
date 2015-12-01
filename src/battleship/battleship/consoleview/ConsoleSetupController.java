package battleship.battleship.consoleview;

import battleship.model.*;


import java.util.*;

public class ConsoleSetupController {

    Scanner input;
    BattleshipGame bg;
    String p1;
    String p2;

    public ConsoleSetupController(){
        input = new Scanner(System.in);
        System.out.println("Enter Player one name: ");
        p1 = input.nextLine();
        System.out.println("Enter Player two name: ");
        p2 = input.nextLine();

        bg = new BattleshipGame(p1,p2);

    }

    public BattleshipGame runSetup(){
        placeShips(p1);

        placeShips(p2);

        return bg;
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

    void placeShips(String playerName){

        String[] ships = {"aircraft carrier", "battleship", "cruiser", "destroyer", "destroyer"};
        System.out.println(playerName + " Ship Setup. \nEnter coordinates as Row letter:Column number. Ex. \"A:3\"\n");
        for (String s: ships){
            System.out.print(playerName + " placing " + s + "\nEnter Coordinates for Ship Head: \n");
            String head = input.nextLine().toUpperCase();

            System.out.print("\nEnter Coordinates for Ship Tail: \n");
            String tail = input.nextLine().toUpperCase();

            //validate coords and parse to int pair.
            head = validateCoords(head, input);
            int headx = stringToRow(head);
            int heady = stringToCol(head);

            //validate coords and parse to int pair.
            tail = validateCoords(tail, input);
            int tailx = stringToRow(tail);
            int taily = stringToCol(tail);

            //validate placement in the model, reprompt for coords if fails
            while ( !(bg.placeShip(s, headx, heady, tailx, taily)) ){
                System.out.println("Invalid placement. Check coords and reenter");

                System.out.println(playerName + " placing " + s + "\nEnter Coordinates for Ship Head: \n");
                head = input.nextLine().toUpperCase();
                System.out.print("Enter Coordinates for Ship Tail: \n");
                tail = input.nextLine().toUpperCase();

                head = validateCoords(head, input);
                headx = stringToRow(head);
                heady = stringToCol(head);

                tail = validateCoords(tail, input);
                tailx = stringToRow(tail);
                taily = stringToCol(tail);
            }
        System.out.println(s + " has been placed.");
        }
        System.out.println(playerName + " has completed setup.");
    }

    static String validateCoords(String s, Scanner in){
        if (s.charAt(0) < 65 || s.charAt(0) > 74){
            System.out.println("Invalid row. Please enter a row A-J: ");
            s = validateCoords(in.nextLine().toUpperCase(), in);
        }
        try{
            int strInt = Integer.parseInt(s.substring(2));
            if (strInt > 10 || strInt < 1){
                System.out.println("Invalid column. Please re-enter coords: ");
                s = validateCoords(in.nextLine().toUpperCase(), in);
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid column. Please re-enter coords:  ");
            s = validateCoords(in.nextLine().toUpperCase(), in);
        }

        return s;
    }

    static int stringToRow(String coords){
        return (int)coords.charAt(0) - 65;
    }

    static int stringToCol(String coords){
        return Integer.parseInt(coords.substring(2)) - 1 ;
    }

}
