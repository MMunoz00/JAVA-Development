import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import javax.print.attribute.IntegerSyntax;

import java.io.Console;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<String, Integer> playerStats = defineStats();
        Integer playerDmg = 0;
        Boolean run = true;
        int level = 1;
        clearConsole();
        draw("title");
        menu();

        println("Your current status:\n");
        status(playerStats, playerDmg);
        do {
            switch (level) {
                case 1:
                    switch (levelOne(input)) {
                        case 1:
                            clearConsole();
                            levelUp(playerStats);
                            level++;
                            break;
                        case 2:
                            clearConsole();
                            statUpgrade(playerStats, "Defense", 5);
                            status(playerStats, playerDmg);
                            level++;
                            break;
                    }
                    break;
                case 2:
                    switch (levelTwo(input)) {
                        case 1:
                            println("Coward...\n");
                            pressEnter();
                            break;
                        case 2:
                            playerDmg = battle(playerStats, defineStats(22, 15, 5), "devil");
                            if ((playerStats.get("Health") - playerDmg) <= 0) {
                                gameOver();
                                run = false;
                            } else {
                                levelUp(playerStats);
                            }
                            break;
                    }
                    break;
            }
        } while (run != false);
        input.close();
    }

    public final static void clearConsole() {
        print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < 10000; i++) {
            print("\b");
        }
    }

    public static void gameOver() {
        clearConsole();
        println("You succumb to your wounds, and your eyes close for the last time...\n\n");
        println("=========================");
        println("\tGAME OVER");
        println("=========================");
        pressEnter();
    }

    public static HashMap<String, Integer> defineStats() {
        HashMap<String, Integer> stats = new HashMap();
        stats.put("Health", 10);
        stats.put("Attack", 5);
        stats.put("Defense", 3);
        return stats;
    }

    public static HashMap<String, Integer> defineStats(Integer hp, Integer atk, Integer def) {
        HashMap<String, Integer> stats = new HashMap();
        stats.put("Health", hp);
        stats.put("Attack", atk);
        stats.put("Defense", def);
        return stats;
    }

    public static void status(HashMap<String, Integer> stats, Integer damage) {
        println("");
        println("Health: " + (stats.get("Health") - damage) + "/" + stats.get("Health"));
        println("Attack: " + stats.get("Attack"));
        println("Defense: " + stats.get("Defense"));
        println("");
        pressEnter();
    }

    public static void enemyStatus(HashMap<String, Integer> stats, Integer damage) {
        println("");
        println("Health: " + (stats.get("Health") - damage) + "/" + stats.get("Health"));
        println("");
    }

    public static Integer levelOne(Scanner input) {
        clearConsole();
        println("\nYou are traveling along a dark road.");
        println("You come across a fork in the path.");
        println("\nWill you take the LEFT path, or the RIGHT path?\n");
        println("\nPlease type LEFT or RIGHT: ");
        String choice = stringInput(input).toUpperCase();
        do {
            if (choice.equals("LEFT")) {
                return 1;
            } else if (choice.equals("RIGHT")) {
                return 2;
            } else if (choice.equals("HELP")) {
                help();
            }
            print("Please type LEFT or RIGHT: ");
            choice = stringInput(input).toUpperCase();
        } while (true);
    }

    public static Integer levelTwo(Scanner input) {
        clearConsole();
        println("\nYou have been accosted by an angry demon.");
        println("Will you RUN, or will you FIGHT?\n");
        println("\nPlease type RUN or FIGHT: ");
        String choice = stringInput(input).toUpperCase();
        do {
            if (choice.equals("RUN")) {
                return 1;
            } else if (choice.equals("FIGHT")) {
                return 2;
            } else if (choice.equals("HELP")) {
                help();
            }
            println("Please type RUN or FIGHT: ");
            choice = stringInput(input).toUpperCase();
        } while (true);
    }

    public static Integer battle(HashMap<String, Integer> player, HashMap<String, Integer> enemy, String name) {
        Integer playerDMG = 0;
        Integer enemyDMG = 0;

        do {
            clearConsole();
            draw(name);
            println("Enemy health:");
            enemyStatus(enemy, enemyDMG);
            println("");
            status(player, playerDMG);
            enemyDMG += battleOptions(player, enemy);
            playerDMG += enemyAttack(player, enemy);
            pressEnter();

            if ((player.get("Health") - playerDMG) <= 0) {
                return playerDMG;
            }

        } while (enemy.get("Health") - enemyDMG > 0);

        println(String.format("You have defeated the %d! Congratulations!", name));
        pressEnter();
        return playerDMG;
    }

    public static Integer battleOptions(HashMap<String, Integer> player,
            HashMap<String, Integer> enemy) {
        Integer playerDEF = (int) Math.floor(player.get("Defense"));
        Integer enemyDEF = (int) Math.floor(enemy.get("Defense"));
        Integer playerATK = (int) Math.floor(player.get("Attack"));
        Integer enemyATK = (int) Math.floor(enemy.get("Attack"));
        Integer enemyDMG = 0;

        println("");
        println("What will you do?: ");
        println("");
        println("Attack");
        println("Run");
        Scanner input = new Scanner(System.in);
        do {
            String choice = stringInput(input).toUpperCase();
            println("");
            switch (choice) {
                case "ATTACK":
                    if ((playerATK - enemyDEF) <= 0) {
                        println("You attack the enemy! [1 DMG]");
                        return 1;
                    }
                    println(String.format("You attack the enemy! [%d DMG]", (playerATK - enemyDEF)));
                    return playerATK - enemyDEF;
                case "RUN":
                    println("You attempt to run away...");
                    println("It failed! This function isn't finished yet!");
                    return enemyDMG;
                case "HELP":
                    help();
            }
        } while (true);
    }

    public static Integer enemyAttack(HashMap<String, Integer> player,
            HashMap<String, Integer> enemy) {
        Integer playerDEF = (int) Math.floor((player.get("Defense") - 1) / 3);
        Integer enemyATK = (int) Math.floor(enemy.get("Attack") / 2);
        Integer playerDMG = 0;
        Random randomInt = new Random();

        Integer fun = randomInt.nextInt(500);
        if (fun < 25) {
            println("The enemy's attack missed! [0 DMG]");
            return playerDMG;
        } else if (fun > 475) {
            println(String.format("You've been critically hit by the enemy! [%d DMG]", enemyATK));
            return enemyATK;
        } else {
            if ((enemyATK - playerDEF) <= 0) {
                println("You've been hit by the enemy! [1 DMG]");
                return 1;
            } else {
                println(String.format("You've been hit by the enemy! [%d DMG]", enemyATK - playerDEF));
                return enemyATK - playerDEF;
            }
        }
    }

    public static HashMap<String, Integer> levelUp(HashMap<String, Integer> stats) {
        clearConsole();
        println("YOU'VE LEVELED UP!\n");
        println("");
        statUpgrade(stats, "Health", 10);
        statUpgrade(stats, "Attack", 2);
        statUpgrade(stats, "Defense", 1);
        status(stats, 0);
        pressEnter();
        return stats;
    }

    public static HashMap<String, Integer> statUpgrade(HashMap<String, Integer> stats, String attribute,
            Integer value) {
        println(String.format("Your %s attribute has gone up by %d", attribute, value));
        Integer upgrade = stats.get(attribute) + value;
        stats.put(attribute, upgrade);
        return stats;
    }

    public final static void draw(String name) {
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

    public static void menu() {
        println("\t\t\t\tPress Enter To Start\n");
        Scanner check = new Scanner(System.in);
        check.nextLine();
        return;
    }

    public static void pressEnter() {
        println("\t\t\t\tPress Enter To Continue\n");
        Scanner check = new Scanner(System.in);
        check.nextLine();
        return;
    }

    public static String stringInput(Scanner input) {
        try {
            String value = input.next();
            return value;
        } catch (NoSuchElementException e) {
            String error = "error - no such element exception";
            print(error);
            return null;
        }
    }

    public static Integer intInput(Scanner input) {
        try {
            Integer value = input.nextInt();
            return value;
        } catch (NoSuchElementException e) {
            String error = "error - no such element exception";
            print(error);
            return null;
        }
    }

    public static void help() {
        println("");
        println("\"Help\" - Displays this menu");
        println("\"Status\" - Displays Player status sheet");
        println("\"Save\" - (NOT OPERATIONAL AT THIS TIME) Saves Player progress");
        println("\"Load\" - (NOT OPERATIONAL AT THIS TIME) Loads Player progress");
        println("");
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
}
