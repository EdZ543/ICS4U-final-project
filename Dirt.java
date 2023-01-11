import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The most basic block, a static square
 * 
 * @author Eddie Zhuang 
 * @version (a version number or a date)
 */
public class Dirt extends Block
{
    /**
     * Class constructor.
     * 
     * @param cellX
     * @param cellY 
     */
    public Dirt(int cellX, int cellY) {
        super(cellX, cellY, 1, 1);
        // image = new GreenfootImage(width, width);
        // image.setColor(Color.BLUE);
        // image.fill();
        // setImage(image);
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        image = new GreenfootImage(cellWidth, cellWidth);
        image.setColor(Color.BLUE);
        image.fill();
        setImage(image);
    }

    /**
     * Act - do whatever the TestBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
