import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Physical object in the world
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
     * @param width  The width of the block, in cells
     * @param height The height of the block, in cells
     */
    public Block(int cellX, int cellY, int width, int height) {
        super(cellX, cellY, width, height);
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
    }
}
