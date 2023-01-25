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
    private int clickCldwn;
    private final int COOLDOWN = 15;
    
    private boolean dying;
    private int deathTimer;
    private final int DEATH_TIME = 45;
    private char facingDirection;
    private ArrayList<BirdSnakePiece> bodyPieces;
    private GreenfootSound dyingSound;
    private GreenfootSound[] moveSoundAs, moveSoundBs;
    private int moveSoundIndex;
    /**
     * @param cellX         The x-position of the head
     * @param cellY         The y-position of the head
     */
    public BirdSnakeHead(int cellX, int cellY, char facingDirection) {
        super(cellX, cellY);
        setFacingDirection(facingDirection);
        bodyPieces = new ArrayList<BirdSnakePiece>();
        clickCldwn = 0;
        dyingSound = new GreenfootSound("dead.wav");
        dyingSound.setVolume(90);
        moveSoundAs = new GreenfootSound[5];
        for(int i=0;i<moveSoundAs.length;i++) {
            moveSoundAs[i] = new GreenfootSound("woosh0.wav");
            moveSoundAs[i].setVolume(70);
        }
        moveSoundBs = new GreenfootSound[5];
        for(int i=0;i<moveSoundBs.length;i++) {
            moveSoundBs[i] = new GreenfootSound("woosh1.wav");
            moveSoundBs[i].setVolume(60);
        }
        moveSoundIndex = 0;
    }
    
    public boolean snakeIsSliding() {
        if (sliding) return true;
        for (BirdSnakePiece p : bodyPieces) {
            if (p.isSliding()) return true;
        }
        return false;
    }
    
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        
    }
    protected GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage("birdsnakehead.png");
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
        if(obj != null && obj.getCellX() == slideToCellX && obj.getCellY() == slideToCellY) {
            obj.collide(this);
        }
        
        // Handle keys
        if(clickCldwn>0) {
            clickCldwn--;
        } else if(!snakeIsSliding() && !lw.getFalling()){
            boolean up = (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) && canMoveUp();
            boolean down = (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) && canMoveDown();
            boolean left = (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) && canMoveLeft();
            boolean right = (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) && canMoveRight();
            boolean acted = up || down || left || right;
            if(acted) {
                if(Greenfoot.getRandomNumber(2) == 0) {
                    moveSoundAs[moveSoundIndex].play();
                } else {
                    moveSoundBs[moveSoundIndex].play();
                }
                moveSoundIndex = (moveSoundIndex+1)%moveSoundAs.length;
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
    
    /**
     * @return boolean      Is the piece able to move rightwards
     */
    public boolean canMoveRight() {
        LevelWorld lw = (LevelWorld)getWorld();
        if(cellX >= lw.getGridXLength()-1)return false;
        return !(getItemRight() instanceof Block);
    }
    /**
     * @return boolean      Is the piece able to move leftwards
     */
    public boolean canMoveLeft() {
        if(getCellX() <= 0) return false;
        return !(getItemLeft() instanceof Block);
    }
    /**
     * @return boolean      Is the piece able to move upwards
     */
    public boolean canMoveUp() {
        if(getCellY() <= 0) return false;
        return !(getItemAbove() instanceof Block);
    }
    /**
     * @return boolean      Is the piece able to move downwards
     */
    public boolean canMoveDown() {
        LevelWorld lw = (LevelWorld)getWorld();
        if(cellY + 1 == lw.getGridYLength()) startDying();
        if(cellY >= lw.getGridYLength()-1) return false;
        return !(getItemBelow() instanceof Block);
    }
    
    public char getFacingDirection() {
        return facingDirection;
    }
    
    public void setFacingDirection(char d) {
        facingDirection = d;
        updateSprite(facingDirection);
    }
    
    /**
     * Update the head's sprite
     */
    public void updateSprite(char d) {
        switch(d) {
            case 'u':
                setRotation(270);
                break;
            case 'd':
                setRotation(90);
                break;
            case 'l':
                setRotation(180);
                break;
            case 'r':
                setRotation(0);
                break;
        }
    }
    
    /**
     * @return boolean       whether the SnakeBird should currently be falling
     */
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
        dyingSound.play();
    }
    public boolean isDying() {
        return dying;
    }
    /**
     * Death animation; call each act when the BirdSnake is dying
     */
    public void deathTick() {
        if(deathTimer == DEATH_TIME) {
            LevelWorld lw = (LevelWorld)getWorld();
            for(BirdSnakePiece piece : bodyPieces) {
                lw.removeObject(piece);
            }
            lw.removeObject(this);
            lw.setLevel(lw.getLevel());
        }
        deathTimer ++;
        for(BirdSnakePiece piece: bodyPieces) {
            piece.getImage().setTransparency(255-(int)((255/DEATH_TIME)*deathTimer));
        }
        getImage().setTransparency(255-(int)((255/DEATH_TIME)*deathTimer));
    }
    
    /**
     * Get the last piece in the BirdSnake. If there are no body pieces, return the BirdSnake's head
     */
    public BirdSnakePiece getLastPiece() {
        return bodyPieces.size() == 0 ? this : bodyPieces.get(bodyPieces.size()-1);
    }
    /**
     * Method used by LevelWorld to build snake.
     * NOTE: 
     *      - Snake is built from tail --> piece-before-head.
     *      - bodyPieces is ordered from piece-before-head --> tail
     */
    public void addPiece(BirdSnakePiece piece) {
        // piece.setHeadPiece(this);
        bodyPieces.add(0, piece);
    }
    /**
     * Used by grow method to create a new piece, add to appropriate
     * data sets, and initialize its important variables.
     */
    private void addNewPiece(BirdSnakePiece piece) {
        bodyPieces.add(piece);
        
        LevelWorld lw = (LevelWorld)getWorld();
        
        lw.changeGrid(piece.getCellX(),piece.getCellY(), piece);
        getWorld().addObject(piece, 0, 0);
        
    }
    /**
     * Add piece to the end of the BirdSnake
     */
    public void grow() {
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
    public void resetSnakeColours(int cellWidth) {
        GreenfootImage image;
        for(int i=0;i<bodyPieces.size();i++) {
            image = new GreenfootImage("birdsnakepiece" + i%2 + ".png");
            image.scale(cellWidth, cellWidth);
            bodyPieces.get(i).setImage(image);
        }
    }
}
