import java.util.Scanner;
import java.util.Random;

public class Battle {
    
    public static Game g = new Game();
    public static Enemy e = new Enemy();
    public Scanner input = new Scanner(System.in);
    public Random rand = new Random();
    
    public Battle() {
    }

    public void battle(Player p, Enemy e, String name) {
        Boolean engage = true;
        Integer pDMG = p.stats.get("Damage");
        Integer eDMG = e.stats.get("Damage");

        do {
            g.clearConsole();
            g.draw(name);
            g.println(name);
            e.enemyStatus(e);
            g.println("");
            p.quickStatus();
            pDMG += battleOptions(p, e);
            eDMG += e.enemyAttack(p, e);
            p.stats.put("Damage", eDMG);
            e.stats.put("Damage", pDMG);
            g.pressEnter();

            if ((p.stats.get("Health") - p.stats.get("Damage")) <= 0) {
                g.gameOver();
                break;
            } else if (e.stats.get("Health") - e.stats.get("Damage") <= 0) {
                g.println(String.format("You have defeated the %s! Congratulations!", name));
                g.pressEnter();
                g.clearConsole();
                g.draw("Chest");
                g.println("You found a treasure chest!\n");
                g.println("Inside, you find an elixir of life!");
                g.println("Drinking it fills you with vitality!\n");
                g.statUpgrade(p, "Health", 5);
                g.pressEnter();
                break;
            } else if (e.stats.get("Damage") >= 1000000) {
                engage = false;
            }

        } while (engage == true);
    }

    public Integer battleOptions(Player p, Enemy e) {
        Integer eDEF = (int) Math.floor(e.stats.get("Defense"));
        Integer pATK = (int) Math.floor(p.stats.get("Attack"));

        Integer fun = rand.nextInt(500);

        g.println("");
        g.println("What will you do?: ");
        g.println("");
        g.println("Attack");
        g.println("Item");
        g.println("Run\n");
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
                        g.println(String.format("Critical hit! Your attack deals full damage! [%d DMG]", pATK));
                        return pATK;
                    } else {
                        if ((pATK - eDEF) <= 0) {
                            g.println("You attack the enemy! [1 DMG]");
                            return 1;
                        } else {
                            g.println(String.format("You attack the enemy! [%d DMG]", (pATK - eDEF)));
                            return pATK - eDEF;
                        }
                    }

                case "ITEM":
                    p.useInventory();
                    choice = g.stringInput(input).toUpperCase();
                    break;

                case "RUN":
                    g.println("You attempt to run away...");
                    if (fun >= 450) {
                        g.println("Successfully got away!\n");
                        g.pressEnter();
                        return 1000000;
                    } else {
                        g.println("Couldn't get away!");
                        g.pressEnter();
                        return 0;
                    }

                case "HELP":
                    g.help();
                    choice = g.stringInput(input).toUpperCase();
                    break;
                    
                case "STATUS":
                    p.status();
                    choice = g.stringInput(input).toUpperCase();
                    break;
                    
                case "SAVE":
                    g.println("Save function is not completed at this time, sorry!");
                    choice = g.stringInput(input).toUpperCase();
                    break;
                    
                case "LOAD":
                    g.println("Load function is not completed at this time, sorry!");
                    choice = g.stringInput(input).toUpperCase();
                    break;

                default:
                    g.println("");
                    g.println("Please select one of the following options: ");
                    g.println("");
                    g.println("Attack");
                    g.println("Item");
                    g.println("Run\n");
                    choice = g.stringInput(input).toUpperCase();        

            }
        } while (true);
    }
}
