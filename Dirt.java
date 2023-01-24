import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The most basic block, a static square
 * 
 * @author Eddie Zhuang 
 * @version (a version number or a date)
 */
public class Dirt extends Block
{
    /**
     * Class constructor.
     * 
     * @param cellX
     * @param cellY 
     */
    public Dirt(int cellX, int cellY) {
        super(cellX, cellY);
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        LevelWorld lw = (LevelWorld)w;
        if(!(getItemAbove() instanceof Dirt)){
            Foliage grass = new Foliage(this, "grass");
            w.addObject(grass, 0, 0);
        }
    }
    
    protected GreenfootImage drawImage(int cellWidth) {
        int rand = Greenfoot.getRandomNumber(100);
        if(rand < 30) {
            image = new GreenfootImage("dirt0.png");
        } else if(rand<50) {
            image = new GreenfootImage("dirt1.png");
        } else if(rand < 60) {
            image = new GreenfootImage("dirt2.png");
        } else if(rand < 80){
            image = new GreenfootImage("dirt3.png");
        } else {
            image = new GreenfootImage("dirt4.png");
        }
        
        image.scale(cellWidth, cellWidth);
        return image;
    }
    
    public boolean shouldFall() {
        return false;
    }
    
    public boolean push(int offsetX, int offsetY) {
        return false;
    }
    /**
     * Fill in unwanted empty spaces between dirt blocks
     */
    public void addFiller() {
        if(getItemBelow() instanceof Dirt) {
            Filler filler = new Filler(this, 0, getImage().getWidth()/2-4);
            getWorld().addObject(filler, 0, 0);
        }
        if(getItemAbove() instanceof Dirt) {
            Filler filler = new Filler(this, 0, -getImage().getWidth()/2+4);
            getWorld().addObject(filler, 0, 0);
        }
    }
}
