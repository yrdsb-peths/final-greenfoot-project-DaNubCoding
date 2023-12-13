public class Vector {
    public double x;
    public double y;
    
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector plus(Vector other) {
        return new Vector(this.x + other.x, this.y + other.y);
    }
    
    public Vector minus(Vector other) {
        return new Vector(this.x - other.x, this.y - other.y);
    }
    
    public Vector times(double scalar) {
        return new Vector(this.x * scalar, this.y * scalar);
    }
    
    public Vector rotate(int angle) {
        double x = Math.cos(angle) * this.x - Math.sin(angle) * this.y;
        double y = Math.sin(angle) * this.x + Math.cos(angle) * this.y;
        return new Vector(x, y);
    }
}
