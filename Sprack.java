import greenfoot.*;

public class Sprack extends Sprite<MainGame> {
    protected SprackLayer[] layers;
    protected GreenfootImage[] layerImages;
    protected int angle;
    
    public static GreenfootImage[] parseSpritesheet(String path, int numOfLayers) {
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
    
    public Sprack(MainGame scene, int x, int y, GreenfootImage[] layerImages) {
        super(scene, x, y);
        this.layers = new SprackLayer[layerImages.length * Scene.PX];
        this.setImage((GreenfootImage) null);
        this.layerImages = layerImages;
        this.createLayers();
    }
    
    public Sprack(MainGame scene, int x, int y, String path, int numOfLayers) {
        super(scene, x, y);
        this.layers = new SprackLayer[numOfLayers * Scene.PX];
        this.setImage((GreenfootImage) null);
        this.layerImages = this.parseSpritesheet(path, numOfLayers);
        this.createLayers();
    }
    
    private void createLayers() {
        for (int i = 0; i < this.layerImages.length; i++) {
            for (int j = 0; j < Scene.PX; j++) {
                int visualLayer = i * Scene.PX + j;
                this.layers[visualLayer] = new SprackLayer(this.scene, this, visualLayer);
            }
        }
    }
    
    public GreenfootImage getLayerImage(int layer) {
        return this.layerImages[layer / Scene.PX];
    }
    
    public int getAngle() {
        return this.angle;
    }
    
    public void tick() {
        
    }
}
