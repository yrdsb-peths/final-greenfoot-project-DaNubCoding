import greenfoot.*;

public class MainGame extends Scene {
    public Player player;
    public Camera camera;
    
    public MainGame() {
        super();
        
        Log.start();
        
        Log.debug("Creating sprack group from \"block.png\"...");
        SprackGroup blocks = new SprackGroup("block.png", 16);
        Log.debug("Creating sprack group from \"player.png\"...");
        SprackGroup players = new SprackGroup("player.png", 32);
        
        Log.debug("Spawning spracks...");
        new Block(this, blocks, 0, 0);
        new Block(this, blocks, 64, 0);
        
        this.player = new Player(this, players);
        this.camera = new Camera(this);
        
        Log.debug("Complete");
    }
}
