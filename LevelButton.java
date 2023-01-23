import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button for selecting a level to play.
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public class LevelButton extends UIButton
{
    int level;
    boolean active;
    
    /**
     * Constructor.
     * 
     * @param level The level corresponding with this button
     * @param size The button's width and height
     * @param active Whether the button can be clicked
     */
    public LevelButton(int level, int size, boolean active) {
        super(null);
        this.level = level;
        this.active = active;
        
        GreenfootImage image = new GreenfootImage(size, size);
        image.setColor(Color.BLUE);
        image.fill();
        image.setFont(new Font(size));
        image.setColor(Color.WHITE);
        image.drawString(Integer.toString(level), 5, size - 5);
        if (!active) image.setTransparency(50);
        changeImage(image);
    }
    
    public void clicked() {
        if (active) Greenfoot.setWorld(new LevelWorld(level));
    }
    
    public void setActive(boolean a) {
        active = a;
    }
}
