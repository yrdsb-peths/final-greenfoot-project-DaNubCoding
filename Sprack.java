import greenfoot.*;

public class Sprack extends Sprite<MainGame> {
    protected SprackGroup group;
    protected int angle;
    protected Vector pos;
    
    public Sprack(MainGame scene, SprackGroup group, int x, int y) {
        super(scene, x, y);
        this.group = group;
        this.pos = new Vector(x, y);
        this.setImage(this.group.getRotationImage(0));
    }
    
    public int getAngle() {
        return this.angle;
    }
    
    protected void setAngle(int angle) {
        this.angle = angle;
    }
    
    public Vector getPos() {
        return this.pos;
    }
    
    public void tick() {
        this.setImage(this.group.getRotationImage(this.angle - this.scene.camera.getAngle()));
        Vector screenPos = this.pos.minus(this.scene.camera.getPos());
        screenPos = screenPos.rotate((int) -this.scene.camera.getAngle());
        screenPos = screenPos.plus(new Vector(this.scene.w / 2, this.scene.h / 2));
        this.setLocation(screenPos);
    }
}
