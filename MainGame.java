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
    public Controls controls;
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
        this.controls = new Controls(this);
        
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
            // Calculate the position of the camera
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
    
    private void handleMouse() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (mouseInfo == null) return;
        
        this.handleBreakBlock(mouseInfo);
        this.handlePlaceBlock(mouseInfo);
    }
    
    private void handleBreakBlock(MouseInfo mouseInfo) {
        if (Greenfoot.mousePressed(null) && mouseInfo.getButton() == 1) {
            List<Block> blocks = this.getObjectsAt(mouseInfo.getX(), mouseInfo.getY(), Block.class);
            if (blocks.isEmpty()) return;
            Collections.sort(blocks, new ZIndexComparator(this.camera));
            
            // Iterate from closest to furthest
            for (int i = blocks.size() - 1; i >= 0; i--) {
                Block block = blocks.get(i);
                Vector3 offset = block.getFace(mouseInfo.getX(), mouseInfo.getY());
                
                // If none of the faces have been clicked
                if (offset == null) continue;
                
                block.delete();
                break;
            }
        }
    }
    
    private void handlePlaceBlock(MouseInfo mouseInfo) {
        if (Greenfoot.mousePressed(null) && mouseInfo.getButton() == 3) {
            List<Block> blocks = this.getObjectsAt(mouseInfo.getX(), mouseInfo.getY(), Block.class);
            if (blocks.isEmpty()) return;
            Collections.sort(blocks, new ZIndexComparator(this.camera));
            
            // Iterate from closest to furthest
            for (int i = blocks.size() - 1; i >= 0; i--) {
                Block block = blocks.get(i);
                Vector3 offset = block.getFace(mouseInfo.getX(), mouseInfo.getY());
                
                // If none of the faces have been clicked
                if (offset == null) continue;
                
                Vector3 coord = block.getCoord().plus(offset);
                
                // If there is already a block there
                if (Block.getBlock(coord) != null) return;
                
                new Block(this, dirtSprackGroup, coord);
                break;
            }
        }
    }
    
    private void sortSpracks() {
        // Sort the z-index of the spracks
        Collections.sort(Sprack.spracks, new ZIndexComparator(this.camera));
        
        // Display the visible spracks, and add them in the order they should be rendered
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
    }
    
    public void act() {
        this.handleMouse();
        this.sortSpracks();
        
        if ("/".equals(Greenfoot.getKey())) {
            this.controls.toggle();
        }
    }
}
