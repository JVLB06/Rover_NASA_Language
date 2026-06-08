package nasa.rover;
import nasa.rover.Consts;

public class Consts {

    //Base map matrix (0 = free, 1 = obstacle)
    public static final int[][] matrix = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    //Walk constraints derived from matrix size
    public static final int walkLimit   = matrix[0].length / 2;
    public static final int walkMinimum = 1;

    public static final String rules = "";

    //Command characters and their string equivalents
    public static final String forwardCommand  = "F";
    public static final char   forwardChar     = 'F';
    public static final String leftCommand     = "L";
    public static final char   leftChar        = 'L';
    public static final String rightCommand    = "R";
    public static final char   rightChar       = 'R';
    public static final String turnOverCommand = "T";
    public static final char   turnOverChar    = 'T';

    //Matrix cell display symbols
    public static final String matrixPath     = " . ";   // empty cell
    public static final String matrixObstacle = "[X]";   // obstacle
    public static final String matrixVisited  = " * ";   // rover trail
    public static final String matrixRover    = " @ ";   // rover fallback

    //Rover directional arrows (index matches direction int: 0=N, 1=E, 2=S, 3=W)
    public static final String[] roverArrow = {" ^ ", " > ", " v ", " < "};

    //Cardinal direction names (index matches direction int: 0=N, 1=E, 2=S, 3=W)
    public static final String[] directionNames = {"North (N)", "East (E)", "South (S)", "West (W)"};

    //Initial direction of the rover (1 = East)
    public static final int initialDirection = 1;

    // Validation error messages
    public static final String errorInvalidCommand = "  [!] Syntax error: unknown command '%s' at position %d.";
    public static final String errorInvalidParam   = "  [!] Parameter error: value '%s' out of range (min: %d, max: %d) at position %d.";
    public static final String errorMissingCommand = "  [!] Syntax error: number '%s' at position %d not followed by a command.";
    public static final String errorEmptyInput     = "  [!] Empty input. Please enter at least one command.";

    // Final result summary format
    public static final String resultSummary = "  Position : (%d, %d)   |   Facing : %s";
}
