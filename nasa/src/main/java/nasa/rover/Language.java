package nasa.rover;
import java.util.Scanner;
import nasa.rover.Consts;
import nasa.rover.Utils;

public class Language {
    // Matrix: 
    //1 = obstacle
    //0 = free space
    //2 = visited
    //3 = rover

    public int[][] matrixUsedPath = {{}};
    public String phases = "";
    public Scanner input = new Scanner(System.in);
    public String commands = "";

    public String getCommands() {
        return commands;
    }
    public void setCommands(String commands) {
        this.commands = commands;
    }
    public int[][] getMatrixUsedPath() {
        return matrixUsedPath;
    }
    public void setMatrixUsedPath(int[][] matrixUsedPath) {
        this.matrixUsedPath = matrixUsedPath;
    }
    public String getPhases() {
        return phases;
    }
    public void setPhases(String phases) {
        this.phases = phases;
    }
    public Scanner getInput() {
        return input;
    }
    public void setInput(Scanner input) {
        this.input = input;
    }
    
}
