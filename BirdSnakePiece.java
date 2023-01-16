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
    // sliding - move to GridItem
    protected int targetX, targetY, speed;
    protected boolean sliding;
    /**
     * Independant piece. Used for the BirdSnakeHead subclass
     * @param cellX                 The x-position of the piece
     * @param cellY                 The y-position of the piece
     */
    public BirdSnakePiece(int cellX, int cellY) {
        super(cellX, cellY);
        headPiece = null;
        followPiece = null;
        speed = 8;
    }
    /**
     * @param cellX                 The x-position of the piece
     * @param cellY                 The y-position of the piece
     * @param headPiece             The BirdSnakeHead to which this piece belongs
     * @param followPiece           The BirdSnakePiece that this piece is behind
     */
    public BirdSnakePiece(int cellX, int cellY, BirdSnakeHead headPiece, BirdSnakePiece followPiece) {
        super(cellX, cellY);
        this.headPiece = headPiece;
        this.followPiece = followPiece;
        facingDirection = directionToAdjacentPiece(followPiece);
        speed = 8;
    }
    
    protected GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage("images/apple.png");
        image.scale(cellWidth, cellWidth);
        return image;
    }
    public void act() {
        slideAct();
        if(headPiece.isMoving()) {
            int[] pos = getOffsetFromDirection(facingDirection);
            startSlideToTargetCell(followPiece.getCellX(), followPiece.getCellY(), speed);
        }
        
    }
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        if(headPiece == null)return;
        int cellWidth = ((LevelWorld)w).getCellWidth();
        image = new GreenfootImage("temp/birdsnakepiece" + headPiece.getBodyLength()%2 + ".png");
        image.scale(cellWidth, cellWidth);
        setImage(image);
    }
    
    public boolean isSliding() {
        return sliding;
    }
    
    public void slideAct() {
        if(sliding) {
            int xSpeed = targetX-getX()>0 ? 1 : targetX-getX()<0 ? -1 : 0;
            int ySpeed = targetY-getY()>0 ? 1 : targetY-getY()<0 ? -1 : 0;
            slide(xSpeed*speed, ySpeed*speed);
            if(Math.abs(targetX-getX()) <= speed && Math.abs(targetY-getY()) <= speed) {
                LevelWorld lw = (LevelWorld)getWorld();
                setLocation(targetX, targetY);
                setCellX(lw.getCellX(targetX));
                setCellY(lw.getCellY(targetY));
                if(followPiece != null) {
                    char a = directionToAdjacentPiece(followPiece);
                    System.out.println(a);
                    setFacingDirection(a);
                }
                
                sliding = false;
            }
        }
    }
    public GreenfootImage drawImage(int cellWidth) {
        image = new GreenfootImage("temp/birdsnakepiece" + headPiece.getBodyLength()%2 + ".png");
        image.scale(cellWidth, cellWidth);
        return image;
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
        if(lw.getGridXLength()-1 <= 0) return false;
        startSlideToTargetCell(getCellX()-1, getCellY(), speed);
        return true;
    }
    
    /**
     * Piece moves one cell up
     */
    public boolean moveUp() {
        LevelWorld lw = (LevelWorld)getWorld();
        if(lw.getGridYLength()-1 <= 0) return false;
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
    
    public void slide(int speedX, int speedY) {
        setLocation(getX() + speedX, getY() + speedY);
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
     * sliding "animation" instead of pieces teleporting btwn cells
     * @param x        The x-position where the piece will end up
     * @param y        The y-position where the piece will end up
     */
    public void startSlideToTargetCell(int x, int y, int speed) {
        LevelWorld lw = (LevelWorld)getWorld();
        targetX = lw.getCoordinateX(x);
        targetY = lw.getCoordinateY(y);
        this.speed = speed;
        sliding = true;
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
