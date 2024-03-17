public class Item {

    public static Game g = new Game();
    public String Name;

    public Item() {

    }

    public Item(String name) {
        Name = name;
    }

    public void useItem(String name) {
        switch (name) {
            case "HEALTH POTION":
                Game.println("You have used a health potion!");
                Game.println("You have recovered 10 Health Points!");
                Integer heal = Game.p.stats.get("Damage") - 10;
                if ((heal - 10) < 0) {
                    heal = 0;
                }
                Game.p.stats.put("Damage", heal);
                break;
                
                case "HEALTH ELIXIR":
                Game.println("You have used a health elixir!");
                // g.println("Your total Health has increased by 10!");
                Game.println("You have been fully recovered!");
                // Integer health = p.stats.get("Health") + 10;
                Game.p.stats.put("Damage", 0);
                // p.stats.put("Health", health);
                Game.statUpgrade("Health", 10);
                break;
                
                case "ATTACK ELIXIR":
                Game.println("You have used an attack elixir!");
                // g.println("Your Attack attribute has increased by 3!");
                // Integer atk = p.stats.get("Attack") + 3;
                // p.stats.put("Attack", atk);
                Game.statUpgrade("Attack", 3);
                break;
                
                case "DEFENSE ELIXIR":
                Game.println("You have used a defense elixir!");
                // g.println("Your Defense attribute has increased by 2!");
                // Integer def = p.stats.get("Defense") + 2;
                // p.stats.put("Defense", def);
                Game.statUpgrade("Defense", 2);
                break;

        }
    }
}

