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

        for (int i = 0; i < commandArray.length; i++) {
            char command = commandArray[i];
            // Process the command and update the matrix accordingly
            // This is a placeholder for the actual logic to move the rover
        }
    }
}
