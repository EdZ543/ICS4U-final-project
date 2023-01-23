/**
 * Arrays of all levels in the game
 * 
 * @author Eddie Zhuang, Caden Chan
 * @version (a version number or a date)
 */
public class Levels  
{
    // Level arrays
    public static final String[][] LEVELS = {
        {
            "          ",
            "          ",
            "          ",
            "          ",
            " A   ^    ",
            "   >>^    ",
            "   ^## A  ",
            "    ##    ",
            "  ####### ",
            "##########",
        },
        {
            "   P  ",
            "      ",
            "#    #",
            "A  #A#",
            ">>v   ",
            " #>>  ",
            " #####",
        }
    };
    
    public static final int[][] LEVEL_OFFSETS = {
        {20, 20},
    };
    
    public static final int[] CELL_WIDTHS = {
        50
    };
}
