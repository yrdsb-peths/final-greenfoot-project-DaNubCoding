import greenfoot.*;

public class Player extends Sprack {
    public Player(MainGame scene, SprackGroup group) {
        super(scene, group, 0, -64, 0);
    }
    
    public void tick() {
        if (Greenfoot.isKeyDown("w")) { // up
            this.pos.xz.plus$(new Vector2(0, -5).rotate(this.angle));
        }
        if (Greenfoot.isKeyDown("s")) { // down
            this.pos.xz.plus$(new Vector2(0, 5).rotate(this.angle));
        }
        if (Greenfoot.isKeyDown("a")) { // left
            this.pos.xz.plus$(new Vector2(-5, 0).rotate(this.angle));
        }
        if (Greenfoot.isKeyDown("d")) { // right
            this.pos.xz.plus$(new Vector2(5, 0).rotate(this.angle));
        }
        if (Greenfoot.isKeyDown("space")) { // up
            this.pos.y.minus$(5);
        }
        if (Greenfoot.isKeyDown("shift")) { // down
            this.pos.y.plus$(5);
        }
        
        if (Greenfoot.isKeyDown("left")) { // CCW
            this.setAngle(this.angle - 2);
        }
        if (Greenfoot.isKeyDown("right")) { // CW
            this.setAngle(this.angle + 2);
        }
        
        super.tick();
    }
}
