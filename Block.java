import greenfoot.*;
import java.util.HashMap;

/**
 * Class that defines a block in the world
 */
public class Block extends Sprack {
    public static HashMap<Vector3, Block> blocks;
    private static GreenfootSound[] breakSounds = {
        new GreenfootSound("sounds/dirt1.wav"),
        new GreenfootSound("sounds/dirt2.wav"),
        new GreenfootSound("sounds/dirt3.wav"),
    };
    
    private Vector3 coord;
    
    /**
     * Static method to get the block at a certain coordinate
     * 
     * @param pos The coordinate of the block
     * @return The block object at the coordinate, null if there is no block there
     */
    public static Block getBlock(Vector3 pos) {
        return blocks.get(pos);
    }
    
    /**
     * @param scene The MainGame scene that contains the block
     * @param group The group that the block will use to render itself
     * @param x The x coordinate as an int
     * @param y The y coordinate as an int
     * @param z The z coordinate as an int
     */
    public Block(MainGame scene, SprackGroup group, int x, int y, int z) {
        super(scene, group, x * 16 * scene.PX, y * 16 * scene.PX, z * 16 * scene.PX);
        this.coord = new Vector3(x, y, z);
        this.blocks.put(this.coord, this);
    }
    
    /**
     * @param scene The MainGame scene that contains the block
     * @param group The group that the block will use to render itself
     * @param coord The coordinates of the block as a Vector3
     */
    public Block(MainGame scene, SprackGroup group, Vector3 coord) {
        this(scene, group, (int) coord.x.get(), (int) coord.y.get(), (int) coord.z.get());
    }
    
    /**
     * Get the face of the block at an on-screen location.
     * 
     * @param x The x location
     * @param y The y location
     * @return The face of the block represented as a unit Vector3 pointing in that direction
     */
    public Vector3 getFace(int x, int y) {
        // Screen X and screen Y (center of the bottom layer)
        Vector2 centerOffset = this.group.getCenterOffset(this.scene.camera.getVerAngle());
        int sx = this.getX() - this.group.getFullWidth() + (int) centerOffset.x.get();
        int sy = this.getY() - this.group.getFullHeight() + (int) centerOffset.y.get();
        
        // boolean cond1 = y > ;
        return new Vector3(0, 0, 0);
    }
    
    /**
     * Get the coordinates of the block as a Vector3.
     */
    public Vector3 getCoord() {
        return this.coord;
    }
    
    /**
     * Fully remove a block
     */
    public void delete() {
        super.delete();
        this.blocks.remove(this.coord);
        breakSounds[Greenfoot.getRandomNumber(3)].play();
    }
    
    public void tick() {
        super.tick();
    }
}
