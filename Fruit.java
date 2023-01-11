import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fruit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Fruit extends InteractiveObject
{
    public Fruit(int cellX, int cellY) {
        super(cellX, cellY, 1, 1);
    }
    public abstract void collide(BirdSnake birdsnake);
    /**
     * Act - do whatever the Fruit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }

}
