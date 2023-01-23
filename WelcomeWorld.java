import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WelcomeWorld here.
 * 
 * @author Eddie Zhuang
 * @version (a version number or a date)
 */
public class WelcomeWorld extends World
{
    private StartButton startBtn;
    private Label titleLabel, tempLabel;
    private GreenfootImage bg;
    private GreenfootSound music;
    private static int levelProgress = 0;

    /**
     * Constructor for objects of class WelcomeWorld.
     * 
     */
    public WelcomeWorld()
    {
        super(1200, 800, 1);
        bg = new GreenfootImage("temp/bg.png");
        setBackground(bg);
        titleLabel = new Label("BirdSnake!", 80);
        tempLabel = new Label("(temporary welcome screen)", 30);
        addObject(titleLabel, getWidth()/2, 100);
        addObject(tempLabel, getWidth()/2, 150);

        // Add music
        music = new GreenfootSound("sounds/background-music.mp3");
        music.setVolume(20);

        // Check level progress from UserInfo
        if (UserInfo.isStorageAvailable()) {
            UserInfo myInfo = UserInfo.getMyInfo();
            levelProgress = myInfo.getScore();
        }
        startBtn = new StartButton();
        addObject(startBtn, getWidth()/2, getHeight()-100);
    }

    public void started() {
        music.playLoop();
    }

    public void stopped() {
        music.stop();
    }
    
    public static int getLevelProgress() {
        return levelProgress;
    }
    
    public static void setLevelProgress(int newLevelProgress) {
        levelProgress = newLevelProgress;
    }
}
