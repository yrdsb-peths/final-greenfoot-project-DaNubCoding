import greenfoot.*;
import java.util.HashMap;

/**
 * Class that defines a block in the world
 */
public class Block extends Sprack {
    public static HashMap<Vector3, Block> blocks;
    
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
     * @param x The x coordinates in blocks
     * @param y The y coordinates in blocks
     * @param z The z coordinates in blocks
     */
    public Block(MainGame scene, SprackGroup group, int x, int y, int z) {
        super(scene, group, x * 16 * scene.PX, y * 16 * scene.PX, z * 16 * scene.PX);
        this.coord = new Vector3(x, y, z);
        this.blocks.put(this.coord, this);
    }
    
    /**
     * Fully remove a block
     */
    public void delete() {
        super.delete();
        this.blocks.remove(this.coord);
    }
    
    public void tick() {
        super.tick();
    }
}
