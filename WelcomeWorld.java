import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main menu screen.
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public class WelcomeWorld extends World
{
    private StartButton startBtn;
    private GreenfootImage bg;
    private static GreenfootSound music;
    private static int levelProgress = 0;
    private Label scoreDisplay;
    private String userName = "";
    private PulsingImage title;
    private SlidingImage playLabel;

    /**
     * Constructor for objects of class WelcomeWorld.
     * 
     */
    public WelcomeWorld()
    {
        super(1200, 800, 1);
        
        // Check level progress and userName from UserInfo
        if (UserInfo.isStorageAvailable()) {
            UserInfo myInfo = UserInfo.getMyInfo();
            levelProgress = myInfo.getScore();
            userName = myInfo.getUserName();
        }
        
        // Draw text and background
        bg = new GreenfootImage("bg0.png");
        setBackground(bg);
        
        title =  new PulsingImage("title.png", 1.9, 1.1);
        addObject(title, getWidth()/2, 150);
        
        playLabel = new SlidingImage("playicon.png", 0, -30, 1);
        addObject(playLabel, getWidth()/2+10, 600);
        if(Levels.LEVELS.length <= levelProgress) {
            scoreDisplay = new Label("Hello " + userName + ", you beat Birdsnake!", 40, "Chalkboard");
        } else {
            scoreDisplay = new Label("Hello " + userName + ", you are at level " + (levelProgress+1), 30, "Chalkboard");
        }
        // addObject(titleLabel, getWidth()/2, 100);
        addObject(scoreDisplay, getWidth()/2, 250);

        // Add music
        music = new GreenfootSound("sounds/background-music.mp3");
        music.setVolume(20);

        // Add start button
        startBtn = new StartButton();
        addObject(startBtn, getWidth()/2, getHeight()-100);
    }

    public void started() {
        music.playLoop();
    }

    public void stopped() {
        music.stop();
    }
    
    /**
     * Returns the background music object
     * 
     * @return GreenfootSound The background music object
     */
    public static GreenfootSound getMusic() {
        return music;
    }
    
    /**
     * Returns the furthest level reached by the player
     * 
     * @return int The furthest level reached by the player
     */
    public static int getLevelProgress() {
        return levelProgress;
    }
    
    /**
     * Sets the furthest level reached by the player
     * 
     * @param newLevelProgress The furthest level reached by the player
     */
    public static void setLevelProgress(int newLevelProgress) {
        levelProgress = newLevelProgress;
    }
}
