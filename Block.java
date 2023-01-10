import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Physical object in the world
 * 
 * @author Eddie Zhuang
 * @version 2023.01.05
 */
public abstract class Block extends GridItem
{
    // Basic properties
    protected int length, width;

    /**
     * Class constructor.
     * 
     * @param cellX  The x-position
     * @param cellY  The y-position
     * @param width  The width of the block, in cells
     * @param height The height of the block, in cells
     */
    public Block(int cellX, int cellY, int width, int height) {
        super(cellX, cellY);
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        width = ((Grid)w).getCellSize();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int w) {
        width = w;
    }
}
