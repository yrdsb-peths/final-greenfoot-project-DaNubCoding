import greenfoot.*;

public abstract class Sprite<T extends Scene> extends Actor {
    protected T scene;
    protected GreenfootImage image;

    public Sprite(T scene, int x, int y) {
        this.scene = scene;
        this.scene.addObject(this, x, y);
        this.image = this.getImage();
    }

    public void act() {
        this.tick();
    }
    
    public void setImage(GreenfootImage image) {
        super.setImage(image);
        this.image = this.getImage();
    }
    
    public void setLocation(Vector location) {
        super.setLocation((int) location.x, (int) location.y);
    }

    public abstract void tick();
}
