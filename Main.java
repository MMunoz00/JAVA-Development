public class Main {
    
    public static void main(String[] args) {
        // Game g = new Game();
        
        Game.menu();

        Game.println("Your current status:\n");
        Game.p.status();
        Game.pressEnter();
        do {
            switch (Game.level) {
                case 1:
                    Game.level = Game.l.one(Game.p);
                    break;
                case 2:
                    Game.level = Game.l.two(Game.p);
                    break;
                default:
                    Game.run = false;
            }
        } while (Game.run != false);
    }
}
