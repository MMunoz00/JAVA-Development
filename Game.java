import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;

public class Game implements Serializable {
    private static Game game;
    public static Player p = new Player();
    public static Level l = new Level();
    public static transient Scanner input = new Scanner(System.in);
    public static Integer level;
    public static transient Boolean run;
    
    public Game() {
        level = 1;
        run = true;
    }

    public static Game getGameInstance() {
        if (game == null){
            game = new Game();
        }
        return game;
    }

    public static void levelUp() {
        clearConsole();
        println("YOU'VE LEVELED UP!\n");
        println("");
        statUpgrade("Level", 1);
        statUpgrade("Health", 10);
        statUpgrade("Attack", 3);
        statUpgrade("Defense", 2);
        p.stats.put("Damage", 0);
        return;
    }

    public static void statUpgrade(String attribute, Integer value) {
        if (value > 0) {
            println(String.format("Your %s attribute has increased by %d", attribute, value));
        } 
        else if (value == 0) {
            println("ERROR: ZERO ATTRIBUTE MODIFICATION");
            return;
        } else {
            println(String.format("Your %s attribute has decreased by %d", attribute, value));
        }
        Integer upgrade = p.stats.get(attribute) + value;
        p.stats.put(attribute, upgrade);
        return;
    }

    public static void menu() {
        clearConsole();
        draw("Title");
        println("\t\t\t\tPress Enter To Start\n");
        input.nextLine();
        return;
    }

    public static void save() {
        String filename = "save.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            // Write the data of the Game object to the file
            writer.write("Level: " + level + "\n");
            writer.write("Player Level: " + p.stats.get("Level") + "\n");
            writer.write("Player Health: " + p.stats.get("Health") + "\n");
            writer.write("Player Attack: " + p.stats.get("Attack") + "\n");
            writer.write("Player Defense: " + p.stats.get("Defense") + "\n");
            writer.write("Player Damage: " + p.stats.get("Damage") + "\n");
            writer.write("Inventory: " + p.printInventory() + "\n");

            System.out.println("Game saved successfully.");            
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Game load() {
        game = new Game();
        try (BufferedReader reader = new BufferedReader(new FileReader("save.txt"))) {
            // Read the data from the file and update the Game object
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    switch (key) {
                        case "Level":
                            level = Integer.parseInt(value);
                            break;
                        case "Player Level":
                            p.stats.put("Level", (Integer.parseInt(value)));
                            break;
                        case "Player Health":
                            p.stats.put("Health", (Integer.parseInt(value)));
                            break;
                        case "Player Attack":
                            p.stats.put("Attack", (Integer.parseInt(value)));
                            break;
                        case "Player Defense":
                            p.stats.put("Defense", (Integer.parseInt(value)));
                            break;
                        case "Player Damage":
                            p.stats.put("Damage", (Integer.parseInt(value)));
                            break;
                        case "Inventory":
                            String inventoryParts[] = value.split(",");
                            p.inventory.clear();
                            for (int i = 0; i < inventoryParts.length; i++) {
                                println(inventoryParts[i]);
                                switch (inventoryParts[i]) {
                                    case "HEALTH POTION":
                                        p.giveItem("health potion");
                                        break;
                                    case "HEALTH ELIXIR":
                                        p.giveItem("health elixir");
                                        break;
                                    case "ATTACK ELIXIR":
                                        p.giveItem("attack elixir");
                                        break;
                                    case "DEFENSE ELIXIR": 
                                        p.giveItem("defense elixir");
                                        break;
                                }
                            }
                            break;
                    }
                }
            }
            System.out.println("Game loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
            e.printStackTrace();
        }
        return game;
    }

    public static void help() {
        println("");
        println("\"Help\" - Displays this menu");
        println("\"Status\" - Displays Player status sheet");
        println("\"Level\" - Displays current level");
        println("\"Save\" - Saves Player progress");
        println("\"Load\" - Loads Player progress");
        println("");
        pressEnter();
    }

    public static void gameOver() {
        clearConsole();
        println("You succumb to your wounds, and your eyes close for the last time...\n\n");
        println("=========================");
        println("\tGAME OVER");
        println("=========================");
        pressEnter();
    }

    public static final void clearConsole() {
        print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < 50; i++) {
            println("");
        }
    }

    public static void pressEnter() {
        println("\t\t\t\tPress Enter To Continue\n");
        input.nextLine();
        return;
    }

    public static String stringInput() {
        try {
            String value = input.nextLine();
            return value.toUpperCase();
        } catch (NoSuchElementException e) {
            String error = "error - no such element exception";
            print(error);
            return null;
        }
    }

    public static Integer intInput() {
        try {
            Integer value = input.nextInt();
            return value;
        } catch (NoSuchElementException e) {
            String error = "error - no such element exception";
            print(error);
            return null;
        }
    }

    public static void print(String input) {
        System.out.print(input);
    }

    public static void print(Integer input) {
        System.out.print(input);
    }

    public static void println(String input) {
        System.out.println(input);
    }

    public static void println(Integer input) {
        System.out.println(input);
    }

    public static void draw(String name) {
        HashMap<String, String> art = new HashMap<>();
        art.put("Title", """
                      ___                                                               _
                    ,"___".    ____     _ _____     _ _____      ___ _    _ ___      ___FJ
                    FJ---L]   F __ J   J '_  _ `,  J '_  _ `,   F __` L  J '__ J    F __  L
                   J |   LJ  | |--| |  | |_||_| |  | |_||_| |  | |--| |  | |__| |  | |--| |
                   | \\___--. F L__J J  F L LJ J J  F L LJ J J  F L__J J  F L  J J  F L__J J
                   J\\_____/FJ\\______/FJ__L LJ J__LJ__L LJ J__LJ\\____,__LJ__L  J__LJ\\____,__L
                    J_____F  J______F |__L LJ J__||__L LJ J__| J____,__F|__L  J__| J____,__F
                      _       __                                     ___       ___     ___
                     FJ       LJ   _ ___      ____                  F _ ",    F _ ", ,"___".
                    J |           J '__ J    F __ J                J `-'(|   J `-' | FJ---L]
                    | |       FJ  | |__| |  | _____J               |  _  L   |  __/FJ |  [""L
                    F L_____ J  L F L  J J  F L___--.              F |_\\  L  F |__/ | \\___] |
                   J________LJ__LJ__L  J__LJ\\______/F             J__| \\\\__LJ__|    J\\_____/F
                   |________||__||__L  J__| J______F              |__|  J__||__L     J_____F


                ====================================================================================
                ====================================================================================





                """);
        art.put("Chest", """
                                   _.--.
                               _.-'_:-'||
                           _.-'_.-::::'||
                   _.-:'_.-::::::'  ||
                 .'`-.-:::::::'     ||
                /.'`;|:::::::'      ||_
                ||   ||::::::'     _.;._'-._
                ||   ||:::::'  _.-!oo @.!-._'-.
                 \\'.  ||:::::.-!()oo @!()@.-'_.|
                 '.'-;|:.-'.&$@.& ()$%-'o.'\\U||
                   `>'-.!@%()@'@_%-'_.-o _.|'||
                   ||-._'-.@.-'_.-' _.-o  |'||
                   ||=[ '-._.-\\U/.-'    o |'||
                   || '-.]=|| |'|      o  |'||
                   ||      || |'|        _| ';
                   ||      || |'|    _.-'_.-'
                   |'-._   || |'|_.-'_.-'
                    '-._'-.|| |' `_.-'
                           '-.||_/.-'
                              """);
        art.put("Demon", """
                    *                       *
                    *                 *
                   )       (\\___/)     (
                * /(       \\ (. .)     )\\ *
                  # )      c\\   >'    ( #
                   '         )-_/      '
                 \\\\|,    ____| |__    ,|//
                   \\ )  (  `  ~   )  ( /
                    #\\ / /| . ' .) \\ /#
                    | \\ / )   , / \\ / |
                     \\,/ ;;,,;,;   \\,/
                      _,#;,;;,;,
                     /,i;;;,,;#,;
                    //  %;;,;,;;,;
                   ((    ;#;,;%;;,,
                  _//     ;,;; ,#;,
                 /_)      #,;    ))
                         //      \\|_
                         \\|_      |#\\
                          |#\\      -"
                           -"
                            """);
        art.put("Merchant", """

                         ____           
                    .--\"\"___ \"\"-,       
                .\' .-\"\" __:-\' ;       
                /__:.--\"\"      :       
                \\              _`-\'\\   
                \\_..--\"\"    \"\"     :  
                /      ______..,   ;  
            _gd$$$$$$$$$$$$$$$P===;  
            ,g$$$$$$P^^^^T$$$$$P'    ;  
            T^\": ,-.       \"\"\"  \\    :  
                ;;  d.   .-\"\"\"d. \\ ,-:  
            : \'.:$$\'-\"    :$$  \'.-,; 
            ;   :^\"    \"-._T\'  \') :: 
            /   /      \\         ._.\' 
            .   :        ; \\       \\;  
            ;    \\      /   :       :  
            ;     '-..-'            ;  
            :     ,---.    ,       /   
            \'    \'  -. \"--\"      .'    
            `.              _.-\"      
                \"-.       _.-\"          
                \"-._.-\"     

            """);
            
        if (art.get(name) != null) {
            println(art.get(name));
        } else {
            println("Error: Missing Art - Name does not exist");
        }
    }

}
