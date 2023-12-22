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
    
    public Player player;
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
        
        this.player = new Player(this, players);
        this.camera = new Camera(this);
        
        Log.debug("Complete");
    }
    
    private class ZIndexComparator implements Comparator<Sprack> {
        private Camera camera;
        
        public ZIndexComparator(Camera camera) {
            this.camera = camera;
        }
        
        private double nearness(Sprack sprack, Vector2 rotationVec) {
            return sprack.pos.x.times(rotationVec.x) + sprack.getBottom() + sprack.pos.z.times(rotationVec.y);
        }
        
        public int compare(Sprack a, Sprack b) {
            Vector2 rotationVec = new Vector2(0, 1).rotate(this.camera.getHorAngle());
            double aNearness = this.nearness(a, rotationVec);
            double bNearness = this.nearness(b, rotationVec);
            if (aNearness > bNearness) {
                return 1;
            } else if (aNearness < bNearness) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    
    public void act() {
        // try {
            Collections.sort(Sprack.spracks, new ZIndexComparator(this.camera));
        // } catch (IllegalArgumentException e) {
            // i actually dont know how to solve this ;-; god help
            // the array maintains the order of the previous sort if it enters this block
        // }
        
        for (int i = 0; i < Sprack.spracks.size(); i++) {
            Sprack.spracks.get(i).remove();
            Sprack.spracks.get(i).add();
        }
    }
}
