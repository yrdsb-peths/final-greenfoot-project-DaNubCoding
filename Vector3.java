/**
 * Class that represents a 3D vector.
 */
public class Vector3 {
    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    
    public VectorComponent.One x;
    public VectorComponent.One y;
    public VectorComponent.One z;
    
    // 2D vector components
    public VectorComponent.Two xy;
    public VectorComponent.Two zy;
    public VectorComponent.Two xz;
    
    /**
     * @param x The x component of the vector as a double
     * @param y The y component of the vector as a double
     * @param z The z component of the vector as a double
     */
    public Vector3(double x, double y, double z) {
        this.x = new VectorComponent.One(x);
        this.y = new VectorComponent.One(y);
        this.z = new VectorComponent.One(z);
        this.xy = new VectorComponent.Two(this.x, this.y);
        this.zy = new VectorComponent.Two(this.z, this.y);
        this.xz = new VectorComponent.Two(this.x, this.z);
    }
    
    /**
     * @param other The values of the vector as another Vector
     */
    public Vector3(Vector3 other) {
        this.x = new VectorComponent.One(other.x.get());
        this.y = new VectorComponent.One(other.y.get());
        this.z = new VectorComponent.One(other.z.get());
        this.xy = new VectorComponent.Two(this.x, this.y);
        this.zy = new VectorComponent.Two(this.z, this.y);
        this.xz = new VectorComponent.Two(this.x, this.z);
    }
    
    /**
     * Set the values of the vector.
     * 
     * @param other The vector to use the values from
     */
    public void set(Vector3 v) {
        this.x.set(v.x.get());
        this.y.set(v.y.get());
        this.z.set(v.z.get());
    }
    
    /**
     * Add a vector to this vector.
     * 
     * @param other The vector to add
     * @return The new vector after addition
     */
    public Vector3 plus(Vector3 other) {
        return new Vector3(this.x.plus(other.x), this.y.plus(other.y), this.z.plus(other.z));
    }
    
    /**
     * Add a vector to this vector in place (represented by $).
     * 
     * @param other The vector to add
     */
    public void plus$(Vector3 other) {
        this.x.plus$(other.x.get());
        this.y.plus$(other.y.get());
        this.z.plus$(other.z.get());
    }
    /**
     * Subtract a vector from this vector.
     * 
     * @param other The vector to subtract
     * @return The new vector after subtraction
     */
    public Vector3 minus(Vector3 other) {
        return new Vector3(this.x.minus(other.x), this.y.minus(other.y), this.z.minus(other.z));
    }
    
    /**
     * Subtract a vector from this vector in place (represented by $).
     * 
     * @param other The vector to subtract
     */
    public void minus$(Vector3 other) {
        this.x.minus$(other.x.get());
        this.y.minus$(other.y.get());
        this.z.minus$(other.z.get());
    }
    
    /**
     * Multiply this vector by a scalar value.
     * 
     * @param scalar The scalar value
     * @return The new vector after multiplication
     */
    public Vector3 times(double scalar) {
        return new Vector3(this.x.times(scalar), this.y.times(scalar), this.z.times(scalar));
    }
    
    /**
     * Multiply this vector by another vector element-wise.
     * 
     * @param other The vector to muliply by
     * @return The new vector after element-wise multiplication
     */
    public Vector3 timesEW(Vector3 other) {
        return this.plus(new Vector3(this.x.times(other.x), this.y.times(other.y), this.z.times(other.z)));
    }
    
    /**
     * Multiply this vector by a scalar value in place (represented by $).
     * 
     * @param scalar The scalar value
     */
    public void times$(double scalar) {
        this.x.times$(scalar);
        this.y.times$(scalar);
        this.z.times$(scalar);
    }
    
    /**
     * Calculate the distance from this vector to another vector.
     * 
     * @param other The vector to check the distance to
     * @return The distance from this vector to the other vector
     */
    public double distanceTo(Vector3 other) {
        double dx = this.x.get() - other.x.get();
        double dy = this.y.get() - other.y.get();
        double dz = this.z.get() - other.z.get();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    
    /**
     * Check if this vector is equal to another vector.
     * 
     * @param other The vector to check against
     * @return Whether this vector is equal to the other vector
     */
    public boolean equals(Object other) {
        Vector3 o = (Vector3) other;
        return this.x.equals(o.x) && this.y.equals(o.y) && this.z.equals(o.z);
    }
    
    /**
     * Return an integer hash code for the vector.
     */
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Double.hashCode(this.x.get());
        hash = 31 * hash + Double.hashCode(this.y.get());
        hash = 31 * hash + Double.hashCode(this.z.get());
        return hash;
    }
    
    /**
     * Returns a human-readable string representation of the vector.
     */
    public String toString() {
        return "Vector3(" + this.x.get() + ", " + this.y.get() + ", " + this.z.get() + ")";
    }
}
