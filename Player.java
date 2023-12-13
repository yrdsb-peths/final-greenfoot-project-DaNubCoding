import greenfoot.*;

public class Player extends Sprack {
    public Player(MainGame scene) {
        super(scene, 0, 0, "player.png", 32);
    }
    
    public void tick() {
        if (Greenfoot.isKeyDown("w")) { // up
            this.pos = this.pos.plus(new Vector(0, -5).rotate(this.angle));
        }
        if (Greenfoot.isKeyDown("s")) { // down
            this.pos = this.pos.plus(new Vector(0, 5).rotate(this.angle));
        }
        if (Greenfoot.isKeyDown("a")) { // left
            this.pos = this.pos.plus(new Vector(-5, 0).rotate(this.angle));
        }
        if (Greenfoot.isKeyDown("d")) { // right
            this.pos = this.pos.plus(new Vector(5, 0).rotate(this.angle));
        }
        
        if (Greenfoot.isKeyDown("q")) { // CCW
            this.setAngle(this.angle - 2);
        }
        if (Greenfoot.isKeyDown("e")) { // CW
            this.setAngle(this.angle + 2);
        }
        
        super.tick();
    }
}
