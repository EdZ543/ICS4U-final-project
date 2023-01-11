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
        super(cellX, cellY);
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        
        // Draw image
        image = new GreenfootImage(cellWidth, cellWidth);
        image.setColor(Color.BROWN);
        image.fill();
        setImage(image);
    }
}
