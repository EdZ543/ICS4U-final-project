import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * World where the player can select a level
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public class LevelSelect extends World
{
    private final int SPACING = 20;
    private final int BUTTON_SIZE = 50;
    private Label titleLabel;
    
    /**
     * Constructor for objects of class LevelSelect.
     * 
     * @param levelProgress The last level the player was able to reach
     * 
     */
    public LevelSelect()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        // Draw title
        titleLabel = new Label("Select a level!", 40);
        addObject(titleLabel, getWidth() / 2, 20);
        
        // Draw grid of buttons
        int level = 0, x = SPACING + BUTTON_SIZE / 2, y = SPACING + + BUTTON_SIZE / 2 + 40;
        while (level < Levels.LEVELS.length) {
            LevelButton b = new LevelButton(level, BUTTON_SIZE, (level <= WelcomeWorld.getLevelProgress()));
            addObject(b, x, y);
            
            x += BUTTON_SIZE + SPACING;
            if (x + BUTTON_SIZE / 2 > getWidth() - SPACING) {
                x = SPACING + BUTTON_SIZE / 2;
                y += BUTTON_SIZE + SPACING;
            }
            
            level++;
        }
    }
    public void started() {
        WelcomeWorld.getMusic().playLoop();
    }

    public void stopped() {
        WelcomeWorld.getMusic().stop();
    }
}
