import greenfoot.*;

public class Camera extends Sprite<MainGame> {
    private Player player;
    private Vector3 pos;
    private double horAngle;
    private double verAngle;
    
    public Camera(MainGame scene) {
        super(scene, 0, 0);
        this.setImage((GreenfootImage) null);
        this.player = scene.player;
        this.pos = Vector3.ZERO;
        this.horAngle = 0;
        this.verAngle = 45;
    }
    
    public Vector3 getPos() {
        return this.pos;
    }
    
    public double getHorAngle() {
        return this.horAngle;
    }
    
    public double getVerAngle() {
        return this.verAngle;
    }
    
    public void tick() {
        Vector3 offset = this.player.getPos().minus(this.pos);
        this.pos.plus$(offset.times(0.1));
        
        double angleOffset = this.player.getHorAngle() - this.horAngle;
        this.horAngle += angleOffset * 0.1;
        
        angleOffset = this.player.getVerAngle() - this.verAngle;
        this.verAngle += angleOffset * 0.1;
    }
}
