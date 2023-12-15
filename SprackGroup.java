import greenfoot.*;

public class SprackGroup {
    private String path;
    private int numOfLayers;
    private GreenfootImage[] rotationImages = new GreenfootImage[360];
    private int[] rotationVerAngles = new int[360];
    private int fullWidth;
    private int fullHeight;
    private SprackLayer[] layers;
    public GreenfootImage[] layerImages;
    private int w;
    private int h;
    private int diagonal;
    
    public SprackGroup(String path, int numOfLayers) {
        this.path = path;
        this.numOfLayers = numOfLayers;
        this.parseSpritesheet();
        
        this.w = this.layerImages[0].getWidth();
        this.h = this.layerImages[0].getHeight();
        this.diagonal = (int) Math.ceil(Math.sqrt(this.w * this.w + this.h * this.h));
        this.fullWidth = this.diagonal;
        this.fullHeight = this.layerImages.length * Scene.PX + this.diagonal;
        
        this.layers = new SprackLayer[numOfLayers];
        this.createLayers();
    }
    
    private void parseSpritesheet() {
        this.layerImages = new GreenfootImage[this.numOfLayers];
        GreenfootImage spritesheet = new GreenfootImage(this.path);
        spritesheet.mirrorVertically();
        int sheetWidth = spritesheet.getWidth(), sheetHeight = spritesheet.getHeight();
        int layerWidth = sheetWidth, layerHeight = sheetHeight / this.numOfLayers;
        
        for (int i = 0; i < this.numOfLayers; i++) {
            GreenfootImage layerImage = new GreenfootImage(layerWidth, layerHeight);
            layerImage.drawImage(spritesheet, 0, -i * layerHeight);
            this.layerImages[i] = layerImage;
            layerImage.scale(layerImage.getWidth() * Scene.PX, layerImage.getHeight() * Scene.PX);
        }
    }
    
    private void createLayers() {
        for (int i = 0; i < this.layers.length; i++) {
            GreenfootImage image = this.getLayerImage(i);
            this.layers[i] = new SprackLayer(i * Scene.PX, this.fullHeight, this.diagonal, image);
        }
    }
    
    public GreenfootImage getLayerImage(int layer) {
        return this.layerImages[layer];
    }
    
    private GreenfootImage generateRotationImage(int horAngle, int verAngle) {
        this.rotationImages[horAngle] = new GreenfootImage(fullWidth, fullHeight);
        GreenfootImage image = this.rotationImages[horAngle];
        for (int i = 0; i < this.layers.length; i++) {
            SprackLayer layer = this.layers[i];
            layer.rotate(horAngle, verAngle);
            for (int j = 0; j < Scene.PX; j++) {
                image.drawImage(layer.getImage(), layer.getX(), layer.getY() - j);
            }
        }
        return image;
    }
    
    public GreenfootImage getRotationImage(double horAngle, double verAngle) {
        int realHorAngle = Math.floorMod((int) horAngle, 360);
        GreenfootImage image = this.rotationImages[realHorAngle];
        if (image == null || this.rotationVerAngles[realHorAngle] != (int) verAngle) {
            this.rotationVerAngles[realHorAngle] = (int) verAngle;
            image = this.generateRotationImage(realHorAngle, (int) verAngle);
        }
        return image;
    }
    
    public Vector2 getCenterOffset() {
        return new Vector2(0, this.fullHeight / 2 - this.diagonal / 2);
    }
}
