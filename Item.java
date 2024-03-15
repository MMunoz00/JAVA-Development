public class Item {

    public static Game g = new Game();
    public String Name;

    public Item() {

    }

    public Item(String name) {
        Name = name;
    }

    public void useItem(Player p, String name) {
        switch (name) {
            case "HEALTH POTION":
                g.println("You have used a health potion!");
                g.println("You have recovered 10 Health Points!");
                Integer heal = p.stats.get("Damage") - 10;
                if ((heal - 10) < 0) {
                    heal = 0;
                }
                p.stats.put("Damage", heal);
                break;
                
                case "HEALTH ELIXIR":
                g.println("You have used a health elixir!");
                // g.println("Your total Health has increased by 10!");
                g.println("You have been fully recovered!");
                // Integer health = p.stats.get("Health") + 10;
                p.stats.put("Damage", 0);
                // p.stats.put("Health", health);
                g.statUpgrade(p, "Health", 10);
                break;
                
                case "ATTACK ELIXIR":
                g.println("You have used an attack elixir!");
                // g.println("Your Attack attribute has increased by 3!");
                // Integer atk = p.stats.get("Attack") + 3;
                // p.stats.put("Attack", atk);
                g.statUpgrade(p, "Attack", 3);
                break;
                
                case "DEFENSE ELIXIR":
                g.println("You have used a defense elixir!");
                // g.println("Your Defense attribute has increased by 2!");
                // Integer def = p.stats.get("Defense") + 2;
                // p.stats.put("Defense", def);
                g.statUpgrade(p, "Defense", 2);
                break;

        }
    }
}

