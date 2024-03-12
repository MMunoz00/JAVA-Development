import java.util.HashMap;
import java.util.Scanner;

public class Player {

    public static Game g = new Game();
    public Scanner input = new Scanner(System.in);
    public HashMap<String, Integer> stats = defineStats();
    public Integer dmg = 0;

    public Player() {
    }

    public HashMap<String, Integer> defineStats() {
        HashMap<String, Integer> stats = new HashMap<String, Integer>();
        stats.put("Level", 1);
        stats.put("Health", 10);
        stats.put("Attack", 5);
        stats.put("Defense", 3);
        return stats;
    }

    public void status(HashMap<String, Integer> stats, Integer damage) {
        g.println("");
        g.println("Level" + stats.get("Level") + "\n");
        g.println("Health: " + (stats.get("Health") - damage) + "/" + stats.get("Health"));
        g.println("Attack: " + stats.get("Attack"));
        g.println("Defense: " + stats.get("Defense"));
        g.println("");
        g.pressEnter(input);
    }

}
