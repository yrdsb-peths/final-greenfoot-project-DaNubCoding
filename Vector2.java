/**
 * Class that represents a 2D vector.
 */
public class Vector2 {
    public static final Vector2 ZERO = new Vector2(0, 0);
    
    public VectorComponent.One x;
    public VectorComponent.One y;
    
    /**
     * @param x The x component of the vector as a double
     * @param y The y component of the vector as a double
     */
    public Vector2(double x, double y) {
        this.x = new VectorComponent.One(x);
        this.y = new VectorComponent.One(y);
    }
    
    /**
     * @param x The x component of the vector as a VectorComponent
     * @param y The y component of the vector as a VectorComponent
     */
    public Vector2(VectorComponent.One x, VectorComponent.One y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @param other The values of the vector as another Vector
     */
    public Vector2(Vector2 other) {
        this.x = new VectorComponent.One(other.x.get());
        this.y = new VectorComponent.One(other.y.get());
    }
    
    /**
     * Set the values of the vector.
     * 
     * @param other The vector to use the values from
     */
    public void set(Vector2 other) {
        this.x.set(other.x.get());
        this.y.set(other.y.get());
    }
    
    /**
     * Add a vector to this vector.
     * 
     * @param other The vector to add
     * @return The new vector after addition
     */
    public Vector2 plus(Vector2 other) {
        return new Vector2(this.x.plus(other.x), this.y.plus(other.y));
    }
    
    /**
     * Add a vector to this vector in place (represented by $).
     * 
     * @param other The vector to add
     */
    public void plus$(Vector2 other) {
        this.x.plus$(other.x);
        this.y.plus$(other.y);
    }
    
    /**
     * Subtract a vector from this vector.
     * 
     * @param other The vector to subtract
     * @return The new vector after subtraction
     */
    public Vector2 minus(Vector2 other) {
        return new Vector2(this.x.minus(other.x), this.y.minus(other.y));
    }
    
    /**
     * Subtract a vector from this vector in place (represented by $).
     * 
     * @param other The vector to subtract
     */
    public void minus$(Vector2 other) {
        this.x.minus$(other.x.get());
        this.y.minus$(other.y.get());
    }
    
    /**
     * Multiply this vector by a scalar value.
     * 
     * @param scalar The scalar value
     * @return The new vector after multiplication
     */
    public Vector2 times(double scalar) {
        return new Vector2(this.x.times(scalar), this.y.times(scalar));
    }
    
    /**
     * Multiply this vector by a scalar value in place (represented by $).
     * 
     * @param scalar The scalar value
     */
    public void times$(double scalar) {
        this.x.times$(scalar);
        this.y.times$(scalar);
    }
    
    /**
     * Rotate the vector by an angle.
     * 
     * @param angle The angle to rotate by (in degrees)
     * @return The new vector after rotation
     */
    public Vector2 rotate(double angle) {
        double x = Math.cos(angle * Math.PI / 180) * this.x.get() - Math.sin(angle * Math.PI / 180) * this.y.get();
        double y = Math.sin(angle * Math.PI / 180) * this.x.get() + Math.cos(angle * Math.PI / 180) * this.y.get();
        return new Vector2(x, y);
    }
    
    /**
     * Rotate the vector by an angle in place (represented by $).
     * 
     * @param angle The angle to rotate by (in degrees)
     */
    public void rotate$(double angle) {
        this.set(this.rotate(angle));
    }
    
    /**
     * Calculate the distance from this vector to another vector.
     * 
     * @param other The vector to check the distance to
     * @return The distance from this vector to the other vector
     */
    public double distanceTo(Vector2 other) {
        double dx = this.x.get() - other.x.get();
        double dy = this.y.get() - other.y.get();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    /**
     * Check if this vector is equal to another vector.
     * 
     * @param other The vector to check against
     * @return Whether this vector is equal to the other vector
     */
    public boolean equals(Vector2 other) {
        return this.x.equals(other.x) && this.y.equals(other.y);
    }
    
    /**
     * Return a human-readable string representation of the vector.
     */
    public String toString() {
        return "Vector2(" + this.x.get() + ", " + this.y.get() + ")";
    }
}
