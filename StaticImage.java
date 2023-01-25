import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StaticImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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