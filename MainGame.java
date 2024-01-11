import greenfoot.*;
import java.util.*;

public class MainGame extends Scene {
    public static SprackGroup grassSprackGroup;
    public static SprackGroup dirtSprackGroup;
    public static SprackGroup playerSprackGroup;
    
    static {
        Log.start();
        Log.debug("Loading static sprack groups:");
        Log.debug("Creating sprack group from \"grass.png\"...");
        grassSprackGroup = new SprackGroup("grass.png", 16);
        Log.debug("Creating sprack group from \"dirt.png\"...");
        dirtSprackGroup = new SprackGroup("dirt.png", 16);
        Log.debug("Creating sprack group from \"player.png\"...");
        playerSprackGroup = new SprackGroup("player.png", 16);
    }
    
    public Player player;
    public Camera camera;
    
    public MainGame() {
        super();
        
        Log.debug("Starting game...");
        
        Log.debug("Spawning spracks...");
        for (int x = 0; x < 12; x++) {
            for (int z = 0; z < 12; z++) {
                new Block(this, grassSprackGroup, x, 0, z);
            }
        }
        new Block(this, grassSprackGroup, 9, 1, 3);
        new Block(this, grassSprackGroup, 3, 1, 4);
        new Block(this, grassSprackGroup, 4, 1, 4);
        new Block(this, grassSprackGroup, 5, 1, 4);
        new Block(this, grassSprackGroup, 4, 1, 3);
        new Block(this, grassSprackGroup, 4, 1, 5);
        new Block(this, grassSprackGroup, 4, 2, 4);
        for (int x = 7; x < 10; x++) {
            for (int y = 1; y < 4; y++) {
                new Block(this, dirtSprackGroup, x, y, 9);
            }
        }
        new Block(this, grassSprackGroup, 7, 3, 8);
        new Block(this, grassSprackGroup, 8, 3, 8);
        new Block(this, grassSprackGroup, 9, 3, 8);
        new Block(this, grassSprackGroup, 8, 2, 6);
        
        this.player = new Player(this);
        this.camera = new Camera(this);
        
        Log.debug("Complete");
    }
    
    private class ZIndexComparator implements Comparator<Sprack> {
        private Vector3 camPos;
        
        public ZIndexComparator(Camera camera) {
            this.camPos = new Vector3(0, 10000, 0);
            this.camPos.xy.rotate$(90 - camera.getVerAngle());
            this.camPos.xz.rotate$(camera.getHorAngle() - 90);
            this.camPos.plus$(camera.getPos());
        }
        
        public int compare(Sprack a, Sprack b) {
            return -Double.compare(a.pos.distanceTo(this.camPos), b.pos.distanceTo(this.camPos));
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
