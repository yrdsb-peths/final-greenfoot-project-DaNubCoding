import greenfoot.*;
import java.util.*;

public class MainGame extends Scene {
    static SprackGroup blocks;
    static SprackGroup players;
    
    static {
        Log.start();
        Log.debug("Loading static sprack groups:");
        Log.debug("Creating sprack group from \"block.png\"...");
        blocks = new SprackGroup("block.png", 16);
        Log.debug("Creating sprack group from \"player.png\"...");
        players = new SprackGroup("player.png", 32);
    }
    
    public Camera camera;
    
    public MainGame() {
        super();
        
        Log.debug("Starting game...");
        
        Log.debug("Spawning spracks...");
        for (int x = 0; x < 12; x++) {
            for (int z = 0; z < 12; z++) {
                new Block(this, blocks, x, 0, z);
            }
        }
        new Block(this, blocks, 8, 1.5, 3);
        new Block(this, blocks, 9, 1, 3);
        new Block(this, blocks, 3, 1, 4);
        new Block(this, blocks, 4, 1, 4);
        new Block(this, blocks, 5, 1, 4);
        new Block(this, blocks, 4, 1, 3);
        new Block(this, blocks, 4, 1, 5);
        new Block(this, blocks, 4, 2, 4);
        for (int x = 7; x < 10; x++) {
            for (int y = 1; y < 4; y++) {
                new Block(this, blocks, x, y, 9);
            }
        }
        
        this.camera = new Camera(this);
        
        Log.debug("Complete");
    }
    
    private class ZIndexComparator implements Comparator<Sprack> {
        private Camera camera;
        
        public ZIndexComparator(Camera camera) {
            this.camera = camera;
        }
        
        private double nearness(Sprack sprack, Vector2 rotVec) {
            return sprack.pos.x.get() * Math.signum(rotVec.x.get()) + sprack.pos.y.get() + sprack.pos.z.get() * Math.signum(rotVec.y.get());
        }
        
        public int compare(Sprack a, Sprack b) {
            Vector2 rotVec = new Vector2(0, 1).rotate(this.camera.getHorAngle());
            return Double.compare(this.nearness(a, rotVec), this.nearness(b, rotVec));
        }
    }
    
    public void act() {
        Collections.sort(Sprack.spracks, new ZIndexComparator(this.camera));
        
        for (int i = 0; i < Sprack.spracks.size(); i++) {
            Sprack.spracks.get(i).remove();
            Sprack.spracks.get(i).add();
        }
    }
}
