import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button for selecting a level to play.
 * 
 * @author Eddie Zhuang
 * @version Jan. 24, 2023
 */
public class LevelButton extends UIButton
{
    private int level;
    private boolean active;
    private Label num;
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
        drawImage(active, size);
        clickSound = new GreenfootSound("buttonhappy.mp3");
        clickSound.setVolume(30);
    }
    
    public void drawImage(boolean active, int size) {
        GreenfootImage image = new GreenfootImage("level-select.png");
        image.scale(size, size);
        image.setFont(new Font("Segoe Print", (int)(size/1.5)));
        image.setColor(Color.WHITE);
        
        // Set text
        int fontSize = (int)(size/1.5);
        int textWidth = Label.textPixelWidth(Integer.toString(level+1), "Segoe Print", fontSize);
        image.drawString(Integer.toString(level+1), size/2 - textWidth/2, fontSize);
        
        // Create copy of image for hover
        GreenfootImage hoverImage = new GreenfootImage(image);
        hoverImage.scale((int)(image.getWidth()*1.2), (int)(image.getHeight()*1.2));  // hover is bigger than actual image
        
        // Make transluscent if inactive
        if(!active) {
            image.setTransparency(50);
            hoverImage.setTransparency(50);
            setActive(false);
        }
        // Set images to superclass
        changeImage(image);
        changeHoverImage(hoverImage);
    }
    
    /**
     * Creates a game world with the associated level.
     */
    public void clicked() {
        if (active) Greenfoot.setWorld(new LevelWorld(level));
        clickSound.play();
    }
}
