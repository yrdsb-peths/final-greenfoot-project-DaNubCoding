import greenfoot.*;

public class SprackGroup {
    private GreenfootImage[] rotationImages = new GreenfootImage[360];
    private int fullWidth;
    private int fullHeight;
    private SprackLayer[] layers;
    public GreenfootImage[] layerImages;
    private int w;
    private int h;
    private int diagonal;
    
    public SprackGroup(String path, int numOfLayers) {
        this.layerImages = this.parseSpritesheet(path, numOfLayers);
        
        this.w = this.layerImages[0].getWidth();
        this.h = this.layerImages[0].getHeight();
        this.diagonal = (int) Math.ceil(Math.sqrt(this.w * this.w + this.h * this.h));
        this.fullWidth = this.diagonal;
        this.fullHeight = this.layerImages.length * Scene.PX + this.diagonal;
        
        this.layers = new SprackLayer[numOfLayers];
        this.createLayers();
        this.cacheRotations();
    }
    
    private static GreenfootImage[] parseSpritesheet(String path, int numOfLayers) {
        GreenfootImage[] layerImages = new GreenfootImage[numOfLayers];
        GreenfootImage spritesheet = new GreenfootImage(path);
        spritesheet.mirrorVertically();
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
    
    private void cacheRotations() {
        for (int angle = 0; angle < 360; angle++) {
            generateRotationImage(angle);
        }
    }
    
    private void createLayers() {
        for (int i = 0; i < layers.length; i++) {
            GreenfootImage image = getLayerImage(i);
            layers[i] = new SprackLayer(i * Scene.PX, fullHeight, diagonal, image);
        }
    }
    
    public GreenfootImage getLayerImage(int layer) {
        return layerImages[layer];
    }
    
    private GreenfootImage generateRotationImage(int angle) {
        GreenfootImage image = rotationImages[angle] = new GreenfootImage(fullWidth, fullHeight);
        for (int i = 0; i < layers.length; i++) {
            layers[i].rotate(angle);
            for (int j = 0; j < Scene.PX; j++) {
                image.drawImage(layers[i].getImage(), layers[i].getX(), layers[i].getY() - j);
            }
        }
        return image;
    }
    
    public GreenfootImage getRotationImage(double angle) {
        GreenfootImage image = this.rotationImages[Math.floorMod((int) angle, 360)];
        if (image == null) image = this.generateRotationImage(Math.floorMod((int) angle, 360));
        return image;
    }
    
    public Vector2 getCenterOffset() {
        return new Vector2(0, this.fullHeight / 2 - this.diagonal / 2);
    }
}
