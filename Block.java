import greenfoot.*;
import java.util.*;

/**
 * Class that defines a block in the world
 */
public class Block extends Sprack {
    public static HashMap<Vector3, Block> blocks;
    private static GreenfootSound[] breakSounds = {
        new GreenfootSound("sounds/dirt1.wav"),
        new GreenfootSound("sounds/dirt2.wav"),
        new GreenfootSound("sounds/dirt3.wav"),
    };
    
    private Vector3 coord;
    
    /**
     * Static method to get the block at a certain coordinate
     * 
     * @param pos The coordinate of the block
     * @return The block object at the coordinate, null if there is no block there
     */
    public static Block getBlock(Vector3 pos) {
        return blocks.get(pos);
    }
    
    /**
     * @param scene The MainGame scene that contains the block
     * @param group The group that the block will use to render itself
     * @param x The x coordinate as an int
     * @param y The y coordinate as an int
     * @param z The z coordinate as an int
     */
    public Block(MainGame scene, SprackGroup group, int x, int y, int z) {
        super(scene, group, x * 16 * scene.PX, y * 16 * scene.PX, z * 16 * scene.PX);
        this.coord = new Vector3(x, y, z);
        this.blocks.put(this.coord, this);
    }
    
    /**
     * @param scene The MainGame scene that contains the block
     * @param group The group that the block will use to render itself
     * @param coord The coordinates of the block as a Vector3
     */
    public Block(MainGame scene, SprackGroup group, Vector3 coord) {
        this(scene, group, (int) Math.round(coord.x.get()), (int) Math.round(coord.y.get()), (int) Math.round(coord.z.get()));
    }
    
    /**
     * Get the face of the block at an on-screen location.
     * 
     * @param _x The x location
     * @param _y The y location
     * @return The face of the block represented as a unit Vector3 pointing in that direction
     */
    public Vector3 getFace(int _x, int _y) {
        // Find the bottom center of the block
        Vector2 centerOffset = this.group.getCenterOffset(this.scene.camera.getVerAngle());
        double ox = this.getX();
        double oy = this.getY() - this.group.getFullHeight() / 2 + centerOffset.y.get();
        // Convenience variables for the camera angles
        double a = -Math.toRadians(this.scene.camera.getHorAngle());
        double b = this.scene.camera.getVerAngle() / 45;
        // Convenience variable for the block size
        double l = this.group.getSize().x.get();
        // x and y relative to the origin (with inverted y)
        double x = _x - ox;
        double y = -(_y - oy);
        
        double y1 = b * (x * Math.cos(a) + l / 2) / Math.sin(a) + l;
        double y2 = b * (x * Math.cos(a) - l / 2) / Math.sin(a) + l;
        double y3 = -b * (x * Math.sin(a) + l / 2) / Math.cos(a) + l;
        double y4 = -b * (x * Math.sin(a) - l / 2) / Math.cos(a) + l;
        double[] topArr = {y1, y2, y3, y4};
        Arrays.sort(topArr);
        if (y > topArr[1] && y < topArr[2]) {
            return new Vector3(0, 1, 0);
        }
        
        final double sqrt2 = Math.sqrt(2);
        final double pi = Math.PI;
        double y5 = b * (x * Math.cos(a) + l / 2) / Math.sin(a);
        double y6 = b * (x * Math.cos(a) - l / 2) / Math.sin(a);
        double y7 = -b * (x * Math.sin(a) + l / 2) / Math.cos(a);
        double y8 = -b * (x * Math.sin(a) - l / 2) / Math.cos(a);
        double[] bottomArr = {y5, y6, y7, y8};
        Arrays.sort(bottomArr);
        double x1 = l / 2 * sqrt2 * Math.cos(a + 1 * pi / 4);
        double x2 = l / 2 * sqrt2 * Math.cos(a + 3 * pi / 4);
        double x3 = l / 2 * sqrt2 * Math.cos(a + 5 * pi / 4);
        double x4 = l / 2 * sqrt2 * Math.cos(a + 7 * pi / 4);
        // Clockwise starting from quadrant 4
        double[] xArr = {x1, x2, x3, x4};
        int rot = (int) (Math.toRadians(Math.floorMod((int) Math.toDegrees(a), 360)) / (pi / 2));
        double[] xArr2 = {0, 0, 0, 0};
        for (int j = 0; j < 4; j++) {
            xArr2[(j + rot) % 4] = xArr[j];
        }
        if (y < topArr[1] && y > bottomArr[1]) {
            Vector2 dir1 = new Vector2(0, 1).rotate(-rot * 90);
            Vector2 dir2 = new Vector2(1, 0).rotate(-rot * 90);
            if (x < xArr2[0]) {
                return new Vector3(dir1.x.get(), 0, dir1.y.get());
            } else {
                return new Vector3(dir2.x.get(), 0, dir2.y.get());
            }
        }
        
        return null;
    }
    
    /**
     * Get the coordinates of the block as a Vector3.
     */
    public Vector3 getCoord() {
        return this.coord;
    }
    
    /**
     * Fully remove a block
     */
    public void delete() {
        super.delete();
        this.blocks.remove(this.coord);
        breakSounds[Greenfoot.getRandomNumber(3)].play();
    }
    
    public void tick() {
        super.tick();
    }
}
