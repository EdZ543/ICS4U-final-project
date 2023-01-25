/**
 * Arrays of all levels in the game
 * 
 * @author Eddie Zhuang
 * @version Jan. 24, 2023
 */
public class Levels  
{
    // Level arrays
    public static final String[][] LEVELS = {
        {
            "              ",
            "              ",
            "              ",
            "        P     ",
            "       ^      ",
            "     >>^  A   ",
            "   A ^##      ",
            "      ##      ",
            "    #######   ",
            "  ##########  ",
            "              ",
            "SSSSSSSSSSSSSS",
        },
        {
            "   P  ",
            "      ",
            "#    #",
            "A  #A#",
            "      ",
            " #>>  ",
            " #####",
        },
        {
            "          ",
            "          ",
            "   >>     ",
            "  ###     ",
            " #####  P ",
            " AAAAAA   ",
            "     #####",
            "          ",
            "          ",
            "          ",
        },
        {
            "    ############## ",
            "     ############# ",
            "     ###           ",
            "     ##            ",
            "     ##    #       ",
            "   P #             ",
            "   ###      #   << ",
            " #   # #    #  ##^ ",
            "#              ##^ ",
            "  ##   #           ",
            "                   ",
            "                   ",
        },
        {  
            "              ",
            "       P      ",
            "   >>         ",
            "   ^A    A    ",
            "     S        ",
            "     S#####   ",
            "   ########   ",
            "   ########   ",
            "              ",
        },
        {   "               ",
            "      >>> P    ",
            "      ^#       ",
            "               ",
            "    #S   S#    ",
            "     S         ",
            "     SS#S      ",
            "       A       ",
            "         #     ",
            "        ##     ",
            "             ",
        },
        {
            "                  ",
            "  ##### P         ",
            "      #           ",
            "    A #   v#      ",
            "        <<<#      ",
            "     #### ##      ",
            "       ## #       ",
            "      S## A       ",
            "    S  ## ##      ",
            "      ### ##      ",
            "        # ##      ",
            "        ####      ",
            "                  ",
        },
        {
            "    ####    ",
            "   ##  #####",
            "   ## A A ##",
            "    #A#A A##",
            " P >> A  ###",
            "   ^### A###",
            "   ^<<##### ",
            "     ^  ##  ",
            "     ^      ",
            "            ",
            "            ",
            "            ",
        },
        {
            "              ",
            "     #        ",
            "     S        ",
            "    v >       ",
            "    >>^       ",
            "     #        ",
            "          S   ",
            "            P ",
            "  S#S  SSS#   ",
            "         ##   ",
            "              ",
            "  A   S       ",
            "     #SA      ",
            "     ###      ",
            "     ###      ",
        },
        {
            "       P       ",
            "       S       ",
            "     A # A     ",
            "               ",
            "               ",
            "      >>       ",
            "   SSS^#  SS   ",
            "   ###^A SS#   ",
            "   ## ^<  ##   ",
            "   ##     ##   ",
        }
    };
    
    // The size that the cells should be for each level
    public static final int[] CELL_WIDTHS = {
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
    };
    public static final String[] BG_IMAGES = {
        "bg0.png",
        "bg0.png",
        "bg0.png",
        "bg1.png",
        "bg1.png",
        "bg1.png",
        "bg1.png",
        "bg2.png",
        "bg2.png",
        "bg2.png"
    };
}
