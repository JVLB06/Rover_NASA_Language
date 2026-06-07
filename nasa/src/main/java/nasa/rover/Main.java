package nasa.rover;
import nasa.rover.Consts;
import nasa.rover.Utils;
import nasa.rover.Language;


public class Main {

    //Section box helper
    private static final String LINE_THICK =
        "╔══════════════════════════════════════════════════════════════╗";
    private static final String LINE_THIN  =
        "╠══════════════════════════════════════════════════════════════╣";
    private static final String LINE_CLOSE =
        "╚══════════════════════════════════════════════════════════════╝";

    //Print a titled box header
    private static void box(String title) {
        System.out.println();
        System.out.println(LINE_THICK);
        System.out.printf ("║  %-60s║%n", title);
        System.out.println(LINE_CLOSE);
    }

    //Run structure
    public static void main(String[] args) {
        Language language = new Language();

        //Presenting
        System.out.println(""
            + " ________   ________  ________  ________          ________  ________  ___      ___ _______   ________     \n"
            + "|\\   ___  \\|\\   __  \\|\\   ____\\|\\   __  \\        |\\   __  \\|\\   __  \\|\\  \\    /  /|\\  ___ \\ |\\   __  \\    \n"
            + "\\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\___|\\ \\  \\|\\  \\       \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\  /  / | \\   __/|\\ \\  \\|\\  \\   \n"
            + " \\ \\  \\\\ \\  \\ \\   __  \\ \\_____  \\ \\   __  \\       \\ \\   _  _\\ \\  \\\\\\  \\ \\  \\/  / / \\ \\  \\_|/_\\ \\   _  _\\  \n"
            + "  \\ \\  \\\\ \\  \\ \\  \\ \\  \\|____|\\  \\ \\  \\ \\  \\       \\ \\  \\\\  \\\\ \\  \\\\\\  \\ \\    / /   \\ \\  \\_|\\ \\ \\  \\\\  \\| \n"
            + "   \\ \\__\\\\ \\__\\ \\__\\ \\__\\____\\_\\  \\ \\__\\ \\__\\       \\ \\__\\\\ _\\\\ \\_______\\ \\__/ /     \\ \\_______\\ \\__\\\\ _\\ \n"
            + "    \\|__| \\|__|\\|__|\\|__|\\_________\\|__|\\|__|        \\|__|\\|__|\\|_______|\\|__|/       \\|_______|\\|__|\\|__|\n"
            + "                        \\|_________|"
            + ""
            + "");

        System.out.println();
        System.out.println();
        System.out.println();

        //Rover art
        System.out.println(
              "       \\_/\n"
            + "      (* *)\n"
            + "      _)#(__\n"
            + "    ( )...( )(_)\n"
            + "    || |_|  ||//\n"
            + ">==()  | |  ()/\n"
            + "     _(___) \n"
            + "    [-]   [-] \n"
        );

        //Show initial map
        box("MARS SURFACE MAP");
        System.out.println();
        Utils.printMatrix(Consts.matrix);
        System.out.println();

        //Legend
        System.out.println(LINE_THICK);
        System.out.printf ("║  %-60s║%n", "LEGEND");
        System.out.println(LINE_THIN);
        System.out.printf ("║   .  = open terrain     [X] = obstacle                      ║%n");
        System.out.printf ("║   *  = rover trail        >  = rover (facing direction)      ║%n");
        System.out.println(LINE_CLOSE);
        System.out.println();

        //Command reference
        System.out.println(LINE_THICK);
        System.out.printf ("║  %-60s║%n", "COMMAND REFERENCE");
        System.out.println(LINE_THIN);
        System.out.printf ("║   F  — move forward  (prefix with number: 3F = 3 steps)     ║%n");
        System.out.printf ("║   L  — rotate 90 deg left                                   ║%n");
        System.out.printf ("║   R  — rotate 90 deg right                                  ║%n");
        System.out.printf ("║   T  — turn 180 deg (turn over)                             ║%n");
        System.out.println(LINE_THIN);
        System.out.printf ("║   Step range : %d to %d per command                           ║%n",
            Consts.walkMinimum, Consts.walkLimit);
        System.out.printf ("║   Rover starts at (0,0) facing East  >                      ║%n");
        System.out.println(LINE_CLOSE);
        System.out.println();

        //Map reminder and input prompt
        System.out.println("  Map reminder:");
        System.out.println();
        Utils.printMatrix(Consts.matrix);
        System.out.println();
        System.out.print("  >> Enter command sequence: ");

        //Receive and execute commands
        language.setPhases(language.getInput().nextLine());

        // Validate syntax and parameters before executing
        System.out.println();
        if (!Utils.validateCommands(language.getPhases())) {
            System.out.println();
            System.out.println("  Execution aborted. Fix the errors above and try again.");
            System.out.println();
            return;
        }

        language.setCommands(Utils.loadPath(Consts.matrix, language.getPhases()));
        int finalDir = Utils.getFinalDirection(language.getCommands());
        language.setMatrixUsedPath(Utils.markPath(Consts.matrix, language.getCommands()));
        int[] finalPos = Utils.getFinalPosition(language.getMatrixUsedPath());

        System.out.println();
        System.out.println();

        //Present last result
        box("MISSION RESULT");
        System.out.println();
        Utils.printEndMatrix(language.getMatrixUsedPath(), finalDir);
        System.out.println();

        // Display final rover state: position and direction
        System.out.println(LINE_THICK);
        System.out.printf ("║  %-60s║%n", "SUMMARY");
        System.out.println(LINE_THIN);
        System.out.printf ("║  Commands entered  : %-39s║%n", language.getPhases());
        System.out.printf ("║  Commands expanded : %-39s║%n", language.getCommands());
        System.out.printf ("║" + Consts.resultSummary + "                    ║%n",
            finalPos[0], finalPos[1], Consts.directionNames[finalDir]);
        System.out.println(LINE_CLOSE);
        System.out.println();
    }
}
