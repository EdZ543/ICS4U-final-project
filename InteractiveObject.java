import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InteractiveObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class InteractiveObject extends GridItem
{
    public InteractiveObject(int cellX, int cellY, int width, int height) {
        super(cellX, cellY, width, height);
    }
    /**
     * What happens when birdsnake hits this object
     * @param birdsnake         The BirdSnake that is touching this object
     */
    public abstract void collide(BirdSnake birdsnake);
    
    public void act()
    {
        // Add your action code here.
    }
}
