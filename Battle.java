import java.util.Random;

public class Battle {
    
    public static Enemy e = new Enemy();
    public static Random rand;
    
    public Battle() {
    }
    
    public static void battle(Enemy e, String name) {
        rand = new Random();
        Boolean engage = true;
        Integer pDMG = Game.p.stats.get("Damage");
        Integer eDMG = e.stats.get("Damage");

        do {
            Game.clearConsole();
            Game.draw(name);
            Game.println(name);
            e.enemyStatus(e);
            Game.println("");
            Game.p.quickStatus();
            pDMG += battleOptions(e);
            eDMG += e.enemyAttack(e);
            Game.p.stats.put("Damage", eDMG);
            e.stats.put("Damage", pDMG);
            Game.pressEnter();

            if ((Game.p.stats.get("Health") - Game.p.stats.get("Damage")) <= 0) {
                Game.gameOver();
                break;
            } else if (e.stats.get("Health") - e.stats.get("Damage") <= 0) {
                Game.println(String.format("You have defeated the %s! Congratulations!", name));
                Game.pressEnter();
                Game.clearConsole();
                Game.draw("Chest");
                Game.println("You found a treasure chest!\n");
                Game.println("Inside, you find an elixir of life!");
                Game.println("Drinking it fills you with vitality!\n");
                Game.statUpgrade("Health", 5);
                Game.pressEnter();
                break;
            } else if (e.stats.get("Damage") >= 1000000) {
                engage = false;
            }

        } while (engage == true);
    }

    public static Integer battleOptions(Enemy e) {
        Integer eDEF = (int) Math.floor(e.stats.get("Defense"));
        Integer pATK = (int) Math.floor(Game.p.stats.get("Attack"));

        Integer fun = rand.nextInt(500);

        do {
            Game.println("");
            Game.println("What will you do?: ");
            Game.println("");
            Game.println("Attack");
            Game.println("Item");
            Game.println("Run\n");
            String choice = Game.stringInput().toUpperCase();
            Game.println("");
            switch (choice) {
                case "ATTACK":
                    if (fun <= 20) {
                        Game.println("Your attack missed! [0 DMG]");
                        return 0;
                    } else if (fun >= 450) {
                        Game.println(String.format("Critical hit! Your attack deals full damage! [%d DMG]", pATK));
                        return pATK;
                    } else {
                        if ((pATK - eDEF) <= 0) {
                            Game.println("You attack the enemy! [1 DMG]");
                            return 1;
                        } else {
                            Game.println(String.format("You attack the enemy! [%d DMG]", (pATK - eDEF)));
                            return pATK - eDEF;
                        }
                    }

                case "ITEM":
                    Game.p.useInventory();
                    break;

                case "RUN":
                    Game.println("You attempt to run away...");
                    if (fun >= 450) {
                        Game.println("Successfully got away!\n");
                        Game.pressEnter();
                        return 1000000;
                    } else {
                        Game.println("Couldn't get away!");
                        return 0;
                    }

                case "HELP":
                    Game.help();
                    break;
                    
                case "STATUS":
                    Game.p.status();
                    // choice = Game.stringInput().toUpperCase();
                    break;
                    
                case "SAVE":
                    Game.println("Save function is not completed at this time, sorry!");
                    // choice = Game.stringInput().toUpperCase();
                    break;
                    
                case "LOAD":
                    Game.println("Load function is not completed at this time, sorry!");
                    // choice = Game.stringInput().toUpperCase();
                    break;

                default:
                    Game.println("");
                    Game.println("Please select one of the following options: ");
                    Game.println("");
                    Game.println("Attack");
                    Game.println("Item");
                    Game.println("Run\n");
                    // choice = Game.stringInput().toUpperCase();  
                    break;      

            }
        } while (true);
    }
}
