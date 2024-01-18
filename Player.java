import greenfoot.*;

/**
 * Class that handles player input, movement, and collision
 */
public class Player extends Sprack {
    private Vector3 vel;
    private boolean onGround;
    
    /**
     * @param scene The MainGame scene that contains the player
     */
    public Player(MainGame scene) {
        super(scene, scene.playerSprackGroup, 0, 64, 0);
        this.vel = new Vector3(0, 0, 0);
        this.verAngle = 45;
        this.onGround = true;
    }
    
    /**
     * Calculate movement of the player
     */
    private void move() {
        this.vel.xz.set(new Vector2(0, 0));
        this.vel.y.minus$(0.5);
        
        if (Greenfoot.isKeyDown("w")) { // Forward
            this.vel.xz.plus$(new Vector2(0, -5).rotate(this.horAngle));
        }
        if (Greenfoot.isKeyDown("s")) { // Backward
            this.vel.xz.plus$(new Vector2(0, 5).rotate(this.horAngle));
        }
        if (Greenfoot.isKeyDown("a")) { // Left
            this.vel.xz.plus$(new Vector2(-5, 0).rotate(this.horAngle));
        }
        if (Greenfoot.isKeyDown("d")) { // Right
            this.vel.xz.plus$(new Vector2(5, 0).rotate(this.horAngle));
        }
        if (Greenfoot.isKeyDown("space") && this.onGround) { // Jump
            this.vel.y.plus$(10);
            this.onGround = false;
        }
        
        // Check collision on the x-axis
        this.pos.x.plus$(this.vel.x);
        Sprack sprack = this.colliding();
        if (sprack != null) {
            this.pos.x.minus$(this.vel.x);
        }
        
        // Check collision on the y-axis
        this.pos.y.plus$(this.vel.y);
        sprack = this.colliding();
        if (sprack != null) {
            this.pos.y.minus$(this.vel.y);
            if (this.pos.y.get() > sprack.pos.y.get()) {
                this.onGround = true;
            }
            this.vel.y.set(0);
        }
        
        // Check collision on the z-axis
        this.pos.z.plus$(this.vel.z);
        sprack = this.colliding();
        if (sprack != null) {
            this.pos.z.minus$(this.vel.z);
        }
    }
    
    /**
     * Get the first sprack that the player is currently colliding with
     */
    private Sprack colliding() {
        for (int i = 0; i < Sprack.spracks.size(); i++) {
            Sprack sprack = Sprack.spracks.get(i);
            if (sprack == this) continue;
            if (this.hitbox.overlaps(sprack.getHitbox())) {
                return sprack;
            }
        }
        return null;
    }
    
    public void tick() {
        this.move();
        
        if (Greenfoot.isKeyDown("left")) { // Rotate CCW
            this.horAngle -= 2;
        }
        if (Greenfoot.isKeyDown("right")) { // Rotate CW
            this.horAngle += 2;
        }
        if (Greenfoot.isKeyDown("up")) { // Tilt up
            this.verAngle += 1;
            if (this.verAngle > 45) {
                this.verAngle = 45;
            }
        }
        if (Greenfoot.isKeyDown("down")) { // Tilt down
            this.verAngle -= 1;
            if (this.verAngle < 13) {
                this.verAngle = 13;
            }
        }
        
        super.tick();
        this.setLocation(this.getX(), this.getY() - 6 * Scene.PX);
    }
}