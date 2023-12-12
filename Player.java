import greenfoot.*;

public class Player extends Sprack {
    public Player(MainGame scene) {
        super(scene, Greenfoot.getRandomNumber(scene.w), Greenfoot.getRandomNumber(scene.h), "player_test.png", 16);
    }
    
    public void tick() {
        this.angle++;
    }
}
