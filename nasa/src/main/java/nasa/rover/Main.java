package nasa.rover;
import nasa.rover.Consts;
import nasa.rover.Utils;
import nasa.rover.Language;

public class Main {

    //Run structure
    public static void main(String[] args) {
        Language language = new Language();

        //Presenting
        System.out.println(Consts.openPhrase);
        System.out.println("Hi rover. This is your board:");
        Utils.printMatrix(Consts.matrix);

        System.out.println("You can move " + Consts.walkLimit + " steps at a time, but you must move at least " + Consts.walkMinimum + " step.");
        System.out.println("You can move in 3 directions: left, right and turn over, and walk straight forward.");

        Utils.printMatrix(Consts.matrix);

        //Receive and execute commands
        language.setPhases(language.getInput().nextLine());
        language.setCommands(Utils.loadPath(Consts.matrix, language.getPhases()));
        language.setMatrixUsedPath(Utils.markPath(Consts.matrix, language.getCommands()));

        //Present last result
        Utils.printEndMatrix(language.getMatrixUsedPath());
    }
}