import greenfoot.*;

/**
 * Superclass for all actors on the screen that have regular displaying behavior.
 */
public abstract class Sprite<T extends Scene> extends Actor {
    protected T scene;
    protected GreenfootImage image;
    private int x;
    private int y;
    
    /**
     * @param scene The scene object that contains the sprite
     */
    public Sprite(T scene, int x, int y) {
        this.scene = scene;
        this.scene.addObject(this, x, y);
        this.setLocation(x, y);
        this.image = this.getImage();
    }
    
    /**
     * Overwrites the act method of the Greenfoot Actor class.
     */
    public void act() {
        this.tick();
    }
    
    /**
     * Get the on screen X coordinate of the sprite
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Get the on screen Y coordinate of the sprite.
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Set the image of the sprite.
     * 
     * @param image The image obejct to use
     */
    public void setImage(GreenfootImage image) {
        super.setImage(image);
        this.image = this.getImage();
    }
    
    /**
     * Set the location of the sprite using two integers.
     * 
     * @param x The x position of the sprite
     * @param y The y position of the sprite
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        super.setLocation(x, y);
    }
    
    /**
     * Set the location of the sprite using a vector.
     * 
     * @param location The position of the sprite as a Vector2
     */
    public void setLocation(Vector2 location) {
        this.x = (int) location.x.get();
        this.y = (int) location.y.get();
        super.setLocation(this.x, this.y);
    }
    
    /**
     * Remove the sprite from its scene.
     */
    public void remove() {
        this.scene.removeObject(this);
    }
    
    /**
     * Add the object to its scene
     */
    public void add() {
        this.scene.addObject(this, 0, 0);
    }
    
    /**
     * Checks if the sprite is in its scene.
     * 
     * @return true if the sprite is in the scene, false otherwise
     */
    public boolean inScene() {
        return this.getWorld() != null;
    }
    
    /**
     * Abstract method that is called every frame.
     */
    public abstract void tick();
}
