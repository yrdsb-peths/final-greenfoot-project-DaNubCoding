import greenfoot.*;

public class Player extends Sprack {
    public Player(MainGame scene) {
        super(scene, Greenfoot.getRandomNumber(scene.w), Greenfoot.getRandomNumber(scene.h), "player.png", 32);
    }
    
    public void tick() {
        this.setAngle(this.angle + 1);
    }
}
