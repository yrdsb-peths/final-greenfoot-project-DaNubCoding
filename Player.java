import greenfoot.*;

public class Player extends Sprack {
    private Vector3 vel;
    private boolean onGround;
    
    public Player(MainGame scene) {
        super(scene, scene.playerSprackGroup, 0, 64, 0);
        this.vel = new Vector3(0, 0, 0);
        this.verAngle = 45;
        this.onGround = true;
    }
    
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
        
        this.pos.x.plus$(this.vel.x);
        if (this.colliding()) {
            this.pos.x.minus$(this.vel.x);
        }
        this.pos.y.plus$(this.vel.y);
        if (this.colliding()) {
            this.pos.y.minus$(this.vel.y);
            // if (this.vel.y.get() >= 0) {
                this.onGround = true;
            // }
            this.vel.y.set(0);
        }
        this.pos.z.plus$(this.vel.z);
        if (this.colliding()) {
            this.pos.z.minus$(this.vel.z);
        }
    }
    
    private boolean colliding() {
        for (int i = 0; i < Sprack.spracks.size(); i++) {
            if (Sprack.spracks.get(i) == this) continue;
            if (this.hitbox.overlaps(Sprack.spracks.get(i).getHitbox())) {
                return true;
            }
        }
        return false;
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