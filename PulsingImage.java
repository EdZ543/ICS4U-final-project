import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Image that dilates and contracts
 * 
 * @author Caden Chan
 * @version 2023.01.24
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
     * @param width             initial image width
     * @param height            initial image height
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
        
        if(increase) {              // increase imageWidth and imageHeight if image pulsation = getting bigger
            imageWidth += speed;
            imageHeight += speed * minHeight/minWidth;      // proportional increase
        } else {                    // decrease imageWidth and imageHeight if image pulsation = getting smaler
            imageWidth -= speed;
            imageHeight -= speed * minHeight/minWidth;      // proportional increase
        }
        image = new GreenfootImage(filePath);
        image.scale((int)imageWidth, (int)imageHeight);
        setImage(image);
        // if reach maxWidth/maxHeight (based on pulseFactor), start decreasing
        if(image.getWidth() > minWidth*pulseFactor || image.getHeight() > minHeight*pulseFactor) {
            increase = false;
        // if reach minWidth/minHeight, start increasing
        } else if(image.getWidth()< minWidth || image.getHeight() < minHeight) {
            increase = true;
        }
    }
}
