/**
 * Utility wrapper class containing vector components.
 */
public class VectorComponent {
    /**
     * A mutable wrapper for a single vector component.
     */
    public static class One {
        private double x;
        
        /**
         * Initialize the component with a double.
         */
        public One(double x) {
            this.x = x;
        }
        
        /**
         * Get the value stored by this component.
         */
        public double get() {
            return this.x;
        }
        
        /**
         * Set the value stored by this component using a double.
         */
        public void set(double x) {
            this.x = x;
        }
        
        /**
         * Set the value stored by this component using another component.
         */
        public void set(One x) {
            this.x = x.get();
        }
        
        /**
         * Add a double value to this component and return it.
         */
        public double plus(double other) {
            return this.x + other;
        }
        
        /**
         * Add the value of another component to this component and return it.
         */
        public double plus(One other) {
            return this.x + other.get();
        }
        
        /**
         * Add a double value to this component in place.
         */
        public void plus$(double other) {
            this.x += other;
        }
        
        /**
         * Add the value of another component to this component in place.
         */
        public void plus$(One other) {
            this.x += other.get();
        }
        
        /**
         * Subtract a double value from this component and return it.
         */
        public double minus(double other) {
            return this.x - other;
        }
        
        /**
         * Subtract the value of another component from this component and return it.
         */
        public double minus(One other) {
            return this.x - other.get();
        }
        
        /**
         * Subtracat a double value from this component in place.
         */
        public void minus$(double other) {
            this.x -= other;
        }
        
        /**
         * Subtract the value of another component from this component in place.
         */
        public void minus$(One other) {
            this.x -= other.get();
        }
        
        /**
         * Multiply this component by a scalar value and return it.
         */
        public double times(double scalar) {
            return this.x * scalar;
        }
        
        /**
         * Multiply this component by the value of another component and return it.
         */
        public double times(One other) {
            return this.x * other.get();
        }
        
        /**
         * Multiply this component by a scalar value in place.
         */
        public void times$(double scalar) {
            this.x *= scalar;
        }
        
        /**
         * Multiply this component by the value of another component in place.
         */
        public void times$(One other) {
            this.x *= other.get();
        }
        
        /**
         * Check if this component is equal to another component.
         */
        public boolean equals(One other) {
            return this.x == other.get();
        }
    }
    
    /**
     * A mutable wrapper for a 2D vector component.
     */
    public static class Two extends Vector2 {
        private One x;
        private One y;
        
        public Two(One x, One y) {
            super(x, y);
        }
        
        /**
         * Get the values of this component as a new Vector2.
         */
        public Vector2 get() {
            return new Vector2(this.x.get(), this.y.get());
        }
    }
}
