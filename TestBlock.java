import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestBlock extends Block
{
    /**
     * Act - do whatever the TestBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public TestBlock(int cellX, int cellY) {
        super(cellX, cellY);
        // image = new GreenfootImage(width, width);
        // image.setColor(Color.BLUE);
        // image.fill();
        // setImage(image);
    }
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        image = new GreenfootImage(width, width);
        image.setColor(Color.BLUE);
        image.fill();
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
    }
}