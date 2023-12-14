import greenfoot.*;

public class Sprack extends Sprite<MainGame> {
    protected SprackGroup group;
    protected int angle;
    protected Vector3 pos;
    
    public Sprack(MainGame scene, SprackGroup group, int x, int y, int z) {
        super(scene, x, z);
        this.group = group;
        this.pos = new Vector3(x, y, z);
        this.setImage(this.group.getRotationImage(0));
    }
    
    public int getAngle() {
        return this.angle;
    }
    
    protected void setAngle(int angle) {
        this.angle = angle;
    }
    
    public Vector3 getPos() {
        return this.pos;
    }
    
    public void tick() {
        this.setImage(this.group.getRotationImage(this.angle - this.scene.camera.getAngle()));
        
        Vector2 screenPos = this.pos.xz.minus(this.scene.camera.getPos().xz);
        screenPos.rotate$((int) -this.scene.camera.getAngle());
        screenPos.minus$(this.group.getCenterOffset());
        screenPos.plus$(new Vector2(this.scene.w / 2, this.scene.h / 2));
        screenPos.y.minus$(this.scene.camera.getPos().y.get());
        screenPos.y.plus$(this.pos.y.get());
        this.setLocation(screenPos);
    }
}
