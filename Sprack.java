import greenfoot.*;

public class Sprack extends Sprite<MainGame> {
    protected static GreenfootImage[] rotationImages = new GreenfootImage[360];
    protected static int fullWidth;
    protected static int fullHeight;
    protected static SprackLayer[] layers;
    protected static GreenfootImage[] layerImages;
    protected static int w;
    protected static int h;
    protected static int diagonal;
    protected static boolean initialized = false;
    
    protected int angle;
    protected Vector pos;
    
    private static GreenfootImage[] parseSpritesheet(String path, int numOfLayers) {
        GreenfootImage[] layerImages = new GreenfootImage[numOfLayers];
        GreenfootImage spritesheet = new GreenfootImage(path);
        int sheetWidth = spritesheet.getWidth(), sheetHeight = spritesheet.getHeight();
        int layerWidth = sheetWidth, layerHeight = sheetHeight / numOfLayers;
        
        for (int i = 0; i < numOfLayers; i++) {
            GreenfootImage layerImage = new GreenfootImage(layerWidth, layerHeight);
            layerImage.drawImage(spritesheet, 0, -i * layerHeight);
            layerImages[i] = layerImage;
            layerImage.scale(layerImage.getWidth() * Scene.PX, layerImage.getHeight() * Scene.PX);
        }
        
        return layerImages;
    }
    
    private static void cacheRotations() {
        for (int angle = 0; angle < 360; angle++) {
            GreenfootImage image = rotationImages[angle] = new GreenfootImage(fullWidth, fullHeight);
            for (int i = 0; i < layers.length; i++) {
                layers[i].rotate(angle);
                image.drawImage(layers[i].getImage(), layers[i].getX(), layers[i].getY());
            }
        }
        initialized = true;
    }
    
    private static void createLayers() {
        for (int i = 0; i < layerImages.length; i++) {
            for (int j = 0; j < Scene.PX; j++) {
                int visualLayer = i * Scene.PX + j;
                GreenfootImage image = getLayerImage(visualLayer);
                layers[visualLayer] = new SprackLayer(visualLayer, fullHeight, diagonal, image);
            }
        }
    }
    
    public static GreenfootImage getLayerImage(int layer) {
        return layerImages[layer / Scene.PX];
    }
    
    public Sprack(MainGame scene, int x, int y, GreenfootImage[] layerImages) {
        super(scene, x, y);
        this.pos = new Vector(x, y);
        this.layerImages = layerImages;
        
        this.w = this.layerImages[0].getWidth();
        this.h = this.layerImages[0].getHeight();
        this.diagonal = (int) Math.ceil(Math.sqrt(this.w * this.w + this.h * this.h));
        this.fullWidth = this.diagonal;
        this.fullHeight = this.layerImages.length * Scene.PX + this.diagonal;
        
        if (!this.initialized) {
            this.layers = new SprackLayer[layerImages.length * Scene.PX];
            this.createLayers();
            this.cacheRotations();
        }
        this.setImage(this.rotationImages[0]);
    }
    
    public Sprack(MainGame scene, int x, int y, String path, int numOfLayers) {
        super(scene, x, y);
        this.pos = new Vector(x, y);
        this.layerImages = this.parseSpritesheet(path, numOfLayers);
        
        this.w = this.layerImages[0].getWidth();
        this.h = this.layerImages[0].getHeight();
        this.diagonal = (int) Math.ceil(Math.sqrt(this.w * this.w + this.h * this.h));
        this.fullWidth = this.diagonal;
        this.fullHeight = this.layerImages.length * Scene.PX + this.diagonal;
        
        if (!this.initialized) {
            this.layers = new SprackLayer[numOfLayers * Scene.PX];
            this.createLayers();
            this.cacheRotations();
        }
        this.setImage(this.rotationImages[0]);
    }
    
    public int getAngle() {
        return this.angle;
    }
    
    protected void setAngle(int angle) {
        this.angle = angle;
    }
    
    protected GreenfootImage getRotationImage(double angle) {
        return this.rotationImages[Math.floorMod((int) angle, 360)];
    }
    
    public Vector getPos() {
        return this.pos;
    }
    
    public void tick() {
        this.setImage(this.getRotationImage(this.angle - this.scene.camera.getAngle()));
        Vector screenPos = this.pos.minus(this.scene.camera.getPos());
        screenPos = screenPos.rotate((int) this.scene.camera.getAngle());
        screenPos = screenPos.plus(new Vector(this.scene.w / 2, this.scene.h / 2));
        this.setLocation(screenPos);
    }
}
