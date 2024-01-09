public class Hitbox {
    private Sprack master;
    private Vector3 pos;
    private Vector3 size;
    
    public Hitbox(Sprack master, Vector3 size) {
        this.master = master;
        this.pos = master.pos;
        this.size = new Vector3(size);
    }
    
    public double xMin() {
        return this.pos.x.minus(this.size.x.times(0.5));
    }
    
    public double xMax() {
        return this.pos.x.plus(this.size.x.times(0.5));
    }
    
    public double yMin() {
        return this.pos.y.minus(this.size.y.times(0.5));
    }
    
    public double yMax() {
        return this.pos.y.plus(this.size.y.times(0.5));
    }
    
    public double zMin() {
        return this.pos.z.minus(this.size.z.times(0.5));
    }
    
    public double zMax() {
        return this.pos.z.plus(this.size.z.times(0.5));
    }
    
    public boolean overlaps(Hitbox other) {
        boolean xOverlap = this.xMin() < other.xMax() && this.xMax() > other.xMin();
        boolean yOverlap = this.yMin() < other.yMax() && this.yMax() > other.yMin();
        boolean zOverlap = this.zMin() < other.zMax() && this.zMax() > other.zMin();
        return xOverlap && yOverlap && zOverlap;
    }
}