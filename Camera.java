import greenfoot.*;

public class Camera extends Sprite<MainGame> {
    private Player player;
    private Vector3 pos;
    private double angle;
    
    public Camera(MainGame scene) {
        super(scene, 0, 0);
        this.setImage((GreenfootImage) null);
        this.player = this.scene.player;
        this.pos = Vector3.ZERO;
        this.angle = 0;
    }
    
    public Vector3 getPos() {
        return this.pos;
    }
    
    public double getAngle() {
        return this.angle;
    }
    
    public void tick() {
        Vector3 offset = this.player.getPos().minus(this.pos);
        this.pos.plus$(offset.times(0.1));
        
        double angleOffset = this.player.getAngle() - this.angle;
        this.angle += angleOffset * 0.1;
    }
}
