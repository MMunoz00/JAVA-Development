import java.util.Scanner;

public class Main {
    public static Game g = new Game();
    public static Player p = new Player();
    public static Level level = new Level();
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        g.menu(input);

        g.println("Your current status:\n");
        p.status();
        g.pressEnter();
        do {
            switch (g.level) {
                case 1:
                    g.level = level.one(p, input);
                    break;
                case 2:
                    g.level = level.two(p, input);
                    break;
                default:
                    g.run = false;
            }
        } while (g.run != false);
    }
}
