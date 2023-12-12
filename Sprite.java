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

    public abstract void tick();
}
