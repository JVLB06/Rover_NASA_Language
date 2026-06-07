package nasa.rover;
import nasa.rover.Consts;
import nasa.rover.Utils;
import nasa.rover.Language;
import java.util.List;
import java.util.ArrayList;


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

        int terminalWidth = 170;
        int artWidth = 80;
        int padding = (terminalWidth - artWidth) / 2;
        String pad = " ".repeat(padding);

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
              pad + "       \\_/\n"
            + pad + "      (* *)\n"
            + pad + "      _)#(__\n"
            + pad + "    ( )...( )(_)\n"
            + pad + "    || |_|  ||//\n"
            + pad + ">==()  | |  ()/\n"
            + pad + "     _(___) \n"
            + pad + "    [-]   [-] \n"
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
        System.out.println("  Type commands and press Enter after each line. Type ';' to finish.");
        System.out.println();

        //Receive commands line by line until ';' is entered or a collision occurs
        StringBuilder allPhases   = new StringBuilder();
        StringBuilder allExpanded = new StringBuilder();

        // Rover state carried across command lines
        int currentX   = 0;
        int currentY   = 0;
        int currentDir = Consts.initialDirection;
        int[][] currentMatrix = Consts.matrix;
        boolean stopped = false;

        // Full trail accumulated across all command lines
        List<int[]> fullTrail = new ArrayList<>();
        fullTrail.add(new int[]{0, 0});

        while (!stopped) {
            System.out.print("  >> Enter command sequence: ");
            String line = language.getInput().nextLine().trim();

            // Check for terminator: ";" alone or appended to a command (e.g. "3F;")
            boolean endsWithSemicolon = line.endsWith(";");
            if (line.equals(";")) {
                break;
            }
            if (endsWithSemicolon) {
                line = line.substring(0, line.length() - 1).trim();
                stopped = true;
            }

            // Validate syntax and parameters before executing
            System.out.println();
            if (!Utils.validateCommands(line)) {
                System.out.println();
                System.out.println("  Execution aborted. Fix the errors above and try again.");
                System.out.println();
                return;
            }

            // Expand this line's commands
            String expanded = Utils.loadPath(Consts.matrix, line);

            // Run executePath continuing from rover position and direction; count resets per line
            Utils.PathResult pr = Utils.executePath(currentMatrix, expanded, currentX, currentY, currentDir, 0);
            int[]   posAfter = Utils.getFinalPosition(pr.matrix);
            int     dirAfter = Utils.getFinalDirection(expanded, currentDir);

            // Accumulate commands
            allPhases.append(line);
            allExpanded.append(expanded);
            language.setCommands(allExpanded.toString());
            language.setMatrixUsedPath(pr.matrix);

            // Show updated map after each line
            Utils.printEndMatrix(pr.matrix, dirAfter);
            System.out.println();

            // Trail for this command: full pr.trail includes the starting position
            List<int[]> cmdTrail = pr.trail;

            // Show position, direction and trail of this command only
            System.out.println(LINE_THIN);
            System.out.printf ("  Command : %-51s%n", line);
            System.out.printf (Consts.resultSummary + "%n", posAfter[0], posAfter[1], Consts.directionNames[dirAfter]);
            System.out.printf ("  Trail   : %s%n", Utils.formatTrail(cmdTrail));
            System.out.println(LINE_THIN);
            System.out.println();

            // Append new positions to the full trail (skip index 0 = start, already recorded)
            List<int[]> newSteps = cmdTrail.size() > 1 ? cmdTrail.subList(1, cmdTrail.size()) : new ArrayList<>();
            fullTrail.addAll(newSteps);

            // Stop only if rover had forward commands but still did not move (collision or wall)
            boolean hadForward = expanded.contains(Consts.forwardCommand);
            if (hadForward && posAfter[0] == currentX && posAfter[1] == currentY) {
                stopped = true;
            } else {
                // Update state for next iteration
                currentX      = posAfter[0];
                currentY      = posAfter[1];
                currentDir    = dirAfter;
                currentMatrix = pr.matrix;
            }
        }

        language.setPhases(allPhases.toString());
        language.setCommands(allExpanded.toString());

        int finalDir = Utils.getFinalDirection(language.getCommands());
        int[] finalPos = Utils.getFinalPosition(language.getMatrixUsedPath());

        System.out.println();

        //Present last result
        box("MISSION RESULT");
        System.out.println();
        Utils.printEndMatrix(language.getMatrixUsedPath(), finalDir);
        System.out.println();

        // Display final rover state: position, direction and complete trail
        System.out.println(LINE_THICK);
        System.out.printf ("║  %-60s║%n", "SUMMARY");
        System.out.println(LINE_THIN);
        System.out.printf ("║  Commands entered  : %-39s║%n", language.getPhases());
        System.out.printf ("║  Commands expanded : %-39s║%n", language.getCommands());
        System.out.printf ("║" + Consts.resultSummary + "                    ║%n",
            finalPos[0], finalPos[1], Consts.directionNames[finalDir]);
        System.out.println(LINE_CLOSE);
        System.out.println();
        // Full trail printed outside the box so long paths wrap naturally
        System.out.println("  Full trail :");
        System.out.println("  " + Utils.formatTrail(fullTrail));
        System.out.println();
    }
}
