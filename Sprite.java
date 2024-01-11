import greenfoot.*;

public abstract class Sprite<T extends Scene> extends Actor {
    protected T scene;
    protected GreenfootImage image;
    private int x;
    private int y;

    public Sprite(T scene, int x, int y) {
        this.scene = scene;
        this.scene.addObject(this, x, y);
        this.setLocation(x, y);
        this.image = this.getImage();
    }

    public void act() {
        this.tick();
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setImage(GreenfootImage image) {
        super.setImage(image);
        this.image = this.getImage();
    }
    
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        super.setLocation(x, y);
    }
    
    public void setLocation(Vector2 location) {
        this.x = (int) location.x.get();
        this.y = (int) location.y.get();
        super.setLocation(this.x, this.y);
    }
    
    public void remove() {
        this.scene.removeObject(this);
    }
    
    public void add() {
        this.scene.addObject(this, 0, 0);
    }
    
    public boolean inScene() {
        return this.getWorld() != null;
    }

    public abstract void tick();
}
