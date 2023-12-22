public class Vector3 {
    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    
    public VectorComponent.One x;
    public VectorComponent.One y;
    public VectorComponent.One z;
    
    // 2D vector components
    public VectorComponent.Two xy;
    public VectorComponent.Two zy;
    public VectorComponent.Two xz;
    
    public Vector3(double x, double y, double z) {
        this.x = new VectorComponent.One(x);
        this.y = new VectorComponent.One(y);
        this.z = new VectorComponent.One(z);
        this.xy = new VectorComponent.Two(this.x, this.y);
        this.zy = new VectorComponent.Two(this.z, this.y);
        this.xz = new VectorComponent.Two(this.x, this.z);
    }
    
    public Vector3(Vector3 other) {
        this.x = new VectorComponent.One(other.x.get());
        this.y = new VectorComponent.One(other.y.get());
        this.z = new VectorComponent.One(other.z.get());
        this.xy = new VectorComponent.Two(this.x, this.y);
        this.zy = new VectorComponent.Two(this.z, this.y);
        this.xz = new VectorComponent.Two(this.x, this.z);
    }
    
    public void set(Vector3 v) {
        this.x.set(v.x.get());
        this.y.set(v.y.get());
        this.z.set(v.z.get());
    }
    
    public Vector3 plus(Vector3 other) {
        return new Vector3(this.x.plus(other.x), this.y.plus(other.y), this.z.plus(other.z));
    }
    
    public void plus$(Vector3 other) {
        this.x.plus$(other.x.get());
        this.y.plus$(other.y.get());
        this.z.plus$(other.z.get());
    }
    
    public Vector3 minus(Vector3 other) {
        return new Vector3(this.x.minus(other.x), this.y.minus(other.y), this.z.minus(other.z));
    }
    
    public void minus$(Vector3 other) {
        this.x.minus$(other.x.get());
        this.y.minus$(other.y.get());
        this.z.minus$(other.z.get());
    }
    
    public Vector3 times(double scalar) {
        return new Vector3(this.x.times(scalar), this.y.times(scalar), this.z.times(scalar));
    }
    
    public Vector3 timesEW(Vector3 other) {
        return this.plus(new Vector3(this.x.times(other.x), this.y.times(other.y), this.z.times(other.z)));
    }
        
    public void times$(double scalar) {
        this.x.times$(scalar);
        this.y.times$(scalar);
        this.z.times$(scalar);
    }
    
    public double distanceTo(Vector3 other) {
        double dx = this.x.get() - other.x.get();
        double dy = this.y.get() - other.y.get();
        double dz = this.z.get() - other.z.get();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    
    public String toString() {
        return "Vector3(" + this.x.get() + ", " + this.y.get() + ", " + this.z.get() + ")";
    }
}
