import greenfoot.*;

public class Scene extends World {
    public static final int PX = 4; // Pixel size
    
    public int w;
    public int h;
    
    public Scene() {
        super(1024, 576, 1, false);
        this.w = super.getWidth();
        this.h = super.getHeight();
    }
}
