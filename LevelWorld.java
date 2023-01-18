import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The main game world.
 * 
 * Credits
 * - Images
 *      - apple.png: From https://creazilla.com/nodes/3170476-apple-clipart, by Clker-Free-Vector-Images on Pixabay
 *      - portal.png: From https://pbs.twimg.com/media/CS4f2mqWsAAbMcR.png, by Apple
 *      - crate.jpeg: From http://clipart-library.com/clipart/810972.htm, by Clipart Library
 * 
 * @author Caden Chan, Eddie Zhuang
 * @version (a version number or a date)
 */
public class LevelWorld extends World
{
    private boolean falling = false; // Whether things are currently falling or not
    private GridItem[][] grid;
    private int cellWidth, gridXOffset, gridYOffset;
    private int level = 0;
    private int fallingTimer = 0; // Timer for updating falling objects
    private int fallingDelay = 30; // Delay between each downward movement of falling objects
    private BirdSnakeHead birdSnakeHead;

    public LevelWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);

        setLevel(level);
    }
    
    public void act() {
        if (falling) {
            fallingTimer++;
            if (fallingTimer % fallingDelay == 0) {
                ArrayList<GridItem> toFall = new ArrayList<GridItem>();

                for (int y = 0; y < grid.length; y++) {
                    for (int x = 0; x < grid[y].length; x++) {
                        if (grid[y][x].shouldFall()) {
                            toFall.add(grid[y][x]);
                        }
                    }
                }

                if (toFall.size() > 0) {
                    for (GridItem gi : toFall) {
                        gi.setCellY(gi.getCellY() + 1);
                    }
                } else {
                    falling = false;
                }
            }
        }
    }

    /**
     * Changes the level
     * 
     * @param level The new level number
     */
    public void setLevel(int level) {
        this.level = level;
        createLevel(Levels.CELL_WIDTHS[level], Levels.LEVEL_OFFSETS[level][0], Levels.LEVEL_OFFSETS[level][1], Levels.LEVELS[level]);
    }
    
    /**
     * Returns the current level number
     */
    public int getLevel() {
        return level;
    }

    /**
     * Checks whether a character is a bird snake character, for level generation
     * 
     * @param levelArray The level array
     * @param x The x position being checked
     * @param y the y position being checked
     * @return Whether the character is >, <, ^, v
     */
    private boolean isSnakeBlock(String[] levelArray, int x, int y) {
        if (y < 0 || y >= levelArray.length || x < 0 || x >= levelArray[y].length()) return false;
        char c = levelArray[y].charAt(x);
        return c == '>' || c == '<' || c == '^' || c == 'v';
    }

    /**
     * Checks whether a character is the tail of the bird snake
     * 
     * @param levelArray The level array
     * @param x The x position being checked
     * @param y the y position being checked
     */
    private boolean isTail(String[] levelArray, int x, int y) {
        if (x - 1 > 0 && levelArray[y].charAt(x - 1) == '>') return false;
        if (x + 1 < levelArray[y].length() && levelArray[y].charAt(x + 1) == '<') return false;
        if (y - 1 > 0 && levelArray[y - 1].charAt(x) == 'v') return false;
        if (y + 1 < levelArray.length && levelArray[y + 1].charAt(x) == '^') return false;
        return true;
    }

    /**
     * Builds the world based on a 2D array representation
     * 
     * @param levelArray The 2D array
     */
    public void createLevel(int cellWidth, int gridXOffset, int gridYOffset, String[] levelArray) {
        this.cellWidth = cellWidth;
        this.gridXOffset = gridXOffset;
        this.gridYOffset = gridYOffset;

        grid = new GridItem[levelArray.length][levelArray[0].length()];
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
                        break;
                    case 'A':
                        grid[y][x] = new Apple(x, y);
                        break;
                    case 'C':
                        grid[y][x] = new Crate(x, y);
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

        // Join bird snake together
        connectBird(tailX, tailY);
    }

    /**
     * Recursive method to add all bird snake pieces to the head's arraylist.
     * 
     * @param x The x position of the piece in the grid
     * @param y The y position of the piece in the grid
     */
    private void connectBird(int x, int y) {
        String[] levelArray = Levels.LEVELS[level];

        if (grid[y][x] != birdSnakeHead) {
            birdSnakeHead.addPiece((BirdSnakePiece)grid[y][x]);
        }

        addObject(grid[y][x], 0, 0);

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
     * @param cellX             The x-position, in cells, of the item
     * @param cellY             The y-position, in cells, of the item
     * @return GridItem         The item at the position; returns null if no item exists there.
     */
    public GridItem getItem(int cellX, int cellY) {
        return grid[cellY][cellX];
    }

    /**
     * Assumes that the 2-d array creates a perfect square shape
     * return int       Number of columns in the grid (# of cells per row)
     */
    public int getGridXLength() {
        return grid[0].length;
    }

    /**
     * Assumes that the 2-d array creates a perfect square shape
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
     * @param cellNumber        row number pertaining to the grid
     * @return int              x-coordinate in Greenfoot world pertaining to given row number
     */
    public int getCoordinateX (int cellNumber){
        return (cellNumber * cellWidth) + gridXOffset + cellWidth/2;
    }

    /**
     * @param coordinate        x-coordinate in the Greenfoot world
     * @return int              row number where the x-coordinate is located
     */
    public int getCellX(int coordinate){
        return (coordinate - gridXOffset) / cellWidth;
    }

    /**
     * @param cellNumber        column number pertaining to the grid
     * @return int              y-coordinate in Greenfoot world pertaining to given column number
     */
    public int getCoordinateY (int cellNumber){
        return (cellNumber * cellWidth) + gridYOffset + cellWidth/2;
    }

    /**
     * @param coordinate        y-coordinate in the Greenfoot world
     * @return int              column number where the y-coordinate is located
     */
    public int getCellY(int coordinate){
        return (coordinate - gridYOffset) / cellWidth;
    }

    /**
     * @return int          Cell width
     */
    public int getCellWidth() {
        return cellWidth;
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
     */
    public boolean getFalling() {
        return falling;
    }
}
