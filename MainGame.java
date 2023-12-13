import greenfoot.*;

public class MainGame extends Scene {
    public Player player;
    public Camera camera;
    
    public MainGame() {
        super();
        this.player = new Player(this);
        this.camera = new Camera(this);
    }
}
