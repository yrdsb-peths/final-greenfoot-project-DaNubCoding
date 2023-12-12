import greenfoot.*;

public class Scene extends World {
    public int w;
    public int h;
    
    public Scene() {
        super(1024, 576, 1, false);
        this.w = super.getWidth();
        this.h = super.getHeight();
    }
}
