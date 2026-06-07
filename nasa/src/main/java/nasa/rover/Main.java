package nasa.rover;
import nasa.rover.Consts;
import nasa.rover.Utils;
import nasa.rover.Language;


public class Main {

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
      
        System.out.println( Consts.openPhrase);
                        
        System.out.println();
       
        Utils.printMatrix(Consts.matrix);
        
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("You can move " + Consts.walkLimit + " steps at a time, but you must move at least " + Consts.walkMinimum + " step.");
        
        System.out.println();
        System.out.println();
        
        System.out.println("You can move in 3 directions: left, right and turn over, and walk straight forward.");
        
        System.out.println();
        System.out.println();
        
        System.out.println("Please enter your command: one number folowed by one letter command:\n");
        
        System.out.println();
        
        System.out.println(Consts.forwardCommand + " for forward;\n" + Consts.leftCommand + " for left;\n" + Consts.rightCommand + " for right;\n" + Consts.turnOverCommand + " for turn over.");

        

        System.out.println();
        Utils.printMatrix(Consts.matrix);

        //Receive and execute commands
        language.setPhases(language.getInput().nextLine());

        // Validate syntax and parameters before executing
        if (!Utils.validateCommands(language.getPhases())) {
            System.out.println();
            System.out.println("Execution aborted due to errors above. Please fix your command and try again.");
            return;
        }

        language.setCommands(Utils.loadPath(Consts.matrix, language.getPhases()));
        language.setMatrixUsedPath(Utils.markPath(Consts.matrix, language.getCommands()));
        
        System.out.println();
        System.out.println();
        
        System.out.println("Your command is: " + language.getCommands());
        
        System.out.println();
        
        //Present last result
        Utils.printEndMatrix(language.getMatrixUsedPath());

        // Display final rover state: position and direction
        int finalDir = Utils.getFinalDirection(language.getCommands());
        int[] finalPos = Utils.getFinalPosition(language.getMatrixUsedPath());
        System.out.println();
        System.out.println(String.format(Consts.resultSummary, finalPos[0], finalPos[1], Consts.directionNames[finalDir]));
    }
}
