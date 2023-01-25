import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AnimatedImage here.
 * 
 * @author Caden Chan
 * @version (a version number or a date)
 */

public abstract class AnimatedImage extends Actor
{
    protected GreenfootImage image;
    protected String filePath;
    public AnimatedImage(String filePath) {
        this.filePath = filePath;
        if(filePath == null) return;
        image = new GreenfootImage(filePath);
        setImage(image);
    }
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
