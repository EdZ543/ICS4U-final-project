import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fill gaps between grass block sprites
 * 
 * @author Caden Chan
 * @version 2023.01.22
 */
public class Filler extends Foliage
{
    public Filler(Dirt origin, int xOffset, int yOffset) {
        super(origin, "filler");
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    /**
     * Act - do whatever the Filler wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
}
