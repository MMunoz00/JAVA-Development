import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;
import java.util.Iterator;


public class Player {

    public static Character c = new Character();
    public HashMap<String, Integer> stats = new HashMap<String, Integer>();
    public Item o = new Item();
    public Vector<Item> inventory = new Vector<Item>();

    public Player() {
        stats = c.defineStats(1, 10, 5, 3);
    }

    public void showInventory() {
        for (Item item: inventory) {
            Game.println(String.format("[%s]", item.Name));
        }
    }

    public String printInventory() {
        String items = "";
        for (Item item: inventory) {
            items += String.format("%s,", item.Name);
        }
        if (items.length() > 0) {
            items = items.substring(0, items.length() - 1);
        }
        return items;
    }

    public void useInventory() {
        if (inventory.isEmpty()) {
            Game.println("You currently have no items.");
            return;
        }
        showInventory();
        Game.println("Use an item? Y or N:\n");
        String choice = Game.stringInput();
        switch (choice) {
            case "Y", "YES":
                Boolean check = false;
                Game.println("Which item do you want to use?\n");
                showInventory();
                String i = Game.stringInput();
                for (Item item: inventory) {
                    if (i.equals(item.Name)) {
                        check = true;
                        break;
                    }
                }

                if (check == true) {
                    Game.println(String.format("Use [%s]? Y or N:\n", i));
                    choice = Game.stringInput();
                    switch (choice) {
                        case "Y", "YES":
                            o.useItem(i);
                            this.removeItem(i);
                            break;
                        default:
                            break;
                    }
                    
                } else {
                    Game.println(String.format("Item: [%s] does not exist", i));
                    i = Game.stringInput();
                }

                return;

            default:
                return;
        }
    }

    public void giveItem(String name) {
        if (inventory.size() < 8) {
            name = name.toUpperCase();
            Item item = new Item(name);
            this.inventory.add(item);
            return;
        }
        else {
            Game.println("Too many items in inventory!\n");
            Game.println("Choose an item to replace, or toss the new item.");
            Game.p.showInventory();
            String choice = Game.stringInput();
            while (true) {
                switch (choice) {
                    case "HEALTH POTION":
                        Game.p.removeItem("health potion");
                        Game.p.giveItem(name);
                        return;
                    case "HEALTH ELIXIR":
                        Game.p.removeItem("health elixir");
                        Game.p.giveItem(name);
                        return;
                    case "ATTACK ELIXIR":
                        Game.p.removeItem("attack elixir");
                        Game.p.giveItem(name);
                        return;
                    case "DEFENSE ELIXIR":
                        Game.p.removeItem("defense elixir");
                        Game.p.giveItem(name);
                        return;
                    case "TOSS":
                        Game.println(String.format("$s has been discarded"));
                        return;
                    default:
                        Game.println("Invalid input, try again");
                        choice = Game.stringInput();
                        break;
                }
            }
        }
    }

    public void removeItem(String name) {
        Iterator<Item> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (name.equals(item.Name)) {
                iterator.remove();
                return;
            }
        }
    }

    public void sortInventory() {
        Collections.sort(inventory, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.Name.compareTo(item2.Name);
            }
        });
    }

    public void status() {
        Game.println("");
        Game.println("Level " + stats.get("Level") + "\n");
        Game.println("Health: " + (stats.get("Health") - stats.get("Damage")) + "/" + stats.get("Health"));
        Game.println("Attack: " + stats.get("Attack"));
        Game.println("Defense: " + stats.get("Defense"));
        Game.println("");
    }
    
    public void quickStatus() {
        Game.println("");
        Game.println("Level " + stats.get("Level"));
        Game.println("Health: " + (stats.get("Health") - stats.get("Damage")) + "/" + stats.get("Health"));
        Game.println("");
    }



}
