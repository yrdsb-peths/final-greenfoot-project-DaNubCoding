import greenfoot.*;
import java.util.*;

/**
 * Super class for all the 3D-looking sprites, using sprite-stacking.
 * "Sprack" is short for "Sprite Stack".
 */
public class Sprack extends Sprite<MainGame> {
    public static ArrayList<Sprack> spracks;
    
    protected SprackGroup group;
    protected int horAngle;
    protected int verAngle;
    protected Vector3 pos;
    protected Hitbox hitbox;
    
    /**
     * @param scene The scene that contains the sprack.
     * @param group The type of sprack
     * @param x The x coordinates in pixels
     * @param y The y coordinates in pixels
     * @param z The z coordinates in pixels
     */
    public Sprack(MainGame scene, SprackGroup group, int x, int y, int z) {
        super(scene, x, z);
        this.group = group;
        this.pos = new Vector3(x, y, z);
        this.setImage(this.group.getRotationImage(0, 45));
        this.hitbox = new Hitbox(this, this.group.getSize());
        
        spracks.add(this);
    }
    
    /**
     * Get the horizontal rotation of the sprack
     */
    public int getHorAngle() {
        return this.horAngle;
    }
    
    /**
     * Get the vertical rotation of the sprack
     */
    public int getVerAngle() {
        return this.verAngle;
    }
    
    /**
     * Get the position of the sprack
     */
    public Vector3 getPos() {
        return this.pos;
    }
    
    /**
     * Get the hitbox object of the sprack
     */
    public Hitbox getHitbox() {
        return this.hitbox;
    }
    
    /**
     * Check if the sprack is fully within the viewport
     */
    public boolean inViewport() {
        boolean xCond = this.getX() > -this.group.getFullWidth() / 2 && this.getX() < this.scene.w + this.group.getFullWidth() / 2;
        boolean yCond = this.getY() > -this.group.getFullHeight() / 2 && this.getY() < this.scene.h + this.group.getFullHeight() / 2;
        return xCond && yCond;
    }
    
    /**
     * Update the location the sprack should appear on the screen
     */
    public void updateScreenPos() {
        Vector2 screenPos = this.pos.xz.minus(this.scene.camera.getPos().xz);
        screenPos.rotate$(-this.scene.camera.getHorAngle());
        screenPos.y.times$(this.scene.camera.getVerAngle() / 45);
        screenPos.minus$(this.group.getCenterOffset(this.scene.camera.getVerAngle()));
        screenPos.plus$(new Vector2(this.scene.w / 2, this.scene.h / 2));
        screenPos.y.plus$(100);
        screenPos.y.minus$(this.pos.y);
        screenPos.y.plus$(this.scene.camera.getPos().y);
        
        this.setLocation(screenPos);
    }
    
    /**
     * Fully remove the sprack
     */
    public void delete() {
        super.remove();
        spracks.remove(this);
    }
    
    public void tick() {
        this.setImage(this.group.getRotationImage(this.horAngle - this.scene.camera.getHorAngle(), this.scene.camera.getVerAngle()));
        
        this.updateScreenPos();
    }
}
