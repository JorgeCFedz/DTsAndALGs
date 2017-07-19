package Controllers;

import Models.Model;
import Views.GUIView;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <p><b>GUIController</b> controls the <b>DTs&amp;ALGs</b> GUI interface</p>
 * <p>It is responsible for connecting the GUIView with the model by</p>
 * <ul>
 *     <li>Processing user requests through the GUIView</li>
 *     <li>Dispatching these requests to the main controller for the application</li>
 *     <li>storing template edits in the model</li>
 *     <li>updating the GUI guiView with model changes</li>
 * </ul>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class GUIController implements ActionListener, ItemListener, MouseListener {
    private Model model;
    private GUIView guiView;
    private Controller controller;

    /**
     * Initializes the GUIController with the given guiView and model
     *
     * @param model   the model where to store changes for the guiView
     * @param guiView the guiView where to display model changes
     */
    public GUIController(Model model, GUIView guiView) {                                       // add try catch and remove throws
        this.model = model;
        this.guiView = guiView;
        controller = new Controller(model);
    }

    /**
     * Updates the benchmarker result panel with the currently selected algorithms
     *
     * @param e the benchmarker run button that has recently changed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // DEBUGGING LINES
//        System.out.println("Inside actionPerformed in the controller");
//        System.out.println(model.getBenchmarkButtonsSelected().toString());

        File outputFile = processBenchmarkerResultsToFile();
        String benchmarkerPanelText = getBenchmarkerResultsFromFile(outputFile);
        model.setBenchmarkText(benchmarkerPanelText);
        guiView.updateBenchmarkerResults(model.getBenchmarkText());
    }

    /**
     * Swaps the current visualizer panel between Algorithms and data structures
     *
     * @param e the button that has changed state
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        guiView.selectVisualizerPanel(isAlgorithmComboBoxSelected(e));
    }

    /**
     * Processes the radio button changes by detecting if the source is the visualizer or benchmarker
     * If the source is the benchmarker it updates the model with the button changes
     * if the source is the visualizer it updates both the model and view with the button changes
     *
     * @param e the radio button pressed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(isBenchmarkerButton(e)) {
            // Benchmarker buttons
            JCheckBox btn = (JCheckBox) e.getSource();
            String text = btn.getText();
            model.addBenchmarkButtonSelected(text);
        } else {
            JRadioButton btn = (JRadioButton) e.getSource();
            String text = btn.getText();
            model.setVisualizerButtonSelected(text);
            String fileName = model.getFileName(text);
            if (isAlgorithm(fileName)) {
                controller.visualizeAlgorithm(model.getVisualizerButtonSelected());
            } else {
                controller.visualizeDataStructure(model.getVisualizerButtonSelected());
            }
            guiView.updateVisualizerResults(model.getVisualizerText());
        }
//        JRadioButton btn = (JRadioButton) e.getSource();
//        String text = btn.getText();
//        if (isBenchmarkerButton(btn)) {
//            // Benchmarker buttons
//            model.addBenchmarkButtonSelected(text);
//        } else {
//            // Visualizer buttons
//            model.setVisualizerButtonSelected(text);
//            String fileName = model.getFileName(text);
//            if (isAlgorithm(fileName)) {
//                controller.visualizeAlgorithm(model.getVisualizerButtonSelected());
//            } else {
//                controller.visualizeDataStructure(model.getVisualizerButtonSelected());
//            }
//            guiView.updateVisualizerResults(model.getVisualizerText());
//        }
    }

    private boolean isBenchmarkerButton(MouseEvent e) {
        return e.getComponent().getClass().equals(JCheckBox.class);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	PLACEHOLDER METHODS
    ///////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * returns whether the current selected visualizer panel is algorithms or data structures
     *
     * @param e the currently selected panel
     * @return true of the panel selected is Algorithms, false otherwise
     */
    private boolean isAlgorithmComboBoxSelected(ItemEvent e) {
        return ((JComboBox) e.getSource()).getSelectedItem().toString().equals("Algorithms");
    }

    /**
     * Process the selected algorithm requests and stores results in a file
     *
     * @return the file containing the benchmarker results
     */
    private File processBenchmarkerResultsToFile() {
        File outputFile = new File("GUIBenchmarkerOutput.txt");
        PrintStream output = null;
        try {
            output = new PrintStream(outputFile);
            System.setOut(output);
            for (String btn : model.getBenchmarkButtonsSelected()) {
                if (btn.equals("Linear Search") || btn.equals("Binary Search")) {
                    controller.search(btn);
                } else {
                    controller.sort(btn);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            System.setOut(System.out);
            if (output != null) {
                output.close();
            }
        }
        return outputFile;
    }

    /**
     * @param outputFile the file with the temp results from the benchmarker
     * @return the processed benchmarker results from the temp file
     */
    private String getBenchmarkerResultsFromFile(File outputFile) {
        List<String> lines = new ArrayList<>();
        try {
            String filePath = outputFile.getCanonicalPath();
            Path path = Paths.get(filePath);
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            outputFile.delete();
        }
        StringBuilder benchmarkPanelBuffer = new StringBuilder();
        for (String line : lines) {
            benchmarkPanelBuffer.append(line);
            benchmarkPanelBuffer.append(System.lineSeparator());
        }
        String benchmarkPanelText = benchmarkPanelBuffer.toString();
        return benchmarkPanelText;
    }

    /**
     * returns whether the given btn belongs to the visualizer or benchmarker
     *
     * @param btn the given btn
     * @return true if the given btn belongs to the benchmarker, false otherwise
     */
    private boolean isBenchmarkerButton(JRadioButton btn) {
        return btn.getHideActionText();
    }

    /**
     * returns whether the fileName is associated with an algorithm or a data structure
     *
     * @param fileName the associated file name for this data structure or algorithm
     * @return true if this fileName represents an algorithm, false otherwise
     */
    private boolean isAlgorithm(String fileName) {
        return fileName.equals("Algorithms");
    }
}
