import greenfoot.*;

public class SprackLayer extends Sprite<MainGame> {
    private Sprack master;
    private int layer;
    private int angle = 0;
    
    public SprackLayer(MainGame scene, Sprack master, int layer) {
        super(scene, master.getX(), master.getY() - layer);
        this.master = master;
        this.layer = layer;
        this.setImage(master.getLayerImage(layer));
    }
    
    public void tick() {
        this.setRotation(this.master.getAngle());
    }
}
