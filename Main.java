import java.util.Scanner;

// import javax.print.attribute.IntegerSyntax;

// import java.io.Console;

public class Main {
    public static Game g = new Game();
    public static Player p = new Player();
    public static Character c = new Character();
    public static Battle b = new Battle();
    public static Level level = new Level();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        g.clearConsole();
        g.draw("title");
        g.menu(input);

        g.println("Your current status:\n");
        p.status(p.stats, p.dmg);
        do {
            switch (g.level) {
                case 1:
                    switch (level.one(input)) {
                        case 1:
                            g.clearConsole();
                            g.levelUp(p.stats);
                            g.level++;
                            break;
                        case 2:
                            g.clearConsole();
                            g.statUpgrade(p.stats, "Defense", 5);
                            p.status(p.stats, p.dmg);
                            g.level++;
                            break;
                    }
                    break;
                case 2:
                    switch (level.two(input)) {
                        case 1:
                            g.println("Coward...\n");
                            g.pressEnter(input);
                            break;
                        case 2:
                            p.dmg = b.battle(p.stats, c.defineStats(22, 8, 5), "devil");
                            if ((p.stats.get("Health") - p.dmg) <= 0) {
                                g.gameOver();
                                g.run = false;
                            } else {
                                g.levelUp(p.stats);
                            }
                            break;
                    }
                    break;
            }
        } while (g.run != false);
        input.close();
    }
}
