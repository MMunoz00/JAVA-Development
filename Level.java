import java.util.Scanner;

public class Level {

    public static Enemy e = new Enemy();
    public Scanner input = new Scanner(System.in);
    
    public Level() {
    }
    
    public Integer one(Player p) {
        Boolean run = true;

        Game.clearConsole();
        Game.println("\nYou are traveling along a dark road.");
        Game.println("You come across a fork in the path.");
        Game.println("\nWill you take the LEFT path, or the RIGHT path?\n");
        Game.println("\nPlease type LEFT or RIGHT: \n");
        String choice = Game.stringInput().toUpperCase();
        do {
            switch (choice) {
                case "LEFT":
                    run = false;
                    Game.clearConsole();
                    Game.levelUp();
                    Game.p.status();
                    Game.pressEnter();
                    Game.level++;
                    break;

                case "RIGHT":
                    run = false;
                    Game.clearConsole();
                    Game.println("You have received:\nHealth potion: x1\nHealth elixir: x1\nAttack elixir: x1\nDefense elixir: x1\n");
                    // g.statUpgrade(p, "Defense", 10);
                    Game.p.giveItem("health potion");
                    Game.p.giveItem("health elixir");
                    Game.p.giveItem("attack elixir");
                    Game.p.giveItem("defense elixir");
                    Game.p.sortInventory();
                    Game.pressEnter();
                    Game.level++;
                    break;

                case "HELP":
                    Game.help();
                    choice = Game.stringInput().toUpperCase();
                    break;

                case "STATUS":
                    Game.p.status();
                    choice = Game.stringInput().toUpperCase();
                    break;
                
                case "SAVE":
                    Game.println("Save function is not completed at this time, sorry!");
                    choice = Game.stringInput().toUpperCase();
                    break;

                case "LOAD":
                    Game.println("Load function is not completed at this time, sorry!");
                    choice = Game.stringInput().toUpperCase();
                    break;
                    
                default:
                    Game.print("Please type LEFT or RIGHT: \n");
                    choice = Game.stringInput().toUpperCase();
                    break;
            }
        } while (run);

        return Game.level;
    }

    public Integer two(Player p) {

        Boolean run = true;

        Game.clearConsole();
        Game.println("\nYou have been accosted by an angry demon.");
        Game.println("Will you RUN, or will you FIGHT?\n");
        Game.println("\nPlease type RUN or FIGHT: \n");
        String choice = Game.stringInput().toUpperCase();
        do {
            switch (choice) {
                case "RUN":
                    Game.println("The demon prevents you from fleeing.");
                    Game.println("You have been weakened by your cowardice.");
                    Game.println("");
                    Game.statUpgrade("Attack", -1);
                    Game.statUpgrade("Defense", -1);
                    Game.println("");
                    Game.pressEnter();
                    choice = "FIGHT";
                    break;

                case "FIGHT":
                    run = false;
                    Game.clearConsole();
                    Enemy e = new Enemy(3, 20, 7, 5);
                    
                    Game.p.status();
                    Battle.battle(e, "Demon");
                    Game.level++;
                    break;

                case "HELP":
                    Game.help();
                    choice = Game.stringInput().toUpperCase();
                    break;

                case "STATUS":
                    Game.p.status();
                    choice = Game.stringInput().toUpperCase();
                    break;
                
                case "SAVE":
                    Game.println("Save function is not completed at this time, sorry!");
                    choice = Game.stringInput().toUpperCase();
                    break;

                case "LOAD":
                    Game.println("Load function is not completed at this time, sorry!");
                    choice = Game.stringInput().toUpperCase();
                    break;

                default:
                    Game.println("Please type RUN or FIGHT: \n");
                    choice = Game.stringInput().toUpperCase();
                    break;
            }
        } while (run);

        return Game.level;
    }
}
