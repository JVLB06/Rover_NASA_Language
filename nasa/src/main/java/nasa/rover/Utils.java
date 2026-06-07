package nasa.rover;
import nasa.rover.Consts;

public class Utils {
    //Print base Matrix
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print(Consts.matrixObstacle + " ");
                } else {
                    System.out.print(Consts.matrixPath + " ");
                }
            }
            System.out.println();
        }
    }

    //Print end Matrix
    public static void printEndMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print(Consts.matrixObstacle + " ");
                } else if (matrix[i][j] == 2) {
                    System.out.print(Consts.matrixVisited + " ");
                } else if (matrix[i][j] == 3) {
                    System.out.print(Consts.matrixRover + " ");
                } else {
                    System.out.print(Consts.matrixPath + " ");
                }
            }
            System.out.println();
        }
    }

    //Function to validate the syntax and parameters of the command string
    //Returns true if valid, false if any error is found (errors are printed to stdout)
    public static boolean validateCommands(String commands) {

        // Empty input check
        if (commands == null || commands.trim().isEmpty()) {
            System.out.println(Consts.errorEmptyInput);
            return false;
        }

        char[] commandArray = commands.toCharArray();
        boolean hasError = false;
        StringBuilder numSequence = new StringBuilder();

        for (int i = 0; i < commandArray.length; i++) {
            char c = commandArray[i];

            if (Character.isDigit(c)) {
                // Accumulate digits
                numSequence.append(c);

            } else {
                // Check if it is a known command
                boolean isKnown = (c == Consts.forwardChar
                        || c == Consts.leftChar
                        || c == Consts.rightChar
                        || c == Consts.turnOverChar);

                if (!isKnown) {
                    // Unknown command character
                    System.out.println(String.format(Consts.errorInvalidCommand, c, i));
                    hasError = true;
                    numSequence.setLength(0);
                    continue;
                }

                // Validate numeric parameter if present
                if (numSequence.length() > 0) {
                    int value = Integer.parseInt(numSequence.toString());
                    int paramStart = i - numSequence.length();

                    if (value < Consts.walkMinimum || value > Consts.walkLimit) {
                        System.out.println(String.format(
                                Consts.errorInvalidParam,
                                numSequence.toString(), Consts.walkMinimum, Consts.walkLimit, paramStart));
                        hasError = true;
                    }
                    numSequence.setLength(0);
                }
            }
        }

        // Trailing digits with no command after them
        if (numSequence.length() > 0) {
            int paramStart = commandArray.length - numSequence.length();
            System.out.println(String.format(
                    Consts.errorMissingCommand, numSequence.toString(), paramStart));
            hasError = true;
        }

        return !hasError;
    }

    //Function to read the command and convert to a iterable line of commands
    public static String loadPath(int[][] matrix, String commands) {

        char[] commandArray = commands.toCharArray();

        StringBuilder pathState = new StringBuilder();
        StringBuilder numSequence = new StringBuilder();

        for (int i = 0; i < commandArray.length; i++) {

            char command = commandArray[i];

            // Accumulate digits
            if (Character.isDigit(command)) {

                numSequence.append(command);

            } else {

                // If no number before command, default = 1
                int multiplier = numSequence.length() == 0
                        ? 1
                        : Integer.parseInt(numSequence.toString());

                // Repeat command
                for (int c = 0; c < multiplier; c++) {

                    switch (command) {

                        case Consts.leftChar:
                            pathState.append(Consts.leftCommand);
                            break;

                        case Consts.rightChar:
                            pathState.append(Consts.rightCommand);
                            break;

                        case Consts.forwardChar:
                            pathState.append(Consts.forwardCommand);
                            break;

                        case Consts.turnOverChar:
                            pathState.append(Consts.turnOverCommand);
                            break;

                        default:
                            System.out.println("Invalid command: " + command);
                    }
                }

                // Reset number accumulator
                numSequence.setLength(0);
            }
        }

        return pathState.toString();
    }

    //Function to mark the path on the matrix based on the commands
    public static int[][] markPath(int[][] matrix, String path) {

        int direction = Consts.initialDirection;
        int lastDirection = Consts.initialDirection;
        int countDirection = 0;

        // Current rover position (starts at top-left corner)
        int currentX = 0;
        int currentY = 0;

        //Generate base Matrix
        int[][] markedMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                markedMatrix[i][j] = matrix[i][j];
            }
        }

        // Mark starting position as visited
        markedMatrix[currentX][currentY] = 2;

        //Define directions based on the command
        for (int i = 0; i < path.length(); i++) {
            char command = path.charAt(i);
            switch (command) {
                case Consts.leftChar:
                    direction = (direction - 1 + 4) % 4;
                    countDirection = 0;
                    break;
                case Consts.rightChar:
                    direction = (direction + 1) % 4;
                    countDirection = 0;
                    break;
                case Consts.forwardChar:
                    // no direction change
                    break;
                case Consts.turnOverChar:
                    direction = (direction + 2) % 4;
                    countDirection = 0;
                    break;
                default:
                    System.out.println("Invalid command in path: " + command);
            }

            //Move forward in the corresponding direction
            if (command == Consts.forwardChar) {
                int dx = 0, dy = 0;
                switch (direction) {
                    case 0: // Up
                        dx = -1;
                        break;
                    case 1: // Right
                        dy = 1;
                        break;
                    case 2: // Down
                        dx = 1;
                        break;
                    case 3: // Left
                        dy = -1;
                        break;
                }

                // Count consecutive steps in the same direction
                if (direction == lastDirection) {
                    countDirection++;
                } else {
                    countDirection = 1;
                }
                lastDirection = direction;

                // Validate walk limit
                if (countDirection > Consts.walkLimit) {
                    System.out.println("You walked too much in the same direction. Stopping.");
                    break;
                }

                int newX = currentX + dx;
                int newY = currentY + dy;

                // Validate bounds
                if (newX < 0 || newX >= markedMatrix.length || newY < 0 || newY >= markedMatrix[0].length) {
                    System.out.println("Out of bounds at (" + newX + ", " + newY + "). Stopping.");
                    break;
                }

                // Validate obstacle
                if (markedMatrix[newX][newY] == 1) {
                    System.out.println("Encountered an obstacle at (" + newX + ", " + newY + "). Stopping.");
                    break;
                }

                // Mark current cell as visited before moving
                markedMatrix[currentX][currentY] = 2;

                // Update rover position
                currentX = newX;
                currentY = newY;

                // Mark new position as rover
                markedMatrix[currentX][currentY] = 3;
            }
        }

        // Ensure final position is marked as rover
        markedMatrix[currentX][currentY] = 3;

        return markedMatrix;
    }
    //Function to calculate the final direction (int) after executing the full path
    //Returns: 0=North, 1=East, 2=South, 3=West
    public static int getFinalDirection(String path) {
        int direction = Consts.initialDirection;
        for (int i = 0; i < path.length(); i++) {
            char command = path.charAt(i);
            switch (command) {
                case Consts.leftChar:
                    direction = (direction - 1 + 4) % 4;
                    break;
                case Consts.rightChar:
                    direction = (direction + 1) % 4;
                    break;
                case Consts.turnOverChar:
                    direction = (direction + 2) % 4;
                    break;
                default:
                    // forwardChar and others do not change direction
                    break;
            }
        }
        return direction;
    }

    //Function to find the final rover position (cell marked as 3) in the result matrix
    //Returns int[]{x, y} or int[]{-1, -1} if not found
    public static int[] getFinalPosition(int[][] markedMatrix) {
        for (int i = 0; i < markedMatrix.length; i++) {
            for (int j = 0; j < markedMatrix[i].length; j++) {
                if (markedMatrix[i][j] == 3) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

}
