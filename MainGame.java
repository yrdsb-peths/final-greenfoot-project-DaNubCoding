import greenfoot.*;
import java.util.*;

/**
 * Class that contains global logic and spawns sprites for the main game.
 */
public class MainGame extends Scene {
    public static SprackGroup grassSprackGroup;
    public static SprackGroup dirtSprackGroup;
    public static SprackGroup playerSprackGroup;
    
    public Player player;
    public Camera camera;
    private Block mouseOver;
    
    /**
     * Intializes the sprites in the main game.
     */
    public MainGame() {
        super();
        
        Debug.start();
        
        Sprack.spracks = new ArrayList<Sprack>();
        Block.blocks = new HashMap<Vector3, Block>();
        
        Debug.log("Loading sprack groups...");
        grassSprackGroup = new SprackGroup("grass.png", 16);
        dirtSprackGroup = new SprackGroup("dirt.png", 16);
        playerSprackGroup = new SprackGroup("player.png", 16);
        
        Debug.log("Spawning spracks...");
        int size = 10;
        for (int x = 0; x < size; x++) {
            for (int y = -1; y >= -1; y--) {
                for (int z = 0; z < size; z++) {
                    new Block(this, dirtSprackGroup, x, y, z);
                }
            }
        }
        for (int x = 0; x < size; x++) {
            for (int z = 0; z < size; z++) {
                new Block(this, grassSprackGroup, x, 0, z);
            }
        }
        
        this.player = new Player(this);
        this.camera = new Camera(this);
        
        Debug.log("Complete");
    }
    
    /**
     * Comparator class used to sorting the sprites' z-index.
     */
    private class ZIndexComparator implements Comparator<Sprack> {
        private Vector3 camPos;
        
        /**
         * @param The camera object of the game
         */
        public ZIndexComparator(Camera camera) {
            this.camPos = new Vector3(0, 10000, 0);
            this.camPos.xy.rotate$(90 - camera.getVerAngle());
            this.camPos.xz.rotate$(camera.getHorAngle() - 90);
            this.camPos.plus$(camera.getPos());
        }
        
        /**
         * Compares two Spracks by their distance to the position of the camera.
         */
        public int compare(Sprack a, Sprack b) {
            return -Double.compare(a.pos.distanceTo(this.camPos), b.pos.distanceTo(this.camPos));
        }
    }
    
    public void act() {
        Collections.sort(Sprack.spracks, new ZIndexComparator(this.camera));
        
        for (int i = 0; i < Sprack.spracks.size(); i++) {
            Sprack sprack = Sprack.spracks.get(i);
            if (!sprack.inScene()) {
                sprack.updateScreenPos();
            }
            sprack.remove();
            if (sprack.inViewport()) {
                sprack.add();
            }
        }
        
        // NOTE: use this.getObjectsAt()
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (mouseInfo == null) return;
        if (mouseInfo.getActor() instanceof Block) {
            this.mouseOver = (Block) mouseInfo.getActor();
            if (Greenfoot.mousePressed(null)) {
                this.mouseOver.delete();
            }
        }
    }
}
