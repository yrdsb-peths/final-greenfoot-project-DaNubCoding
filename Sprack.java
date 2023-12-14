import greenfoot.*;
import java.util.*;

public class Sprack extends Sprite<MainGame> {
    public static ArrayList<Sprack> spracks = new ArrayList<Sprack>();
    
    protected SprackGroup group;
    protected int angle;
    protected Vector3 pos;
    
    public Sprack(MainGame scene, SprackGroup group, int x, int y, int z) {
        super(scene, x, z);
        this.group = group;
        this.pos = new Vector3(x, y, z);
        this.setImage(this.group.getRotationImage(0));
        
        this.spracks.add(this);
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
        screenPos.y.plus$(30);
        screenPos.y.minus$(this.pos.y);
        screenPos.y.plus$(this.scene.camera.getPos().y);
        this.setLocation(screenPos);
    }
}
