package Mains;

import Controllers.GUIController;
import Models.Model;
import Views.GUIView;

/**
 * <p><b>Mains.GuiMain</b> starts the <b>DTs&amp;ALGs</b> application using a <b>GUI Views</b></p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class GuiMain {

    /**
     * starts the application
     */
    public static void main(String[] args) {
        Model model = new Model();
        GUIView guiView = new GUIView(model);
        GUIController guiController = new GUIController(model, guiView);

        // register controller as the listener
        guiView.registerListener(guiController);
    }
}

