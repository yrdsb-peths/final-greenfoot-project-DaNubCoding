import greenfoot.*;

/**
 * Class that contains the data of a single layer of a Sprack
 */
public class SprackLayer extends Actor {
    private int layer;
    private int x;
    private int y;
    private GreenfootImage originalImage;
    
    /**
     * @param layer The index of the layer
     * @param fullHeight The on-screen height of the full Sprack this layer belongs to
     * @param diagonal The length of the diagonal of the image of this layer
     * @param sourceImage The GreenfootImage object that represents this layer's image
     */
    public SprackLayer(int layer, int fullHeight, int diagonal, GreenfootImage sourceImage) {
        this.layer = layer;
        
        this.x = 0;
        this.y = fullHeight - diagonal - layer;
        
        this.originalImage = new GreenfootImage(diagonal, diagonal);
        int x = diagonal / 2 - sourceImage.getWidth() / 2;
        int y = diagonal / 2 - sourceImage.getHeight() / 2;
        this.originalImage.drawImage(sourceImage, x, y);
        this.setImage(this.originalImage);
    }
    
    /**
     * Rotates the layer's image both verticalls and horizontally (vertical rotation is actually scaling).
     * 
     * @param horAngle The horizontal rotation of the layer
     * @param verAngle The vertical rotation of the layer
     */
    public void rotate(int horAngle, int verAngle) {
        GreenfootImage newImage = new GreenfootImage(this.originalImage);
        newImage.rotate(horAngle);
        newImage.scale(newImage.getWidth(), newImage.getHeight() * verAngle / 45);
        this.setImage(newImage);
    }
    
    /**
     * Get the x coordinate of the layer relative to the master Sprack's image.
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Get the y coordinate of the layer relative to the master Sprack's image.
     */
    public int getY() {
        return this.y;
    }
}
