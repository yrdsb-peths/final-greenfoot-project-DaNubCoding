public class Vector {
    public static final Vector ZERO = new Vector(0, 0);
    
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
        double x = Math.cos(angle * Math.PI / 180) * this.x - Math.sin(angle * Math.PI / 180) * this.y;
        double y = Math.sin(angle * Math.PI / 180) * this.x + Math.cos(angle * Math.PI / 180) * this.y;
        return new Vector(x, y);
    }
}
