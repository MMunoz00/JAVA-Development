import java.util.HashMap;
import java.util.Random;

public class Enemy {

    public static Character c = new Character();
    public HashMap<String, Integer> stats = new HashMap<String, Integer>();
    public Random rand = new Random();

    public Enemy() {
    }
    
    public Enemy(Integer lvl, Integer hp, Integer atk, Integer def) {
        stats = c.defineStats(lvl, hp, atk, def);
    }

    public Integer enemyAttack(Enemy e) {
        Integer pDEF = (int) Math.floor((Game.p.stats.get("Defense") - 1) / 3);
        Integer eATK = (int) Math.floor(e.stats.get("Attack") / 2);

        Integer fun = rand.nextInt(500);
        if (fun <= 25) {
            Game.println("The enemy's attack missed! [0 DMG]");
            return 0;
        } else if (fun >= 475) {
            Game.println(String.format("You've been critically hit by the enemy! [%d DMG]", eATK));
            return eATK;
        } else {
            if ((eATK - pDEF) <= 0) {
                Game.println("You've been hit by the enemy! [1 DMG]");
                return 1;
            } else {
                Game.println(String.format("You've been hit by the enemy! [%d DMG]", eATK - pDEF));
                return eATK - pDEF;
            }
        }
    }

    public void enemyStatus(Enemy e) {
        Game.println("");
        Game.println("Level " + e.stats.get("Level"));
        Game.println("Health: " + (e.stats.get("Health") - e.stats.get("Damage")) + "/" + e.stats.get("Health"));
        Game.println("");
    }

}
