import greenfoot.*;

public class MainGame extends Scene {
    public Player player;
    public Camera camera;
    
    public MainGame() {
        super();
        // for (int x = 0; x < this.w; x += 64){
            // for (int y = 0; y < this.h; y += 64) {
                // new Block(this, x, y);
            // }
        // }
        SprackGroup blocks = new SprackGroup("block.png", 16);
        new Block(this, blocks, 0, 0);
        new Block(this, blocks, 64, 0);
        
        SprackGroup players = new SprackGroup("player.png", 32);
        this.player = new Player(this, players);
        this.camera = new Camera(this);
    }
}
