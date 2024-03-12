import java.util.Scanner;

public class Level {

    public static Game g = new Game();

    public Level() {

    }

    public Integer one(Scanner input) {
        g.clearConsole();
        g.println("\nYou are traveling along a dark road.");
        g.println("You come across a fork in the path.");
        g.println("\nWill you take the LEFT path, or the RIGHT path?\n");
        g.println("\nPlease type LEFT or RIGHT: ");
        String choice = g.stringInput(input).toUpperCase();
        do {
            if (choice.equals("LEFT")) {
                return 1;
            } else if (choice.equals("RIGHT")) {
                return 2;
            } else if (choice.equals("HELP")) {
                g.help();
            }
            g.print("Please type LEFT or RIGHT: ");
            choice = g.stringInput(input).toUpperCase();
        } while (true);
    }

    public Integer two(Scanner input) {
        g.clearConsole();
        g.println("\nYou have been accosted by an angry demon.");
        g.println("Will you RUN, or will you FIGHT?\n");
        g.println("\nPlease type RUN or FIGHT: ");
        String choice = g.stringInput(input).toUpperCase();
        do {
            if (choice.equals("RUN")) {
                return 1;
            } else if (choice.equals("FIGHT")) {
                return 2;
            } else if (choice.equals("HELP")) {
                g.help();
            }
            g.println("Please type RUN or FIGHT: ");
            choice = g.stringInput(input).toUpperCase();
        } while (true);
    }
}
