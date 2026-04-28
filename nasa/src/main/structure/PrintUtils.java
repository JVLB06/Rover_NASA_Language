package nasa.rover;
import nasa.rover.Consts;

public class PrintUtils {
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

    public static void loadPath(int[][] matrix, String commands) {
        char[] commandArray = commands.toCharArray();
        String pathState = "";
        String numSequence = "";
        char lastCommand = ' ';

        for (int i = 0; i < commandArray.length; i++) {
            char command = commandArray[i];
            if (Character.isDigit(command)) {
                numSequence += lastCommand + command;
                lastCommand = command;
            } else {
                numSequence = numSequence.isEmpty() ? "1" : numSequence;
                for (int c = 0; c < Integer.parseInt(numSequence); c++) {
                    switch (command) {
                        case 'L':
                            pathState += "L";
                            break;
                        case 'R':
                            pathState += "R";
                            break;
                        case 'F':
                            pathState += "F";
                            break;
                        default:
                            System.out.println("Invalid command: " + command);
                    }
                }
            }
        }
    }
}
