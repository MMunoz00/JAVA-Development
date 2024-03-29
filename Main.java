public class Main {
    
    public static void main(String[] args) {
        
        Game.menu();

        Game.println("Your current status:\n");
        Game.p.status();
        Game.pressEnter();
        do {
            switch (Game.level) {
                case 1:
                    Game.level = Game.l.one();
                    break;
                case 2:
                    Game.level = Game.l.two();
                    break;
                // case 3:
                //     Game.level = Game.l.three();
                //     break;
                // case 4:
                //     Game.level = Game.l.four();
                //     break;
                // case 5:
                //     Game.level = Game.l.five();
                //     break;
                default:
                    Game.run = false;
            }
        } while (Game.run != false);
    }
}
