import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * This is a 2D grid-based puzzle game. Its mechanics can be likened to that of the arcade game Snake, but with gravity.
 * Use WASD or the arrow keys to control the bird snake. Collecting fruits causes you to grow.
 * Collect all fruits in a level and enter the portal to continue to the next level.
 * 
 * Credits
 * - Images
 *   - portal.png: From https://pbs.twimg.com/media/CS4f2mqWsAAbMcR.png, by Apple
 *   - bg.png:
 *   - restart-button.png: from https://www.freeiconspng.com/img/12293 by Ahkâm
 *   - level-select-button.png from http://clipart-library.com/clipart/571254.htm by Clipart Library
 *   - All other sprites created by Caden Chan, with inspiration from the original game, SnakeBird, by Noumenon Games.
 * - Audio
 *   - background music: from https://omori.bandcamp.com/track/lets-get-together-now-2 by omocat
 * 
 * 
 * @author Caden Chan, Eddie Zhuang
 * @version 2023.01.22
 */
public class LevelWorld extends World
{
    private boolean falling = false; // Whether things are currently falling or not
    private GridItem[][] grid;
    private int fruitsLeft = 0;
    private Portal portal = null;
    private int cellWidth, gridXOffset, gridYOffset;
    private int level = 0;
    private int fallingTimer = 0; // Timer for updating falling objects
    private int fallingDelay = 30; // Delay between each downward movement of falling objects
    private BirdSnakeHead birdSnakeHead;

    /**
     * The constructor.
     * 
     * @param level The first level to load
     */
    public LevelWorld(int level)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        setBackground(new GreenfootImage("temp/bg.png"));
        // Put reset button
        RestartButton rb = new RestartButton();
        addObject(rb, 1170, 28);

        // Put level select button
        SelectLevelButton slb = new SelectLevelButton();
        addObject(slb, 1048, 28);

        setPaintOrder(Foliage.class, Block.class, InteractiveObject.class, Filler.class);
        this.level = level;
        setLevel(this.level);
    }

    public void act() {
        // Check if things need to fall
        if (!birdSnakeHead.snakeIsSliding()) {
            checkFalling();
        }

        // Make things fall
        if (falling) {
            if (fallingTimer % fallingDelay == 0) {
                ArrayList<GridItem> toFall = new ArrayList<GridItem>();
                // Make bird snake fall
                if (birdSnakeHead.snakeShouldFall()) {
                    toFall.add(birdSnakeHead);
                    for(BirdSnakePiece p : birdSnakeHead.getPieces()) {
                        toFall.add(p);
                    }
                }

                if (toFall.size() > 0) {
                    for (GridItem gi : toFall) {
                        gi.startSlideToTargetCell(gi.getCellX(), gi.getCellY() + 1, 5);
                    }
                } else {
                    falling = false;
                    fallingTimer = 0;
                }
            }
            fallingTimer++;
        }
    }
    public void started() {
        WelcomeWorld.getMusic().playLoop();
    }

    public void stopped() {
        WelcomeWorld.getMusic().stop();
    }

    /**
     * Changes the level
     * 
     * @param level The new level number
     */
    public void setLevel(int level) {
        this.level = level;
        resetLevel();

        String[] levelArray = Levels.LEVELS[level];
        int levelHeight = levelArray.length * Levels.CELL_WIDTHS[level];
        int levelWidth = levelArray[0].length() * Levels.CELL_WIDTHS[level];
        int offsetX = (getWidth() - levelWidth) / 2;
        int offsetY = (getHeight() - levelHeight) / 2;
        renderLevel(Levels.CELL_WIDTHS[level], offsetX, offsetY, Levels.LEVELS[level]);
        birdSnakeHead.resetSnakeColours(cellWidth);
        // Update user progress on gallery
        if (level > WelcomeWorld.getLevelProgress()) {
            WelcomeWorld.setLevelProgress(level);
            if (UserInfo.isStorageAvailable()) {
                UserInfo myInfo = UserInfo.getMyInfo();
                myInfo.setScore(level);
                myInfo.store();  // write back to server
            }
        }
    }

    /**
     * Destroys current level
     */
    public void resetLevel() {
        removeObject(birdSnakeHead);
        removeObject(portal);

        if (grid == null) return;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) removeObject(grid[i][j]);
            }
        }
    }

    /**
     * Returns the current level number
     * 
     * @return int The current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Checks whether a character is a bird snake character, for level generation
     * 
     * @param   levelArray The level array
     * @param x The x position being checked
     * @param y the y position being checked
     * @return  Whether the character is >, <, ^, v
     */
    private boolean isSnakeBlock(String[] levelArray, int x, int y) {
        if (isYOutOfBounds(levelArray, y) || isXOutOfBounds(levelArray, x)) return false;
        char c = levelArray[y].charAt(x);
        return c == '>' || c == '<' || c == '^' || c == 'v';
    }

    /**
     * Checks whether a character is the tail of the bird snake
     * 
     * @param   levelArray The level array
     * @param x The x position being checked
     * @param y the y position being checked
     * @return  boolean Whether the item is the bird snake tail
     */
    private boolean isTail(String[] levelArray, int x, int y) {
        if (x - 1 >= 0 && levelArray[y].charAt(x - 1) == '>') return false;
        if (x + 1 < levelArray[y].length() && levelArray[y].charAt(x + 1) == '<') return false;
        if (y - 1 >= 0 && levelArray[y - 1].charAt(x) == 'v') return false;
        if (y + 1 < levelArray.length && levelArray[y + 1].charAt(x) == '^') return false;
        return true;
    }

    /**
     * Builds the world based on a String array representation
     * 
     * @param cellWidth     The cell width
     * @param gridXOffset   The x position to start the grid
     * @param gridYOffset   The y position to start the grid
     * @param levelArray    The String array representation
     */
    public void renderLevel(int cellWidth, int gridXOffset, int gridYOffset, String[] levelArray) {
        this.cellWidth = cellWidth;
        this.gridXOffset = gridXOffset;
        this.gridYOffset = gridYOffset;

        grid = new GridItem[levelArray.length][levelArray[0].length()];
        fruitsLeft = 0;
        int tailX = 0, tailY = 0;

        // Generate pieces
        for (int y = 0; y < levelArray.length; y++) {
            for (int x = 0; x < levelArray[y].length(); x++) {
                // Add non-snake pieces
                switch(levelArray[y].charAt(x)) {
                    case '#':
                        grid[y][x] = new Dirt(x, y);
                        break;
                    case 'P':
                        grid[y][x] = new Portal(x, y);
                        portal = (Portal)grid[y][x];
                        break;
                    case 'A':
                        grid[y][x] = new Apple(x, y);
                        fruitsLeft++;
                        break;
                    case 'S':
                        grid[y][x] = new Spike(x, y);
                        break;
                }
                if (grid[y][x] != null) addObject(grid[y][x], 0, 0);

                // Add snake pieces
                if (isSnakeBlock(levelArray, x, y)) {
                    grid[y][x] = new BirdSnakePiece(x, y);
                    if (isTail(levelArray, x, y)) {
                        tailX = x;
                        tailY = y;                            
                    }
                }

                // Add snake head
                switch(levelArray[y].charAt(x)) {
                    case '<':
                        if (!isSnakeBlock(levelArray, x - 1, y)) {
                            birdSnakeHead = new BirdSnakeHead(x, y, 'l');
                            grid[y][x] = birdSnakeHead;
                        }
                        break;
                    case '>':
                        if (!isSnakeBlock(levelArray, x + 1, y)) {
                            birdSnakeHead = new BirdSnakeHead(x, y, 'r');
                            grid[y][x] = birdSnakeHead;
                        }
                        break;
                    case '^':
                        if (!isSnakeBlock(levelArray, x, y - 1)) {
                            birdSnakeHead = new BirdSnakeHead(x, y, 'u');
                            grid[y][x] = birdSnakeHead;
                        }
                        break;
                    case 'v':
                        if (!isSnakeBlock(levelArray, x, y + 1)) {
                            birdSnakeHead = new BirdSnakeHead(x, y, 'd');
                            grid[y][x] = birdSnakeHead;
                        }
                        break;
                }
            }
        }
        // Fill in unwanted empty spaces between dirt blocks
        addDirtFiller();
        
        // Edge case: no fruits
        if (fruitsLeft == 0) {
            portal.activate();
        }

        // Join bird snake together
        connectBird(tailX, tailY);
    }

    /**
     * Recursive method to add all bird snake pieces to the head's arraylist.
     * 
     * @param x The x position of the bird snake piece in the grid
     * @param y The y position of the bird snake piece in the grid
     */
    private void connectBird(int x, int y) {
        String[] levelArray = Levels.LEVELS[level];

        // Add piece to head's array
        if (grid[y][x] != birdSnakeHead) {
            birdSnakeHead.addPiece((BirdSnakePiece)grid[y][x]);
        }

        addObject(grid[y][x], 0, 0);

        // Recursively call on whatever piece this one is pointing at
        switch (levelArray[y].charAt(x)) {
            case '<':
                if (isSnakeBlock(levelArray, x - 1, y)) connectBird(x - 1, y);
                break;
            case '>':
                if (isSnakeBlock(levelArray, x + 1, y)) connectBird(x + 1, y);
                break;
            case '^':
                if (isSnakeBlock(levelArray, x, y - 1)) connectBird(x, y - 1);
                break;
            case 'v':
                if (isSnakeBlock(levelArray, x, y + 1)) connectBird(x, y + 1);
                break;
        }
    }

    /**
     * Returns an item from the 2d grid
     * 
     * @param cellX             The x-position, in cells, of the item
     * @param cellY             The y-position, in cells, of the item
     * @return GridItem         The item at the position; returns null if no item exists there.
     */
    public GridItem getItem(int cellX, int cellY) {
        // check if desired x and y values are out of bounds
        if(isXOutOfBounds(cellX) || isYOutOfBounds(cellY)) return null;
        return grid[cellY][cellX];
    }

    /**
     * Returns the width of the grid in cells
     * 
     * return int       Number of columns in the grid (# of cells per row)
     */
    public int getGridXLength() {
        return grid[0].length;
    }

    /**
     * Returns the height of the grid in cells
     * 
     * return int       Number of rows in the grid (# of cells per column)
     */
    public int getGridYLength() {
        return grid.length;
    }

    /**
     * Understanding cellX/cellY & coordinateX/coordinateY
     * [A][ ][ ][ ]
     * [ ][ ][B][ ]
     * [ ][ ][ ][ ]
     * cellX of A : 0           coordinateX of A or B : x-value of respective cell in the greenfoot world
     * cellY of A : 0           coordinateY of A or B : y-value of respective cell in the greenfoot world
     *                                                      ^^points to the center of the cell
     * cellX of B : 2
     * cellY of B : 1
     */

    /**
     * Gets grid coordinate based on world coordinate
     * 
     * @param cellNumber        row number pertaining to the grid
     * @return int              x-coordinate in Greenfoot world pertaining to given row number
     */
    public int getCoordinateX (int cellNumber){
        return (cellNumber * cellWidth) + gridXOffset + cellWidth/2;
    }

    /**
     * Gets grid coordinate based on world coordinate
     * 
     * @param coordinate        x-coordinate in the Greenfoot world
     * @return int              row number where the x-coordinate is located
     */
    public int getCellX(int coordinate){
        return (coordinate - gridXOffset) / cellWidth;
    }

    /**
     * Gets world coordinate based on grid coordinate
     * 
     * @param cellNumber        column number pertaining to the grid
     * @return int              y-coordinate in Greenfoot world pertaining to given column number
     */
    public int getCoordinateY (int cellNumber){
        return (cellNumber * cellWidth) + gridYOffset + cellWidth/2;
    }

    /**
     * Gets world coordinate based on grid coordinate
     * 
     * @param coordinate        y-coordinate in the Greenfoot world
     * @return int              column number where the y-coordinate is located
     */
    public int getCellY(int coordinate){
        return (coordinate - gridYOffset) / cellWidth;
    }

    /**
     * Gets the current cell size
     * 
     * @return int          Cell width
     */
    public int getCellWidth() {
        return cellWidth;
    }

    /**
     * Checks whether an x-coordinate is out of bounds
     * 
     * @param x         The x-coordinate
     * @return boolean  Whether the x-coordinate is out of bounds
     */
    public boolean isXOutOfBounds(int x) {
        return x < 0 || x >= getGridXLength();
    }

    /**
     * Checks whether an x-coordinate is out of bounds
     * 
     * @param levelArray    The String array representation of the level
     * @param x             the x-coordinate
     * @return boolean      Whether the x-coordinate is out of bounds
     */
    public boolean isXOutOfBounds(String[] levelArray, int x) {
        return x < 0 || x >= levelArray[0].length();
    }

    /**
     * Checks whether an y-coordinate is out of bounds
     * 
     * @param y         The y-coordinate
     * @return boolean  Whether the y-coordinate is out of bounds
     */
    public boolean isYOutOfBounds(int y) {
        return y < 0 || y >= getGridYLength();
    }

    /**
     * Checks whether a y-coordinate is out of bounds
     * 
     * @param levelArray    The String array representation of the level
     * @param y             the y-coordinate
     * @return boolean      Whether the y-coordinate is out of bounds
     */
    public boolean isYOutOfBounds(String[] levelArray, int y) {
        return y < 0 || y >= levelArray.length;
    }

    /**
     * Method that makes everything in the world fall
     */
    public void checkFalling() {
        fallingTimer = 0;
        falling = true;
    }

    /**
     * Returns whether or not things are falling in the world
     * 
     * @return boolean Whether or not things are falling in the world
     */
    public boolean getFalling() {
        return falling;
    }

    /**
     * Alters the grid array
     */
    public void changeGrid(int x, int y, GridItem item) {
        if (y >= 0 && y < grid.length && x >= 0 && x < grid[y].length) {
            grid[y][x] = item;
        }
    }

    /**
     * Decrease fruitsLeft count by 1, and activates portal if all are eaten
     */
    public void eatFruit() {
        fruitsLeft--;
        if (fruitsLeft == 0) {
            portal.activate();
        }
    }

    public BirdSnakeHead getBirdSnakeHead() {
        return birdSnakeHead;
    }
    /**
     * Fill in unwanted empty spaces between dirt blocks
     */
    public void addDirtFiller() {
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++) {
                if(grid[i][j] instanceof Dirt) {
                    ((Dirt)grid[i][j]).addFiller();
                }
            }
        }
    }
}
