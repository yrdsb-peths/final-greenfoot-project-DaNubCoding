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
        
        public double plus(double other) {
            return this.x + other;
        }
        
        public void plus$(double other) {
            this.x += other;
        }
        
        public double minus(double other) {
            return this.x - other;
        }
        
        public void minus$(double other) {
            this.x -= other;
        }
        
        public double times(double other) {
            return this.x * other;
        }
        
        public void times$(double other) {
            this.x *= other;
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
