import greenfoot.*;

public class SprackLayer extends Actor {
    private int layer;
    private int x;
    private int y;
    private GreenfootImage originalImage;
    private int angle = 0;
    
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
    
    public void rotate(int angle) {
        GreenfootImage newImage = new GreenfootImage(this.originalImage);
        newImage.rotate(angle);
        this.setImage(newImage);
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
}
