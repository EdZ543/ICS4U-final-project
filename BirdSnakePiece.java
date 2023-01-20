import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BirdSnakePiece here.
 * 
 * @author Caden Chan
 * @version (a version number or a date)
 */
public class BirdSnakePiece extends Block
{
    private BirdSnakeHead headPiece;
    private BirdSnakePiece followPiece;
    protected char facingDirection;
    int actCount;
    /**
     * Independant piece. Used for the BirdSnakeHead subclass
     * @param cellX                 The x-position of the piece
     * @param cellY                 The y-position of the piece
     */
    public BirdSnakePiece(int cellX, int cellY) {
        super(cellX, cellY);
        headPiece = null;
        followPiece = null;
        speed = 5;
    }
    /**
     * @param cellX                 The x-position of the piece
     * @param cellY                 The y-position of the piece
     * @param facingDirection       The direction that this piece faces
     */
    public BirdSnakePiece(int cellX, int cellY, char facingDirection) {
        super(cellX, cellY);
        headPiece = null;
        followPiece = null;
        speed = 5;
        setFacingDirection(facingDirection);
    }
    public boolean push(int xOffset, int yOffset) {
        return false;
    }
    protected GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage("temp/birdsnakepiece1.png");
        image.scale(cellWidth+1, cellWidth+1);
        return image;
    }
    public void act() {
        slideAct();
        if(actCount <= 0) {
            actCount = 60;
        }
        actCount --;
        
    }
    public void addedToWorld(World w) {
        super.addedToWorld(w);
    }
    
    public void onSlideFinished() {
        if(followPiece != null) {
            char a = directionToAdjacentPiece(followPiece);
            setFacingDirection(a);
        }
    }
    public void setHeadPiece(BirdSnakeHead head) {
        headPiece = head;
    }
    
    public BirdSnakeHead getHeadPiece() {
        return headPiece;
    }
    public void setFollowPiece(BirdSnakePiece piece) {
        followPiece = piece;
        facingDirection = directionToAdjacentPiece(followPiece);
    }
    
    public boolean shouldFall() {
        LevelWorld lw = (LevelWorld)getWorld();
        if (cellY == lw.getGridYLength() - 1) return true;
        GridItem below = getItemBelow();
        if (below == null || below.shouldFall()) return true;
        return false;
    }
    
    public boolean canMoveRight() {
        LevelWorld lw = (LevelWorld)getWorld();
        if(cellX >= lw.getGridXLength()-1)return false;
        return !(getItemRight() instanceof Block);
    }
    
    public boolean canMoveLeft() {
        if(getCellX() <= 0) return false;
        return !(getItemLeft() instanceof Block);
    }
    
    public boolean canMoveUp() {
        if(getCellY() <= 0) return false;
        return !(getItemAbove() instanceof Block);
    }
    public boolean canMoveDown() {
        LevelWorld lw = (LevelWorld)getWorld();
        if(cellY >= lw.getGridYLength()-1) return false;
        return !(getItemBelow() instanceof Block);
    }
    /**
     * Piece moves one cell to the right
     */
    public boolean moveRight() {
        LevelWorld lw = (LevelWorld)getWorld();
        if(cellX >= lw.getGridXLength()-1) return false;
        startSlideToTargetCell(getCellX()+1, getCellY(), speed);
        return true;
    }
    
    /**
     * Piece moves one cell to the left
     */
    public boolean moveLeft() {
        LevelWorld lw = (LevelWorld)getWorld();
        if(getCellX() <= 0) return false;
        startSlideToTargetCell(getCellX()-1, getCellY(), speed);
        return true;
    }
    
    /**
     * Piece moves one cell up
     */
    public boolean moveUp() {
        LevelWorld lw = (LevelWorld)getWorld();
        if(getCellY() <= 0) return false;
        startSlideToTargetCell(getCellX(), getCellY()-1, speed);
        return true;
    }
    /**
     * Piece moves one cell down
     */
    public boolean moveDown() {
        LevelWorld lw = (LevelWorld)getWorld();
        if(cellY >= lw.getGridYLength()-1) return false;
        startSlideToTargetCell(getCellX(), getCellY()+1, speed);
        return true;
    }
    
    /**
     * @return char      The direction that the BirdSnake is facing
     */
    public char getFacingDirection() {
        return facingDirection;
    }
    /**
     * @param d         The direction to make the BirdSnake face.
     */
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
     * Return the offset that is needed to move in a certain direction.
     * Valid directions are:
     * "r" = right
     * "l" = left
     * "d" = down
     * "u" = up
     * 
     * @param d             The direction, as a single character
     */
    public int[] getOffsetFromDirection(char d) {
        int[] pos = new int[2];
        switch (d) {
            case 'u': 
                pos[0] = 0;
                pos[1] = -1;
            case 'd':
                pos[0] = 0;
                pos[1] = 1;
            case 'l':
                pos[0] = -1;
                pos[1] = 0;
            case 'r':
                pos[0] = 1;
                pos[1] = 0;
        }
        return pos;
    }
    
    
    /**
     * OPTIONAL: effect that gives visual feedback to the player, to show that they can't move in 
     * that direction, due to some sort of obstacle
     * @param targetCellX        The x-position that the BirdSnake is bumping into
     * @param targetCellY        The y-position that the BirdSnake is bumping into
     */
    public void bumpIntoCell(int targetCellX, int targetCellY) {
        
    }
    /**
     * Compare this piece with an adjacent piece.
     * Example:
     * [n] = one piece
     * 
     * [D][C]
     *    [B][A] 
     *  A.directionToAdjacentPiece(B) --> 'l'
     *  B.directionToAdjacentPiece(A) --> 'r'
     *  B.directionToAdjacentPiece(C) --> 'u'
     *  
     *  Useful method to determine the physics of how the pieces move
     */
    public char directionToAdjacentPiece(BirdSnakePiece other) { // return "u","d","l","r" for up down left right
        if(other.getCellX() == getCellX()) {
            int yDiff = other.getCellY() - getCellY();
            if(yDiff == 1) {
                return 'd';
            } else if(yDiff == -1) {
                return 'u';
            }
        } else if(other.getCellY() == getCellY()) {
            int xDiff = other.getCellX() - getCellX();
            if(xDiff == 1) {
                return 'r';
            } else if(xDiff == -1) {
                return 'l';
            } 
        }
        return ' ';
    }
}
