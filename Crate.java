import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Block that can be pushed around, and has gravity.
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public class Crate extends Block
{
    protected boolean falling = false;
    
    /**
     * Class constructor.
     */
    public Crate(int cellX, int cellY) {
        super(cellX, cellY);
    }
    
    /**
     * Act - do whatever the Crate wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {}
    
    /**
     * Makes crate move down, and stop falling it if lands on something.
     */
    public void fall() {}
}
