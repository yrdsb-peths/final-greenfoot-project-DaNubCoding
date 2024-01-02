import greenfoot.*;

public class Block extends Sprack {
    public Block(MainGame scene, int x, int y, int z) {
        super(scene, scene.blockSprackGroup, x * 16 * scene.PX, y * 16 * scene.PX, z * 16 * scene.PX);
    }
    
    public void tick() {
        super.tick();
    }
}
