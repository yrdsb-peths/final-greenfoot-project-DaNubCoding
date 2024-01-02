import greenfoot.*;

public class Player extends Sprack {
    public Player(MainGame scene) {
        super(scene, scene.playerSprackGroup, 0, 64, 0);
        this.verAngle = 45;
    }
    
    public void tick() {
        if (Greenfoot.isKeyDown("w")) { // up
            this.pos.xz.plus$(new Vector2(0, -5).rotate(this.horAngle));
        }
        if (Greenfoot.isKeyDown("s")) { // down
            this.pos.xz.plus$(new Vector2(0, 5).rotate(this.horAngle));
        }
        if (Greenfoot.isKeyDown("a")) { // left
            this.pos.xz.plus$(new Vector2(-5, 0).rotate(this.horAngle));
        }
        if (Greenfoot.isKeyDown("d")) { // right
            this.pos.xz.plus$(new Vector2(5, 0).rotate(this.horAngle));
        }
        if (Greenfoot.isKeyDown("space")) { // up
            this.pos.y.plus$(5);
        }
        if (Greenfoot.isKeyDown("shift")) { // down
            this.pos.y.minus$(5);
        }
        
        if (this.pos.z.get() > 9 * 64 - 32 - 19.2) {
            this.pos.z.set(9 * 64 - 32 - 19.2);
        }
        
        if (Greenfoot.isKeyDown("left")) { // CCW
            this.horAngle -= 2;
        }
        if (Greenfoot.isKeyDown("right")) { // CW
            this.horAngle += 2;
        }
        if (Greenfoot.isKeyDown("up")) {
            this.verAngle += 1;
            if (this.verAngle > 45) {
                this.verAngle = 45;
            }
        }
        if (Greenfoot.isKeyDown("down")) {
            this.verAngle -= 1;
            if (this.verAngle < 13) {
                this.verAngle = 13;
            }
        }
        
        super.tick();
        this.setLocation(this.getX(), this.getY() - 5 * Scene.PX);
    }
}