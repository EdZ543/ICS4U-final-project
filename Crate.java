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
    
    protected GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage("images/crate.jpeg");
        image.scale(cellWidth, cellWidth);
        return image;
    }
}
