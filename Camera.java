import greenfoot.*;

public class Camera extends Sprite<MainGame> {
    private Player player;
    private Vector pos;
    private double angle;
    
    public Vector getPos() {
        return this.pos;
    }
    
    public double getAngle() {
        return this.angle;
    }
    
    public Camera(MainGame scene) {
        super(scene, 0, 0);
        this.setImage((GreenfootImage) null);
        this.player = this.scene.player;
        this.pos = Vector.ZERO;
        this.angle = 0;
    }
    
    public void tick() {
        Vector offset = this.player.getPos().minus(this.pos);//.minus(new Vector(this.scene.w / 2, this.scene.h / 2));
        this.pos = this.pos.plus(offset.times(0.1));
        
        double angleOffset = this.player.getAngle() - this.angle;
        this.angle += angleOffset * 0.1;
    }
}
