import greenfoot.*;
import java.util.HashMap;

public class Block extends Sprack {
    public static HashMap<Vector3, Block> blocks;
    
    private Vector3 coord;
    
    public static Block getBlock(Vector3 pos) {
        return blocks.get(pos.toString());
    }
    
    public Block(MainGame scene, SprackGroup group, int x, int y, int z) {
        super(scene, group, x * 16 * scene.PX, y * 16 * scene.PX, z * 16 * scene.PX);
        this.coord = new Vector3(x, y, z);
        this.blocks.put(this.coord, this);
    }
    
    public void delete() {
        super.delete();
        this.blocks.remove(this.coord);
    }
    
    public void tick() {
        super.tick();
    }
}
