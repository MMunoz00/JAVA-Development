import java.util.Scanner;

public class Level {

    public static Enemy e = new Enemy();
    public Scanner input = new Scanner(System.in);
    
    public Level() {
    }
    
    public Integer one() {
        Boolean run = true;

        Game.clearConsole();
        Game.println("Level 1\n");
        Game.println("\nYou are traveling along a dark road.");
        Game.println("You come across a fork in the path.");
        Game.println("\nWill you take the LEFT path, or the RIGHT path?\n");
        Game.println("\nPlease type LEFT or RIGHT: \n");
        String choice = Game.stringInput();
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
                    choice = Game.stringInput();
                    break;

                case "STATUS":
                    Game.p.status();
                    choice = Game.stringInput();
                    break;
                
                case "LEVEL":
                    Game.println(String.format("Currently in level %d", Game.level));
                    choice = "";
                    break;
                
                case "SAVE":
                    Game.save();
                    choice = "";
                    break;
                    
                case "LOAD":
                    Game.load();
                    return Game.level;

                case "INVENTORY":
                    Game.p.showInventory();
                    choice = "";
                    break;

                default:
                    Game.print("Please type LEFT or RIGHT: \n");
                    choice = Game.stringInput();
                    break;
            }
        } while (run);

        return Game.level;
    }

    public Integer two() {

        Boolean run = true;

        Game.clearConsole();
        Game.println("Level 2\n");
        Game.draw("Demon");
        Game.println("\nYou have been accosted by an angry demon.");
        Game.println("Will you RUN, or will you FIGHT?\n");
        Game.println("\nPlease type RUN or FIGHT: \n");
        String choice = Game.stringInput();
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
                    choice = Game.stringInput();
                    break;

                case "STATUS":
                    Game.p.status();
                    choice = Game.stringInput();
                    break;
                    
                case "LEVEL":
                    Game.println(String.format("Currently in level %d", Game.level));
                    choice = "";
                    break;
                
                case "SAVE":
                    Game.save();
                    choice = "";
                    break;

                case "LOAD":
                    Game.load();
                    return Game.level;

                case "INVENTORY":
                    Game.p.showInventory();
                    choice = "";
                    break;

                default:
                    Game.println("Please type RUN or FIGHT: \n");
                    choice = Game.stringInput();
                    break;
            }
        } while (run);

        return Game.level;
    }

    public Integer three() {
        Game.clearConsole();
        Game.println("Level 3\n\n");
        Game.draw("Merchant");
        Game.println("\n\nYou're stopped by a wandering trader\n");
        Game.println("\"I've got some strange drinks here, care to take one?\"");
        String choice;
        Boolean loop = true;
        while (loop) {
            Game.println("Red drink");
            Game.println("Yellow drink");
            Game.println("Blue drink");
            Game.println("\"Which will you choose?\"");
            choice = Game.stringInput();
            switch (choice) {
                case "RED DRINK", "RED":
                    Game.p.giveItem("health elixir");
                    Game.println("You have received\nHealth Elixir: x1");
                    Game.pressEnter();
                    loop = false;
                    break;
                case "YELLOW DRINK", "YELLOW":
                    Game.p.giveItem("attack elixir");
                    Game.println("You have received\nAttack Elixir: x1");
                    Game.pressEnter();
                    loop = false;
                    break;
                case "BLUE DRINK", "BLUE":
                    Game.p.giveItem("defense elixir");
                    Game.println("You have received\nDefense Elixir: x1");
                    Game.pressEnter();
                    loop = false;
                    break;
                default:
                    Game.println("\"I don't know what that is.\"");
                    choice = Game.stringInput();
            }
        }

        Game.clearConsole();
        Game.println("You continue on your journey and you come across a river.");
        Game.println("Set by the river, you find three things:");
        Game.println("A canoe, a fishing rod, and a box.");
        Game.println("Would you like to investigate? Y or N?");
        choice = Game.stringInput();
        int option = 0;
        int stolen = 0;
        switch (choice) {
            case "YES", "Y":
                loop = true;
                int checks = 0;
                while (loop) {
                    Game.clearConsole();
                    Game.println("What would you like to investigate?");
                    Game.println("The Canoe, the Fishing Rod, the Box, or Stop investigating?");
                    choice = Game.stringInput();
                    switch (choice) {
                        case "CANOE":
                            checks++;
                            Game.println("Investigating the canoe, you find that it's in decent shape.");
                            Game.println("You intuit that you can use this canoe to get across the river.");
                            Game.println("Go now? Y or N");
                            choice = Game.stringInput();
                            switch (choice) {
                                case "YES", "Y":
                                    loop = false;
                                    option = 1;
                                    continue;
                                case "NO", "N":
                                    continue;
                                default:
                                    Game.println("Invalid input, please try again:");
                                    choice = Game.stringInput();
                                    break;
                            }
                            break;
                        case "FISHING ROD", "FISHINGROD":
                            checks++;
                            Game.println("The fishing rod is well maintained, but shows signs of wear.");
                            Game.println("Whoever owns this must use it often and care about this particular rod.");
                            break;
                        case "BOX":
                            checks++;
                            Game.println("Inside the box you find two bottles of a red liquid.");
                            Game.println("Would you like to take them? Y or N");
                            choice = Game.stringInput();
                            switch (choice) {
                                case "YES", "Y":
                                    Game.p.giveItem("health potion");
                                    Game.p.giveItem("health potion");
                                    Game.println("You have received\nHealth Potion x2\n");
                                    break;
                                case "NO", "N":
                                    Game.println("You hold back the instinct to steal someone's stuff.");
                                    break;
                            }
                            break;
                        case "STOP", "STOP INVESTIGATING", "STOPINVESTINGATING":
                            loop = false;
                            break;
                        default:
                            Game.println("Invalid input, please try again:");
                            choice = Game.stringInput();
                            break;
                    }
                }
                break;
            case "NO", "N":
                break;
            default:
                Game.println("Invalid input, please try again:");
                choice = Game.stringInput();
                break;
        }
        if (option == 1) {

        }

        
        Game.pressEnter();

        return Game.level;
    }
    public Integer four(Player p) {
        Game.clearConsole();
        return Game.level;
    }
    public Integer five(Player p) {
        Game.clearConsole();
        return Game.level;
    }
}
