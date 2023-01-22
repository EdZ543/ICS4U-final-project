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
    private boolean dying;
    private int deathTimer;
    private final int DEATH_TIME = 45;
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
        
        // Handle falling off
        if(checkFallOff()) {
            startDying();
        }
        if(dying) {
            deathTick();
            return;
        }
        // Handle collisions with InteractiveObject objects
        InteractiveObject obj = (InteractiveObject)getOneIntersectingObject(InteractiveObject.class);
        if(obj != null) {
            obj.collide(this);
        }
        
        // Handle keys
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
            if(!snakeShouldFall()) {
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
        if(dying) return false;
        if(!shouldFall()) return false;
        for(BirdSnakePiece piece : bodyPieces) {
            if(!piece.shouldFall()) return false;
        }
        return true;
    }
    /**
     * @return          Whether or not the BirdSnake has fallen off of the map
     */
    public boolean checkFallOff() {
        if(!snakeShouldFall()) return false;
        LevelWorld lw = (LevelWorld)getWorld();
        int bottomY = lw.getGridYLength()-1;
        if(getCellY() == bottomY) return true;
        for(BirdSnakePiece piece : bodyPieces) {
            if(piece.getCellY() >= bottomY) return true;
        }
        return false;
    }
    /**
     * Start BirdSnake's dying sequence, located in act() method.
     */
    public void startDying() {
        dying = true;
    }
    
    public void deathTick() {
        if(deathTimer == DEATH_TIME) {
            LevelWorld lw = (LevelWorld)getWorld();
            for(BirdSnakePiece piece : bodyPieces) {
                lw.removeObject(piece);
            }
            lw.removeObject(this);
            return;
        }
        deathTimer ++;
        for(BirdSnakePiece piece: bodyPieces) {
            piece.getImage().setTransparency(255-(int)((255/DEATH_TIME)*deathTimer));
        }
        getImage().setTransparency(255-(int)((255/DEATH_TIME)*deathTimer));
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
    /**
     * Method used by LevelWorld to build snake.
     * NOTE: 
     *      - Snake is built from tail --> piece-before-head.
     *      - bodyPieces is ordered from piece-before-head --> tail
     */
    public void addPiece(BirdSnakePiece piece) {
        piece.setHeadPiece(this);
        bodyPieces.add(0, piece);
    }
    /**
     * Used by grow method to create a new piece, add to appropriate
     * data sets, and initialize its important variables.
     */
    private void addNewPiece(BirdSnakePiece piece) {
        piece.setHeadPiece(this);
        bodyPieces.add(piece);
        LevelWorld lw = (LevelWorld)getWorld();
        lw.changeGrid(piece.getCellX(),piece.getCellY(), piece);
        getWorld().addObject(piece, 0, 0);
    }
    /**
     * Add piece
     */
    public void grow() {
        // BirdSnakePiece piece = new BirdSnakePiece()
        BirdSnakePiece last = getLastPiece();
        // int[] pos = getOffsetFromDirection(last.getFacingDirection());
        // BirdSnakePiece piece = new BirdSnakePiece(last.getCellX()-pos[0], last.getCellY()-pos[1]);//, this, last);
        BirdSnakePiece piece = new BirdSnakePiece(last.getCellX(), last.getCellY());
        addNewPiece(piece);
    }
    // /**
     // * Remove piece
     // */
    // public void shrink() {
        // if(bodyPieces.size() == 0) return;
        // getWorld().removeObject(bodyPieces.remove(bodyPieces.size()-1));
    // }
    
    public ArrayList<BirdSnakePiece> getPieces() {
        return bodyPieces;
    }
    
}
