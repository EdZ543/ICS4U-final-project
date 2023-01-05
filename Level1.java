import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends Grid
{

    /**
     * Constructor for objects of class Level1.
     * 
     */
    private Outline gridTester;
    public Level1()
    {
        super(0, 0);
        gridTester = new Outline(Color.GREEN);
        addObject(gridTester, 10, 20);
    }
}
