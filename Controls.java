import greenfoot.*;

/**
 * Class that displays the controls.
 */
public class Controls extends Sprite<MainGame>
{
    private boolean visible = true;
    
    public Controls(MainGame scene) {
        super(scene, scene.w / 2, scene.h / 2);
        this.setImage(new GreenfootImage("controls.png"));
        this.image.setTransparency(200);
    }
    
    /**
     * Toggle the visibility of the controls menu.
     */
    public void toggle() {
        this.visible = !this.visible;
        if (this.visible) {
            this.add();
        } else {
            this.remove();
        }
    }
    
    public void tick() {
        this.remove();
        this.add();
    }
}
