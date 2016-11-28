package nz.ac.aut.prog2.cluedo;

import nz.ac.aut.prog2.cluedo.model.Cluedo;
import nz.ac.aut.prog2.cluedo.ui.CluedoUI;

/**
 * The class called when the game is started. Sets up GUI and game components.
 *
 * @author Warrick Wills 13831575
 * @version 1 2014.10: Created
 * @version 2 2014.10: Re-factored main, added functionality for instructions display.
 */
public class Main {

    private static boolean firstRun = true;

    public static void main(String[] args) {
        //start new game
        Cluedo newGame = new Cluedo();
        final CluedoUI ui = new CluedoUI(newGame);

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ui.setVisible(true);
            }
        });
        // Show instructions if first run:
        if (firstRun) {
            CluedoUI.showInstructions();
        }

        /**
         * Game has run once, change firstRun so it doesn't prompt for name or
         * show instructions again
         */
        firstRun = false;
    }
}
