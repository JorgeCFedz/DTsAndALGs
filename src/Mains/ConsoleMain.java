package Mains;

import Controllers.Controller;
import Models.Model;
import Views.ConsoleView;

/**
 * <p><b>Mains.ConsoleMain</b> starts the <b>DTs&amp;ALGs</b> application using a <b>Console Views</b></p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class ConsoleMain {

    /**
     * starts the application
     */
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        ConsoleView consoleView = new ConsoleView(model, controller);

        consoleView.run();
    }
}
