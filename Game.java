import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {

    public static Player player = new Player();
    public Scanner input = new Scanner(System.in);
    public Integer level = 1;
    public Boolean run = true;

    public Game() {

    }

    public HashMap<String, Integer> levelUp(HashMap<String, Integer> stats) {
        clearConsole();
        println("YOU'VE LEVELED UP!\n");
        println("");
        statUpgrade(stats, "Level", 1);
        statUpgrade(stats, "Health", 10);
        statUpgrade(stats, "Attack", 3);
        statUpgrade(stats, "Defense", 2);
        player.status(stats, 0);
        pressEnter(input);
        return stats;
    }

    public HashMap<String, Integer> statUpgrade(HashMap<String, Integer> stats, String attribute,
            Integer value) {
        println(String.format("Your %s attribute has gone up by %d", attribute, value));
        Integer upgrade = stats.get(attribute) + value;
        stats.put(attribute, upgrade);
        return stats;
    }

    public void menu(Scanner input) {
        println("\t\t\t\tPress Enter To Start\n");
        input.nextLine();
        return;
    }

    public void help() {
        println("");
        println("\"Help\" - Displays this menu");
        println("\"Status\" - Displays Player status sheet");
        println("\"Save\" - (NOT OPERATIONAL AT THIS TIME) Saves Player progress");
        println("\"Load\" - (NOT OPERATIONAL AT THIS TIME) Loads Player progress");
        println("");
    }

    public void gameOver() {
        clearConsole();
        println("You succumb to your wounds, and your eyes close for the last time...\n\n");
        println("=========================");
        println("\tGAME OVER");
        println("=========================");
        pressEnter(input);
    }

    public final void clearConsole() {
        print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < 500; i++) {
            println("");
        }
    }

    public void pressEnter(Scanner input) {
        println("\t\t\t\tPress Enter To Continue\n");
        input.nextLine();
        return;
    }

    public String stringInput(Scanner input) {
        try {
            String value = input.next();
            return value;
        } catch (NoSuchElementException e) {
            String error = "error - no such element exception";
            print(error);
            return null;
        }
    }

    public Integer intInput(Scanner input) {
        try {
            Integer value = input.nextInt();
            return value;
        } catch (NoSuchElementException e) {
            String error = "error - no such element exception";
            print(error);
            return null;
        }
    }

    public void print(String input) {
        System.out.print(input);
    }

    public void print(Integer input) {
        System.out.print(input);
    }

    public void println(String input) {
        System.out.println(input);
    }

    public void println(Integer input) {
        System.out.println(input);
    }

    public void draw(String name) {
        HashMap<String, String> art = new HashMap<>();
        art.put("title", """
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
        art.put("chest", """
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
        art.put("devil", """
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
        if (art.get(name) != null) {
            println(art.get(name));
        } else {
            println("Error: Missing Art - Name does not exist");
        }
    }

}
