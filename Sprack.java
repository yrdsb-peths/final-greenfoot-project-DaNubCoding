import greenfoot.*;
import java.util.*;

public class Sprack extends Sprite<MainGame> {
    public static ArrayList<Sprack> spracks = new ArrayList<Sprack>();
    
    protected SprackGroup group;
    protected int horAngle;
    protected int verAngle;
    protected Vector3 pos;
    protected Hitbox hitbox;
    
    public Sprack(MainGame scene, SprackGroup group, int x, int y, int z) {
        super(scene, x, z);
        this.group = group;
        this.pos = new Vector3(x, y, z);
        this.setImage(this.group.getRotationImage(0, 45));
        this.hitbox = new Hitbox(this, this.group.getSize());
        
        this.spracks.add(this);
    }
    
    public int getHorAngle() {
        return this.horAngle;
    }
    
    protected void setHorAngle(int angle) {
        this.horAngle = angle;
    }
    
    public int getVerAngle() {
        return this.verAngle;
    }
    
    protected void setVerAngle(int angle) {
        this.verAngle = angle;
    }
    
    public Vector3 getPos() {
        return this.pos;
    }
    
    public Hitbox getHitbox() {
        return this.hitbox;
    }
    
    public void tick() {
        this.setImage(this.group.getRotationImage(this.horAngle - this.scene.camera.getHorAngle(), this.scene.camera.getVerAngle()));
        
        Vector2 screenPos = this.pos.xz.minus(this.scene.camera.getPos().xz);
        screenPos.rotate$(-this.scene.camera.getHorAngle());
        screenPos.y.times$(this.scene.camera.getVerAngle() / 45);
        screenPos.minus$(this.group.getCenterOffset(this.scene.camera.getVerAngle()));
        screenPos.plus$(new Vector2(this.scene.w / 2, this.scene.h / 2));
        screenPos.y.plus$(30);
        screenPos.y.minus$(this.pos.y);
        screenPos.y.plus$(this.scene.camera.getPos().y);
        
        this.setLocation(screenPos);
    }
}
