import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spike here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spike extends Block
{
    
    public Spike(int cellX, int cellY) {
        super(cellX, cellY);
    }
    /**
     * Act - do whatever the Spike wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    protected GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage(cellWidth, cellWidth);
        image.setColor(Color.RED);
        image.fill();
        return image;
    }
    public boolean push(int offsetX, int offsetY) {
        return false;
    }
    public boolean shouldFall() {
        return false;
    }
    
}
