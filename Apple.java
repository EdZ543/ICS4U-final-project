import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Apple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Apple extends Fruit
{
    private static final int MY_ID = 4;
    public Apple(int cellX, int cellY) {
        super(cellX, cellY);
        setImage(new GreenfootImage(50, 50));
    }
    /**
     * Act - do whatever the Apple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void collide(BirdSnake birdsnake) {
        
    }
}