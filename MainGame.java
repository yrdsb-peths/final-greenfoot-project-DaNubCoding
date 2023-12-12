import greenfoot.*;

public class MainGame extends Scene {
    public MainGame() {
        super();
        for (int i = 0; i < 20; i++) {
            new Player(this);
        }
    }
}
