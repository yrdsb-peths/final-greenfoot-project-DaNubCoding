import greenfoot.*;

public class Camera extends Sprite<MainGame> {
    private DummyPlayer player;
    private Vector3 pos;
    private double horAngle;
    private double verAngle;
    
    private class DummyPlayer extends Sprite<MainGame> {
        private Vector3 pos;
        protected int horAngle;
        protected int verAngle;
        
        public DummyPlayer(MainGame scene, int x, int y, int z) {
            super(scene, x, z);
            this.pos = new Vector3(x, y, z);
            this.verAngle = 45;
            this.setImage((GreenfootImage) null);
        }
        
        public Vector3 getPos() {
            return this.pos;
        }
        
        public int getHorAngle() {
            return this.horAngle;
        }
        
        public int getVerAngle() {
            return this.verAngle;
        }
        
        public void tick() {
            if (Greenfoot.isKeyDown("w")) { // up
                this.pos.xz.plus$(new Vector2(0, -5).rotate(this.horAngle));
            }
            if (Greenfoot.isKeyDown("s")) { // down
                this.pos.xz.plus$(new Vector2(0, 5).rotate(this.horAngle));
            }
            if (Greenfoot.isKeyDown("a")) { // left
                this.pos.xz.plus$(new Vector2(-5, 0).rotate(this.horAngle));
            }
            if (Greenfoot.isKeyDown("d")) { // right
                this.pos.xz.plus$(new Vector2(5, 0).rotate(this.horAngle));
            }
            if (Greenfoot.isKeyDown("space")) { // up
                this.pos.y.plus$(5);
            }
            if (Greenfoot.isKeyDown("shift")) { // down
                this.pos.y.minus$(5);
            }
            
            if (this.pos.z.get() > 9 * 64 - 32 - 19.2) {
                this.pos.z.set(9 * 64 - 32 - 19.2);
            }
            
            if (Greenfoot.isKeyDown("left")) { // CCW
                this.horAngle -= 2;
            }
            if (Greenfoot.isKeyDown("right")) { // CW
                this.horAngle += 2;
            }
            if (Greenfoot.isKeyDown("up")) {
                this.verAngle += 1;
                if (this.verAngle > 45) {
                    this.verAngle = 45;
                }
            }
            if (Greenfoot.isKeyDown("down")) {
                this.verAngle -= 1;
                if (this.verAngle < 13) {
                    this.verAngle = 13;
                }
            }
        }
    }
    
    public Camera(MainGame scene) {
        super(scene, 0, 0);
        this.setImage((GreenfootImage) null);
        this.player = new DummyPlayer(scene, 0, 0, 0);
        this.pos = Vector3.ZERO;
        this.horAngle = 0;
        this.verAngle = 45;
    }
    
    public Vector3 getPos() {
        return this.pos;
    }
    
    public double getHorAngle() {
        return this.horAngle;
    }
    
    public double getVerAngle() {
        return this.verAngle;
    }
    
    public void tick() {
        Vector3 offset = this.player.getPos().minus(this.pos);
        this.pos.plus$(offset.times(0.1));
        
        double angleOffset = this.player.getHorAngle() - this.horAngle;
        this.horAngle += angleOffset * 0.1;
        
        angleOffset = this.player.getVerAngle() - this.verAngle;
        this.verAngle += angleOffset * 0.1;
    }
}
