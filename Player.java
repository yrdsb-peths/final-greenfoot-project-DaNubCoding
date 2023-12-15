import greenfoot.*;

public class Player extends Sprack {
    public Player(MainGame scene, SprackGroup group) {
        super(scene, group, 0, 64, 0);
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
        
        if (Greenfoot.isKeyDown("left")) { // CCW
            this.horAngle -= 2;
        }
        if (Greenfoot.isKeyDown("right")) { // CW
            this.horAngle += 2;
        }
        if (Greenfoot.isKeyDown("up")) {
            this.verAngle += 2;
            if (this.verAngle > 45) {
                this.verAngle = 45;
            }
        }
        if (Greenfoot.isKeyDown("down")) {
            this.verAngle -= 2;
            if (this.verAngle < 13) {
                this.verAngle = 13;
            }
        }
        
        super.tick();
    }
}
