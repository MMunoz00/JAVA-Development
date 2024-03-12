import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class Battle {

    static Game g = new Game();
    static Player p = new Player();
    static Enemy e = new Enemy();
    public Scanner input = new Scanner(System.in);

    public Battle() {

    }

    public Integer battle(HashMap<String, Integer> player, HashMap<String, Integer> enemy, String name) {
        Boolean engage = true;
        Integer playerDMG = 0;
        Integer enemyDMG = 0;

        do {
            g.clearConsole();
            g.draw(name);
            g.println("Enemy health:");
            e.enemyStatus(enemy, enemyDMG);
            g.println("");
            p.status(player, playerDMG);
            enemyDMG += battleOptions(player, enemy);
            playerDMG += e.enemyAttack(player, enemy);

            if ((player.get("Health") - playerDMG) <= 0) {
                return playerDMG;
            } else if (enemy.get("Health") - enemyDMG > 0) {
                g.println(String.format("You have defeated the %s! Congratulations!", name));
                g.pressEnter(input);
                return playerDMG;
            } else if (enemyDMG >= 1000000) {
                engage = false;
            }

        } while (engage == true);

        return playerDMG;
    }

    public Integer battleOptions(HashMap<String, Integer> player,
            HashMap<String, Integer> enemy) {
        Integer enemyDEF = (int) Math.floor(enemy.get("Defense"));
        Integer playerATK = (int) Math.floor(player.get("Attack"));
        Integer enemyDMG = 0;
        Random randomInt = new Random();

        Integer fun = randomInt.nextInt(500);

        g.println("");
        g.println("What will you do?: ");
        g.println("");
        g.println("Attack");
        g.println("Item");
        g.println("Run");
        g.println("");
        Scanner input = new Scanner(System.in);
        do {
            String choice = g.stringInput(input).toUpperCase();
            g.println("");
            switch (choice) {
                case "ATTACK":
                    if (fun <= 20) {
                        g.println("Your attack missed! [0 DMG]");
                        return 0;
                    } else if (fun >= 450) {
                        g.println(String.format("Critical hit! Your attack deals full damage! [%d DMG]", playerATK));
                        return playerATK;
                    } else {
                        if ((playerATK - enemyDEF) <= 0) {
                            g.println("You attack the enemy! [1 DMG]");
                            return 1;
                        } else {
                            g.println(String.format("You attack the enemy! [%d DMG]", (playerATK - enemyDEF)));
                            return playerATK - enemyDEF;
                        }
                    }
                    // case "ITEM":

                case "RUN":
                    g.println("You attempt to run away...");
                    if (fun >= 450) {
                        g.println("Successfully got away!");
                        g.pressEnter(input);
                        return 1000000;
                    }
                    return enemyDMG;
                case "HELP":
                    g.help();
            }
        } while (true);
    }
}
