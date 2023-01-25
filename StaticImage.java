import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A static image
 * 
 * @author Caden Chan 
 * @version 2023.01.24
 */
public class StaticImage extends AnimatedImage
{
    public StaticImage(String filePath) {
        super(filePath);
    }
    public StaticImage(String filePath, int width, int height) {
        super(filePath, width, height);
    }
    public void animate() {}
}