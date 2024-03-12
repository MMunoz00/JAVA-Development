import java.util.HashMap;

public class Character {

    public Character() {

    }

    public HashMap<String, Integer> defineStats(Integer hp, Integer atk, Integer def) {
        HashMap<String, Integer> stats = new HashMap<String, Integer>();
        stats.put("Health", hp);
        stats.put("Attack", atk);
        stats.put("Defense", def);
        return stats;
    }

}
