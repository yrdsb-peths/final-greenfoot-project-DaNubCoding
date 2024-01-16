/**
 * Class that defines a 3D cuboid region that can be used for collision detection.
 */
public class Hitbox {
    private Sprack master;
    private Vector3 pos;
    private Vector3 size;
    
    /**
     * @param master The Sprack object that the hitbox belongs to
     * @param size The dimensions of the hitbox as a Vector3
     */
    public Hitbox(Sprack master, Vector3 size) {
        this.master = master;
        this.pos = master.pos;
        this.size = new Vector3(size);
    }
    
    /**
     * Get the minimum x value contained in the hitbox.
     */
    public double xMin() {
        return this.pos.x.minus(this.size.x.times(0.5));
    }
    
    /**
     * Get the maximum x value contained in the hitbox.
     */
    public double xMax() {
        return this.pos.x.plus(this.size.x.times(0.5));
    }
    
    /**
     * Get the minimum y value contained in the hitbox.
     */
    public double yMin() {
        return this.pos.y.minus(this.size.y.times(0.5));
    }
    
    /**
     * Get the maximum y value contained in the hitbox.
     */    
    public double yMax() {
        return this.pos.y.plus(this.size.y.times(0.5));
    }
    
    /**
     * Get the minimum z value contained in the hitbox.
     */
    public double zMin() {
        return this.pos.z.minus(this.size.z.times(0.5));
    }
    
    /**
     * Get the maximum z value contained in the hitbox.
     */
    public double zMax() {
        return this.pos.z.plus(this.size.z.times(0.5));
    }
    
    /**
     * Check if this hitbox overlaps with another hitbox.
     * 
     * @param other The other hitbox to check
     * @return Whether the two hitboxes overlap
     */
    public boolean overlaps(Hitbox other) {
        boolean xOverlap = this.xMin() < other.xMax() && this.xMax() > other.xMin();
        boolean yOverlap = this.yMin() < other.yMax() && this.yMax() > other.yMin();
        boolean zOverlap = this.zMin() < other.zMax() && this.zMax() > other.zMin();
        return xOverlap && yOverlap && zOverlap;
    }
}