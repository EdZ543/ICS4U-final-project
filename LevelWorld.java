import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
     * Checks whether a character is a bird snake character, for level generation
     * 
     * @param levelArray
     * @return Whether the character is >, <, ^, v
     */
    private boolean isSnakeBlock(String[] levelArray, int x, int y) {
        if (y < 0 || y > levelArray.length || x < 0 || x > levelArray[y].length()) return false;
        char c = levelArray[y].charAt(x);
        return c == '>' || c == '<' || c == '^' || c == 'v';
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
        int headX = 0, headY = 0;

        // Generate pieces
        for (int y = 0; y < levelArray.length; y++) {
            for (int x = 0; x < levelArray[y].length(); x++) {
                GridItem gridItem = null;

                switch(levelArray[y].charAt(x)) {
                    case '#':
                        gridItem = new Dirt(x, y);
                        break;
                    case '<':
                        if (!isSnakeBlock(levelArray, x - 1, y)) {
                            birdSnakeHead = new BirdSnakeHead(x, y, 'l');
                            gridItem = birdSnakeHead;
                            headX = x;
                            headY = y;
                        } else gridItem = new BirdSnakePiece(x, y);
                        break;
                    case '>':
                        if (!isSnakeBlock(levelArray, x + 1, y)) {
                            birdSnakeHead = new BirdSnakeHead(x, y, 'r');
                            gridItem = birdSnakeHead;
                            headX = x;
                            headY = y;
                        } else gridItem = new BirdSnakePiece(x, y);
                        break;
                    case '^':
                        if (!isSnakeBlock(levelArray, x, y - 1)) {
                            birdSnakeHead = new BirdSnakeHead(x, y, 'u');
                            gridItem = birdSnakeHead;
                            headX = x;
                            headY = y;
                        } else gridItem = new BirdSnakePiece(x, y);
                        break;
                    case 'v':
                        if (!isSnakeBlock(levelArray, x, y + 1)) {
                            birdSnakeHead = new BirdSnakeHead(x, y, 'd');
                            gridItem = birdSnakeHead;
                            headX = x;
                            headY = y;
                        } else gridItem = new BirdSnakePiece(x, y);
                        break;
                    case 'P':
                        gridItem = new Portal(x, y);
                        break;
                    case 'A':
                        gridItem = new Apple(x, y);
                        break;
                    case 'C':
                        gridItem = new Crate(x, y);
                        break;
                }

                if (gridItem != null) addObject(gridItem, 0, 0);
                grid[y][x] = gridItem;
            }
        }

        // Join bird snake together
        connectBird(headX, headY);
    }

    private void connectBird(int x, int y) {
        String[] levelArray = Levels.LEVELS[level];
        if (grid[y][x] != birdSnakeHead) birdSnakeHead.addPiece(grid[y][x]);

        switch (levelArray[y].charAt(x)) {
            case '<':
                if (isSnakeBlock(levelArray, x + 1, y)) connectBird(x + 1, y);
                break;
            case '>':
                if (isSnakeBlock(levelArray, x + 1, y)) connectBird(x - 1, y);
                break;
            case '^':
                if (isSnakeBlock(levelArray, x + 1, y)) connectBird(x, y - 1);
                break;
            case 'v':
                if (isSnakeBlock(levelArray, x + 1, y)) connectBird(x, y + 1);
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

    public void checkFalling() {

    }

    /**
     * Returns whether or not things are falling in the world
     */
    public boolean getFalling() {
        return falling;
    }
}
