package nasa.rover;
import java.util.Scanner;
import nasa.rover.Consts;

public class Language {
    // Matrix: 
    //1 = obstacle
    //0 = free space

    public int[][] matrixUsedPath = {{}};
    public String phases = "";
    public Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        Language language = new Language();
        System.out.println(Consts.openPhrase);
        System.out.println("Hi rover. This is your board:");
        PrintUtils.printMatrix(Consts.matrix);

        System.out.println("You can move " + Consts.walkLimit + " steps at a time, but you must move at least " + Consts.walkMinimum + " step.");
        System.out.println("You can move in 3 directions: left, right and turn over, and walk straight forward.");

        phases = input.nextLine();
    }
}
