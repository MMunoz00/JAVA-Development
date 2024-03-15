import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Player {

    public static Game g = new Game();
    public static Character c = new Character();
    public Scanner input = new Scanner(System.in);
    public HashMap<String, Integer> stats = new HashMap<String, Integer>();
    public Item o = new Item();
    public Vector<Item> inventory = new Vector<Item>();

    public Player() {
        stats = c.defineStats(1, 10, 5, 3);
    }

    public void showInventory() {
        for (Item item: inventory) {
            g.println(String.format("[%s]", item.Name));
        }
    }

    public void useInventory() {
        showInventory();
        g.println("Use an item? Y or N:\n");
        String choice = g.stringInput(input).toUpperCase();
        switch (choice) {
            case "Y", "YES":
                Boolean check = false;
                g.println("Which item do you want to use?\n");
                showInventory();
                String i = g.stringInput(input).toUpperCase();
                for (Item item: inventory) {
                    if (i.equals(item.Name)) {
                        check = true;
                        break;
                    }
                }

                if (check == true) {
                    g.println(String.format("Use [%s]? Y or N:\n", i));
                    choice = g.stringInput(input).toUpperCase();
                    switch (choice) {
                        case "Y", "YES":
                            o.useItem(this, i);
                            removeItem(i);
                            break;
                        default:
                            break;
                    }
                    
                } else {
                    g.println(String.format("Item: [%s] does not exist", i));
                    i = g.stringInput(input).toUpperCase();
                }

                break;

            default:
                break;
        }
        return;
    }

    public void giveItem(String name) {
        name = name.toUpperCase();
        Item item = new Item(name);
        this.inventory.add(item);
    }

    public void removeItem(String name) {
        for (Item item: inventory) {
            if (name.equals(item.Name)) {
                inventory.remove(item);
            }
        }
    }

    public void status() {
        g.println("");
        g.println("Level " + stats.get("Level") + "\n");
        g.println("Health: " + (stats.get("Health") - stats.get("Damage")) + "/" + stats.get("Health"));
        g.println("Attack: " + stats.get("Attack"));
        g.println("Defense: " + stats.get("Defense"));
        g.println("");
    }
    
    public void quickStatus() {
        g.println("");
        g.println("Level " + stats.get("Level"));
        g.println("Health: " + (stats.get("Health") - stats.get("Damage")) + "/" + stats.get("Health"));
        g.println("");
    }

}
