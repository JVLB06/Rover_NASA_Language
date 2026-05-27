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

        int direction = 0;
        int lastDirection = 0;
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
}
