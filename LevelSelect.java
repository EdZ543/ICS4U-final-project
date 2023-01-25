import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * World where the player can select a level
 * 
 * @author Eddie Zhuang
 * @version Jan. 23, 2023
 */
public class LevelSelect extends World
{
    private final int SPACING = 40;
    private final int BUTTON_SIZE = 120;
    private Label titleLabel;
    private GreenfootImage bg;
    /**
     * Constructor for objects of class LevelSelect.
     */
    public LevelSelect()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        bg = new GreenfootImage("bg0.png");
        GreenfootImage black = new GreenfootImage(1200, 800);
        black.setTransparency(90);
        black.setColor(Color.BLACK);
        black.fill();
        bg.drawImage(black, 0, 0);
        setBackground(bg);
        // Draw title
        titleLabel = new Label("Select a level!", 80, "Segoe Print");
        GreenfootImage titleImg = titleLabel.getImage();
        SlidingImage slidingTitle = new SlidingImage(null, titleImg.getWidth(), titleImg.getHeight(), 40, 0, 1);
        slidingTitle.setImage(titleImg);
        addObject(slidingTitle, getWidth() / 2 - 20, 100);
        // addObject(titleLabel, getWidth() / 2, 100);
        
        // Draw grid of buttons
        int level = 0, x = SPACING + BUTTON_SIZE / 2, y = SPACING + BUTTON_SIZE / 2 + 160;
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
