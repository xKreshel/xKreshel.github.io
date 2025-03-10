package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hint: ");
        System.out.println("The game recognizes north, east, west, south, quit directions and the their short versions as well (N, E, W, S, Q).");
        System.out.println("You can use full sentences to play the game as long as they contains directions.");
        System.out.println();

        locations.put(0, new Location(0, "Quit the game."));
        locations.put(1, new Location(1, "You are standing before a small brick building."));
        locations.put(2, new Location(2, "You are at the top of the hill."));
        locations.put(3, new Location(3, "You are in a dark cave."));
        locations.put(4, new Location(4, "You are next to a beautiful river."));
        locations.put(5, new Location(5, "You are in a magical forest."));

        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

        Map<String, String> vocabulary = new HashMap<String, String>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        int loc = 1;
        while(true) {
            System.out.println(locations.get(loc).getDescription());
            if(loc == 0) {
                break;
            }
            Map<String, Integer> exits = locations.get(loc).getExists();
            System.out.println("Available exists: ");
            for(String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word : words) {
                   if(vocabulary.containsKey(word)) {
                       direction = vocabulary.get(word);
                       break;
                   }
                }
            }

            if(exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}
