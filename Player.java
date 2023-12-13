import greenfoot.*;

public class Player extends Sprack {
    public Player(MainGame scene) {
        super(scene, 0, 0, "player.png", 32);
    }
    
    public void tick() {
        if (Greenfoot.isKeyDown("w")) { // up
            this.pos.y -= 5;
        }
        if (Greenfoot.isKeyDown("s")) { // down
            this.pos.y += 5;
        }
        if (Greenfoot.isKeyDown("a")) { // left
            this.pos.x -= 5;
        }
        if (Greenfoot.isKeyDown("d")) { // right
            this.pos.x += 5;
        }
        
        super.tick();
    }
}
