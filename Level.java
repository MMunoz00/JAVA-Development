import java.util.Scanner;

public class Level {

    public static Game g = new Game();
    public static Player p = new Player();
    public static Enemy e = new Enemy();
    public static Battle b = new Battle();
    public Scanner input = new Scanner(System.in);
    
    public Level() {
    }
    
    public Integer one(Player p, Scanner input) {
        Boolean run = true;

        g.clearConsole();
        g.println("\nYou are traveling along a dark road.");
        g.println("You come across a fork in the path.");
        g.println("\nWill you take the LEFT path, or the RIGHT path?\n");
        g.println("\nPlease type LEFT or RIGHT: \n");
        String choice = g.stringInput(input).toUpperCase();
        do {
            switch (choice) {
                case "LEFT":
                    run = false;
                    g.clearConsole();
                    g.levelUp(p);
                    p.status();
                    g.pressEnter();
                    g.level++;
                    break;

                case "RIGHT":
                    run = false;
                    g.clearConsole();
                    g.println("You have received:\nHealth potion: x1\nHealth elixir: x1\nAttack elixir: x1\nDefense elixir: x1\n");
                    // g.statUpgrade(p, "Defense", 10);
                    p.giveItem("health potion");
                    p.giveItem("health elixir");
                    p.giveItem("attack elixir");
                    p.giveItem("defense elixir");
                    g.pressEnter();
                    g.clearConsole();
                    p.showInventory();
                    g.pressEnter();
                    g.level++;
                    break;

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
                    g.print("Please type LEFT or RIGHT: \n");
                    choice = g.stringInput(input).toUpperCase();
                    break;
            }
        } while (run);

        return g.level;
    }

    public Integer two(Player p, Scanner input) {

        Boolean run = true;

        g.clearConsole();
        g.println("\nYou have been accosted by an angry demon.");
        g.println("Will you RUN, or will you FIGHT?\n");
        g.println("\nPlease type RUN or FIGHT: \n");
        String choice = g.stringInput(input).toUpperCase();
        do {
            switch (choice) {
                case "RUN":
                    g.println("The demon prevents you from fleeing.");
                    g.println("You have been weakened by your cowardice.");
                    g.println("");
                    g.statUpgrade(p, "Attack", -1);
                    g.statUpgrade(p, "Defense", -1);
                    g.println("");
                    g.pressEnter();
                    choice = "FIGHT";
                    break;

                case "FIGHT":
                    run = false;
                    g.clearConsole();
                    Enemy e = new Enemy(3, 20, 7, 5);
                    
                    p.status();
                    b.battle(p, e, "Demon");
                    g.level++;
                    break;

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
                    g.println("Please type RUN or FIGHT: \n");
                    choice = g.stringInput(input).toUpperCase();
                    break;
            }
        } while (run);

        return g.level;
    }
}
