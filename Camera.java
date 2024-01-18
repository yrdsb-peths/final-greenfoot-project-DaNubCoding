import greenfoot.*;

/**
 * Class that handles the viewing position and angle of the player
 */
public class Camera extends Sprite<MainGame> {
    private Player player;
    private Vector3 pos;
    private double horAngle;
    private double verAngle;
    
    /**
     * @param scene The MainGame scene that contains the camera
     */
    public Camera(MainGame scene) {
        super(scene, 0, 0);
        this.setImage((GreenfootImage) null);
        this.player = scene.player;
        this.pos = Vector3.ZERO;
        this.horAngle = 0;
        this.verAngle = 45;
    }
    
    /**
     * Get the position the camera is focused on as a Vector3
     */
    public Vector3 getPos() {
        return this.pos;
    }
    
    /**
     * Get the horizontal rotation of the camera
     */
    public double getHorAngle() {
        return this.horAngle;
    }
    
    /**
     * Get the vertical rotation of the camera
     */
    public double getVerAngle() {
        return this.verAngle;
    }
    
    public void tick() {
        // Have the camera follow with a lag-behind
        Vector3 offset = this.player.getPos().minus(this.pos);
        this.pos.plus$(offset.times(0.1));
        
        double angleOffset = this.player.getHorAngle() - this.horAngle;
        this.horAngle += angleOffset * 0.1;
        
        angleOffset = this.player.getVerAngle() - this.verAngle;
        this.verAngle += angleOffset * 0.1;
    }
}
