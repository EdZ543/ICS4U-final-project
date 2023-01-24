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
    private Label titleLabel, tempLabel;
    private GreenfootImage bg;
    private static GreenfootSound music;
    private static int levelProgress = 0;
    private String userName = "player";

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
        bg = new GreenfootImage("temp/bg.png");
        setBackground(bg);
        titleLabel = new Label("BirdSnake!", 80);
        tempLabel = new Label("Hello " + userName + ", you've gotten as far as level " + levelProgress, 30);
        addObject(titleLabel, getWidth()/2, 100);
        addObject(tempLabel, getWidth()/2, 150);

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
