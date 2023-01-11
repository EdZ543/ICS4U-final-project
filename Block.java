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
     */
    public Block(int cellX, int cellY) {
        super(cellX, cellY);
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
    }
}
