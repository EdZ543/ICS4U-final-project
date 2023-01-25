import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PulsingImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PulsingImage extends AnimatedImage
{
    private double imageWidth, imageHeight, minWidth, minHeight;
    private double pulseFactor;
    private double speed;
    private boolean increase;
    /**
     * @param filePath          image's file path
     * @param speed             speed of the pulsing
     * @param pulseFactor       How much bigger the image should get, as a multiplicative factor; must be greater than 1.
     */
    public PulsingImage(String filePath, double speed, double pulseFactor) {
        super(filePath);
        this.speed = speed;
        this.pulseFactor = pulseFactor;
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        minWidth = imageWidth;
        minHeight = imageHeight;
    }
    /**
     * @param width
     * @param height
     * @param filePath          image's file path
     * @param speed             speed of the pulsing
     * @param pulseFactor       How much bigger the image should get, as a multiplicative factor; must be greater than 1.
     */
    public PulsingImage(String filePath, int width, int height, double speed, double pulseFactor) {
        super(filePath, width, height);
        this.speed = speed;
        this.pulseFactor = pulseFactor;
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        minWidth = imageWidth;
        minHeight = imageHeight;
    }
    
    public void animate() {
        int widthChange, heightChange;
        
        if(increase) {
            imageWidth += speed;
            imageHeight += speed * minHeight/minWidth;
        } else {
            imageWidth -= speed;
            imageHeight -= speed * minHeight/minWidth;
        }
        image = new GreenfootImage(filePath);
        image.scale((int)imageWidth, (int)imageHeight);
        setImage(image);
        
        if(image.getWidth() > minWidth*pulseFactor || image.getHeight() > minHeight*pulseFactor) {
            increase = false;
        } else if(image.getWidth()< minWidth || image.getHeight() < minHeight) {
            increase = true;
        }
    }
}
