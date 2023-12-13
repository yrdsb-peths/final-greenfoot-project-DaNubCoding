import greenfoot.*;

public class Camera extends Sprite<MainGame> {
    private Player player;
    private Vector pos;
    
    public Vector getPos() {
        return this.pos;
    }
    
    public Camera(MainGame scene) {
        super(scene, 0, 0);
        this.setImage((GreenfootImage) null);
        this.player = this.scene.player;
        this.pos = Vector.ZERO;
    }
    
    public void tick() {
        Vector offset = this.player.getPos().minus(this.pos).minus(new Vector(this.scene.w / 2, this.scene.h / 2));
        this.pos = this.pos.plus(offset.times(0.1));
    }
}
