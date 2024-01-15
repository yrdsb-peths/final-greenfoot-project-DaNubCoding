import greenfoot.*;

/**
 * The class that stores information about a certain type of spracks, and handles the rendering and caching of their images
 */
public class SprackGroup {
    private String path;
    private GreenfootImage[] rotationImages = new GreenfootImage[360];
    private int[] rotationVerAngles = new int[360];
    private int fullWidth;
    private int fullHeight;
    private SprackLayer[] layers;
    public GreenfootImage[] layerImages;
    private int w;
    private int h;
    private int diagonal;
    private int numOfLayers;
    
    /**
     * @param path The path of the spritesheet image to use to construct the spracks of this group
     * @param numOfLayers The number of layers this group of spracks have
     */
    public SprackGroup(String path, int numOfLayers) {
        this.path = path;
        this.numOfLayers = numOfLayers;
        this.layers = new SprackLayer[numOfLayers];
        this.parseSpritesheet();
        
        this.w = this.layerImages[0].getWidth();
        this.h = this.layerImages[0].getHeight();
        this.diagonal = (int) Math.ceil(Math.sqrt(this.w * this.w + this.h * this.h));
        this.fullWidth = this.diagonal;
        this.fullHeight = this.layerImages.length * Scene.PX + this.diagonal;
        
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
    
    /**
     * Generate the SprackLayer objects from the layer images
     */
    private void createLayers() {
        for (int i = 0; i < this.layers.length; i++) {
            GreenfootImage image = this.layerImages[i];
            this.layers[i] = new SprackLayer(i * Scene.PX, this.fullHeight, this.diagonal, image);
        }
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
    
    /**
     * Get the displayed image of the sprack from a certain viewing angle
     * 
     * @param horAngle The horizontal rotation of the sprack
     * @param verAngle The vertical rotation of the sprack
     */
    public GreenfootImage getRotationImage(double horAngle, double verAngle) {
        int realHorAngle = Math.floorMod((int) horAngle, 360);
        GreenfootImage image = this.rotationImages[realHorAngle];
        if (image == null || this.rotationVerAngles[realHorAngle] != (int) verAngle) {
            this.rotationVerAngles[realHorAngle] = (int) verAngle;
            image = this.generateRotationImage(realHorAngle, (int) verAngle);
        }
        return image;
    }
    
    /**
     * Get the offset the sprack needs to be displayed at from the topleft corner (inaccurate implementation)
     * 
     * @param verAngle The vertical rotation of the camera, affects the offset as vertical rotation squishes the displayed image
     */
    public Vector2 getCenterOffset(double verAngle) {
        return new Vector2(0, this.diagonal / 2 * verAngle / 45 + this.numOfLayers * Scene.PX);
    }
    
    /**
     * Get the size of sprack in world-space
     * 
     * @return The size of the sprack as a 3D vector
     */
    public Vector3 getSize() {
        return new Vector3(this.w, this.numOfLayers * Scene.PX, this.h);
    }
    
    /**
     * The full width of the displayed image
     */
    public int getFullWidth() {
        return this.fullWidth;
    }
    
    /**
     * The full height of the displayed image
     */
    public int getFullHeight() {
        return this.fullHeight;
    }
}
