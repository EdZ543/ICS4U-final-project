import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Solid object in the world
 * 
 * @author Eddie Zhuang
 * @version 2023.01.05
 */
public abstract class Block extends GridItem
{
    /**
     * Class constructor.
     * 
     * @param cellX  The x-position
     * @param cellY  The y-position
     */
    public Block(int cellX, int cellY) {
        super(cellX, cellY);
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
    }
    
    /**
     * Attempts to move the item in a certain direction
     * 
     * @param offsetX The number of cells to change the x position by
     * @param offsetY The number of cells to change the y position by
     * @return Whether the item was able to be pushed         
     */
    public abstract boolean push(int offsetX, int offsetY);
}
