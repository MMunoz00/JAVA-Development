import java.util.HashMap;
import java.util.Random;

public class Enemy {

    public static Game g = new Game();
    public static Character c = new Character();

    public Enemy() {

    }

    // public Enemy(Integer hp, Integer atk, Integer def) {
    // HashMap<String, Integer> enemy = c.defineStats(hp, atk, def);
    // }

    public Integer enemyAttack(HashMap<String, Integer> player,
            HashMap<String, Integer> enemy) {
        Integer playerDEF = (int) Math.floor((player.get("Defense") - 1) / 3);
        Integer enemyATK = (int) Math.floor(enemy.get("Attack") / 2);
        Integer playerDMG = 0;
        Random randomInt = new Random();

        Integer fun = randomInt.nextInt(500);
        if (fun <= 25) {
            g.println("The enemy's attack missed! [0 DMG]");
            return playerDMG;
        } else if (fun >= 475) {
            g.println(String.format("You've been critically hit by the enemy! [%d DMG]", enemyATK));
            return enemyATK;
        } else {
            if ((enemyATK - playerDEF) <= 0) {
                g.println("You've been hit by the enemy! [1 DMG]");
                return 1;
            } else {
                g.println(String.format("You've been hit by the enemy! [%d DMG]", enemyATK - playerDEF));
                return enemyATK - playerDEF;
            }
        }
    }

    public void enemyStatus(HashMap<String, Integer> stats, Integer damage) {
        g.println("");
        g.println("Health: " + (stats.get("Health") - damage) + "/" + stats.get("Health"));
        g.println("");
    }

}
