import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Image that slides back and forth
 * 
 * @author Caden Chan
 * @version 2023.01.24
 */
public class SlidingImage extends AnimatedImage
{
    private int initialX, initialY;
    private int offsetX, offsetY;
    private int speed;
    private boolean goBack;
    /**
     * @param filePath          image's file path
     * @param offsetX           total x-movement
     * @param offsetY           total y-movement
     * @param speed             sliding speed
     */
    public SlidingImage(String filePath, int offsetX, int offsetY, int speed) {
        super(filePath);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.speed = speed;
        goBack = false;
    }
    /**
     * @param filePath          image's file path
     * @param width             initial image width
     * @param height            initial image height
     * @param offsetX           total x-movement
     * @param offsetY           total y-movement
     * @param speed             sliding speed
     */
    public SlidingImage(String filePath, int width, int height, int offsetX, int offsetY, int speed) {
        super(filePath, width, height);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.speed = speed;
        goBack = false;
    }
    public void addedToWorld(World w) {
        initialX = getX();
        initialY = getY();
    }
    /**
     * Animate sliding
     */
    public void animate() {
        if(goBack) {
            turnTowards(initialX, initialY);
        } else {
            turnTowards(initialX+offsetX, initialY+offsetY);
        }
       
        // move(speed);
        for(int i = 0;i<speed;i++) {
            move(speed);
            if(getX() == initialX+offsetX && getY() == initialY+offsetY) {  // if moved enough, start sliding back
                goBack = true;
                setRotation(0);
                return;
            } else if(getX() == initialX && getY() == initialY) {           // if moved back to initial position, restart sliding
                goBack = false;
                setRotation(0);
                return;
            }
        }
        setRotation(0);
    }
}
