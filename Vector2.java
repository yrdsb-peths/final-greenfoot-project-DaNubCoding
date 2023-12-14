public class Vector2 {
    public static final Vector2 ZERO = new Vector2(0, 0);
    
    public VectorComponent.One x;
    public VectorComponent.One y;
    
    public Vector2(double x, double y) {
        this.x = new VectorComponent.One(x);
        this.y = new VectorComponent.One(y);
    }
    
    public Vector2(VectorComponent.One x, VectorComponent.One y) {
        this.x = x;
        this.y = y;
    }
    
    public void set(Vector2 v) {
        this.x.set(v.x.get());
        this.y.set(v.y.get());
    }
    
    public Vector2 plus(Vector2 other) {
        return new Vector2(this.x.plus(other.x.get()), this.y.plus(other.x.get()));
    }
    
    public void plus$(Vector2 other) {
        this.x.plus$(other.x.get());
        this.y.plus$(other.y.get());
    }
    
    public Vector2 minus(Vector2 other) {
        return new Vector2(this.x.minus(other.x.get()), this.y.minus(other.y.get()));
    }
    
    public void minus$(Vector2 other) {
        this.x.minus$(other.x.get());
        this.y.minus$(other.y.get());
    }
    
    public Vector2 times(double scalar) {
        return new Vector2(this.x.times(scalar), this.y.times(scalar));
    }
        
    public void times$(double scalar) {
        this.x.times$(scalar);
        this.y.times$(scalar);
    }
    
    public Vector2 rotate(double angle) {
        double x = Math.cos(angle * Math.PI / 180) * this.x.get() - Math.sin(angle * Math.PI / 180) * this.y.get();
        double y = Math.sin(angle * Math.PI / 180) * this.x.get() + Math.cos(angle * Math.PI / 180) * this.y.get();
        return new Vector2(x, y);
    }
    
    public void rotate$(double angle) {
        this.set(this.rotate(angle));
    }
    
    public String toString() {
        return "Vector2(" + this.x.get() + ", " + this.y.get() + ")";
    }
}
