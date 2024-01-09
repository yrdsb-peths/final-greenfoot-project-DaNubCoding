public class VectorComponent {
    public static class One {
        private double x;
        
        public One(double x) {
            this.x = x;
        }
        
        public double get() {
            return this.x;
        }
        
        public void set(double x) {
            this.x = x;
        }
        
        public void set(One x) {
            this.x = x.get();
        }
        
        public double plus(double other) {
            return this.x + other;
        }
        
        public double plus(One other) {
            return this.x + other.get();
        }
        
        public void plus$(double other) {
            this.x += other;
        }
        
        public void plus$(One other) {
            this.x += other.get();
        }
        
        public double minus(double other) {
            return this.x - other;
        }
        
        public double minus(One other) {
            return this.x - other.get();
        }
        
        public void minus$(double other) {
            this.x -= other;
        }
        
        public void minus$(One other) {
            this.x -= other.get();
        }
        
        public double times(double scalar) {
            return this.x * scalar;
        }
        
        public double times(One other) {
            return this.x * other.get();
        }
        
        public void times$(double scalar) {
            this.x *= scalar;
        }
        
        public void times$(One other) {
            this.x *= other.get();
        }
    }
    
    public static class Two extends Vector2 {
        private One x;
        private One y;
        
        public Two(One x, One y) {
            super(x, y);
        }
        
        public Vector2 get() {
            return new Vector2(this.x.get(), this.y.get());
        }
    }
}
