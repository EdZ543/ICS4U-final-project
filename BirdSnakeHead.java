import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Stack;
/**
 * Write a description of class BirdSnakeHead here.
 * 
 * @author Caden Chan
 * @version (a version number or a date)
 */
public class BirdSnakeHead extends BirdSnakePiece
{
    // private int facingDirection; 
    private int clickCldwn;
    private boolean moving;
    private Stack<BirdSnakePiece> bodyPieces;
    /**
     * @param cellX         The x-position of the head
     * @param cellY         The y-position of the head
     */
    public BirdSnakeHead(int cellX, int cellY, char facingDirection) {
        super(cellX, cellY);
        // this.facingDirection = facingDirection;
        setFacingDirection(facingDirection);
        bodyPieces = new Stack<BirdSnakePiece>();
        clickCldwn = 0;
        moving = false;
    }
    
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        int cellWidth = ((LevelWorld)w).getCellWidth();
        image = new GreenfootImage("temp/birdsnakehead.png");
        image.scale(cellWidth, cellWidth);
        setImage(image);

        grow();
    }
    public GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage("temp/birdsnakehead.png");
        image.scale(cellWidth, cellWidth);
        return image;
    }
    public void act()
    {
        
        slideAct();
        boolean up = Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up");
        boolean down = Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down");
        boolean left = Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left");
        boolean right = Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right");
        if(clickCldwn>0) {
            clickCldwn--;
            moving = false;
        } else if(!sliding){
            if(right) {
                setFacingDirection('r');
                moving = moveRight();
                clickCldwn = 10;
            } else if(down) {
                setFacingDirection('d');
                moving = moveDown();
                clickCldwn = 10;
            } else if(left) {
                setFacingDirection('l');
                moving = moveLeft();
                clickCldwn = 10;
            } else if(up) {
                setFacingDirection('u');
                moving = moveUp();
                clickCldwn = 10;
            } else if(Greenfoot.isKeyDown("l")) {
                grow();
                clickCldwn = 10;
            }
        }
    
        
    }
    
    public boolean isMoving() {
        return moving;
    }
    
    /**
     * Get the length of the BirdSnake
     */
    public int getBodyLength() {
        return bodyPieces.size();
    }
    
    public BirdSnakePiece getLastPiece() {
        return getBodyLength() == 0 ? this : bodyPieces.peek();
    }
    /**
     * Add piece
     */
    public void grow() {
        // BirdSnakePiece piece = new BirdSnakePiece()
        char d = getFacingDirection();
        BirdSnakePiece last = getLastPiece();
        int[] pos = getOffsetFromDirection(last.getFacingDirection());
        
        BirdSnakePiece piece = new BirdSnakePiece(last.getCellX()-pos[0], last.getCellY()-pos[1], this, last);
        bodyPieces.push(piece);
        getWorld().addObject(piece, 0, 0);
    }
    /**
     * Remove piece
     */
    public void shrink() {
        if(bodyPieces.size() == 0) return;
        getWorld().removeObject(bodyPieces.pop());
    }
    
    public Stack<BirdSnakePiece> getPieces() {
        return bodyPieces;
    }
    
}
