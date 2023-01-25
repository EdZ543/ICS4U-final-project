import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclass containing different types of image animations
 * 
 * @author Caden Chan
 * @version 2023.01.24
 */

public abstract class AnimatedImage extends Actor
{
    protected GreenfootImage image;
    protected String filePath;
    /**
     * @param filePath          image's file path
     */
    public AnimatedImage(String filePath) {
        this.filePath = filePath;
        if(filePath == null) return;
        image = new GreenfootImage(filePath);
        setImage(image);
    }
    /**
     * @param filePath          image's file path
     * @param width             image's initial width
     * @param height            image's initial height
     */
    public AnimatedImage(String filePath, int width, int height) {
        this.filePath = filePath;
        if(filePath == null) return;
        image = new GreenfootImage(filePath);
        image.scale(width, height);
        setImage(image);
    }
    public void act()
    {
        animate();
    }
    
    public abstract void animate();
}
