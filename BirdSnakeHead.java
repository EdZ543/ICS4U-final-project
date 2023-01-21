import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Stack;
import java.util.ArrayList;
/**
 * Write a description of class BirdSnakeHead here.
 * 
 * @author Caden Chan
 * @version 2023.01.20
 */
public class BirdSnakeHead extends BirdSnakePiece
{
    // private int facingDirection; 
    private int clickCldwn;
    private final int COOLDOWN = 15;
    private ArrayList<BirdSnakePiece> bodyPieces;
    /**
     * @param cellX         The x-position of the head
     * @param cellY         The y-position of the head
     */
    public BirdSnakeHead(int cellX, int cellY, char facingDirection) {
        super(cellX, cellY);
        setFacingDirection(facingDirection);
        bodyPieces = new ArrayList<BirdSnakePiece>();
        clickCldwn = 0;
    }
    
    public void addedToWorld(World w) {
        super.addedToWorld(w);
    }
    protected GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage("temp/birdsnakehead.png");
        image.scale(cellWidth, cellWidth);
        return image;
    }
    public void act()
    {
        LevelWorld lw = (LevelWorld)getWorld();
        slideAct();
        if(clickCldwn>0) {
            clickCldwn--;
        } else if(!sliding){
            boolean up = (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) && canMoveUp();
            boolean down = (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) && canMoveDown();
            boolean left = (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) && canMoveLeft();
            boolean right = (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) && canMoveRight();
            boolean acted = up || down || left || right;
            if(acted) {
                shiftPieces();
                clickCldwn = COOLDOWN;
            }
            if(right) {
                setFacingDirection('r');
                movePiece(1, 0);
            } else if(down) {
                setFacingDirection('d');
                movePiece(0, 1);
            } else if(left) {
                setFacingDirection('l');
                movePiece(-1, 0);
            } else if(up) {
                setFacingDirection('u');
                movePiece(0, -1);
            }
        }
        
    }
    public void shiftPieces() {
        for(int i=bodyPieces.size()-1;i>=0;i--) {
            if(i==0) {
                bodyPieces.get(i).startSlideToTargetCell(getCellX(), getCellY(), speed);
            } else {
                BirdSnakePiece temp = bodyPieces.get(i-1);
                bodyPieces.get(i).startSlideToTargetCell(temp.getCellX(), temp.getCellY(), speed);
            }
        }
    }
    
    public boolean snakeShouldFall() {
        if(!shouldFall()) return false;
        for(BirdSnakePiece piece : bodyPieces) {
            if(!piece.shouldFall()) return false;
        }
        return true;
    }
    
    /**
     * Get the length of the BirdSnake
     */
    public int getBodyLength() {
        return bodyPieces.size();
    }
    
    public BirdSnakePiece getLastPiece() {
        return getBodyLength() == 0 ? this : bodyPieces.get(bodyPieces.size()-1);
    }
    
    public void addPiece(BirdSnakePiece piece) {
        // if(bodyPieces.size() ==0) {
            // piece.setFollowPiece(this);
        // } else {
            // piece.setFollowPiece(bodyPieces.get(bodyPieces.size()-1));
        // }
        piece.setHeadPiece(this);
        // bodyPieces.add(piece);
        // if(bodyPieces.size() == 0) {
            // bodyPieces.add(piece);
        // } el
        bodyPieces.add(0, piece);
    }
    /**
     * Add piece
     */
    public void grow() {
        // BirdSnakePiece piece = new BirdSnakePiece()
        char d = getFacingDirection();
        BirdSnakePiece last = getLastPiece();
        int[] pos = getOffsetFromDirection(last.getFacingDirection());
        
        BirdSnakePiece piece = new BirdSnakePiece(last.getCellX()-pos[0], last.getCellY()-pos[1]);//, this, last);
        bodyPieces.add(piece);
        getWorld().addObject(piece, 0, 0);
    }
    /**
     * Remove piece
     */
    public void shrink() {
        if(bodyPieces.size() == 0) return;
        getWorld().removeObject(bodyPieces.remove(bodyPieces.size()-1));
    }
    
    public ArrayList<BirdSnakePiece> getPieces() {
        return bodyPieces;
    }
    
}
