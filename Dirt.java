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
        LevelWorld lw = (LevelWorld)w;
        
        // Draw image
        image = new GreenfootImage(lw.getCellWidth(), lw.getCellWidth());
        image.setColor(Color.BLUE);
        image.fill();
        setImage(image);
    }
}
