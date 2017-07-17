package Views;

import Controllers.GUIController;
import Models.Model;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;

/**
 * <p><b>GUIView</b> is a user interface for the <b>DTs&amp;ALGs</b> application</p>
 * <p> It is responsible for displaying the information through a SWING GUI</p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class GUIView extends JPanel {
    private JFrame frame;
    private JPanel cardsLayoutPanel;
    private JPanel card1Buttons;
    private JPanel card2Buttons;
    // Left Panel: Visualizer results section
    private RSyntaxTextArea visualizerResultsPanel;
    private JPanel benchMarkButtons;
    // Right Panel: results section
    private RSyntaxTextArea benchmarkResultsPanel;
    private Model model;
    private JPanel rootPanel;
    // Menu Bar
    private JMenuBar menuBar;
    private JMenuItem menuButton1;
    private JMenuItem menuButton2;
    // Left Panel
    private JPanel leftPanel;
    // Left Panel: Title
    private JPanel titleLeftPanel;
    private JLabel leftPanelLabel;
    // Left Panel: Visualizer Combo Box section
    private JPanel cardsComboBoxPanel;
    private JComboBox comboBox;
    private JPanel cardsPanel;
    private JRadioButton radioButtonLinkedList;
    private JRadioButton radioButtonStack;
    private JRadioButton radioButtonQueue;
    private JRadioButton radioButtonHashTable;
    private JRadioButton radioButtonGraph;
    private JRadioButton radioButtonLinearSearch;
    private JRadioButton radioButtonBinarySearch;
    private JRadioButton radioButtonBubbleSort;
    private JRadioButton radioButtonInsertionSort;
    private JRadioButton radioButtonSelectionSort;
    private JRadioButton radioButtonMergeSort;
    private JRadioButton radioButtonMergeSortInPlace;
    private JRadioButton radioButtonQuickSort;
    // Right Panel
    private JPanel rightPanel;
    // Left Panel: Title
    private JPanel titleRightPanel;
    private JLabel rightTopPanelLabel;
    // Right Panel: Benchmarker buttons section
    private JPanel benchMarkOptionsPanel;
    private JRadioButton radioButtonLinearSearchBenchmark;
    private JRadioButton radioButtonBinarySearchBenchmark;
    private JRadioButton radioButtonBubbleSortBenchmark;
    private JRadioButton radioButtonInsertionSortBenchmark;
    private JRadioButton radioButtonSelectionSortBenchmark;
    private JRadioButton radioButtonQuickSortBenchmark;
    private JRadioButton radioButtonMergeSortBenchmark;
    private JRadioButton radioButtonMergeInPlaceBenchmark;
    private JRadioButton radioButtonShellSort;
    private JRadioButton radioButtonShellSortBenchmark;
    private JButton runBenchmark;


    public GUIView(Model model) {
        this.model = model;
        frame = new JFrame("Data Structures and Algorithms");

        // create top menu bar
//        menuBar = new JMenuBar();
//        menuBar = new JMenuBar();
//        menuButton1 = new JMenuItem("Info");
//        menuButton1.setMnemonic(KeyEvent.VK_I);
//        menuButton1.setActionCommand("Info");
//        menuButton2 = new JMenuItem("Exit");
//        menuButton2.setMnemonic(KeyEvent.VK_X);
//        menuButton2.setHorizontalAlignment(0);
//        menuButton2.setActionCommand("Exit");
//        menuBar.add(menuButton1);
//        menuBar.add(menuButton2);
//        JMenuItem menuButton3 = new JMenuItem("Other");
//        menuButton3.setMnemonic(KeyEvent.VK_O);
//        menuButton3.setHorizontalAlignment(0);
//        menuButton3.setActionCommand("Other");
//        menuBar.add(menuButton3);

        // Create Text Area
        // Using JSyntaxPane
//        DefaultSyntaxKit.initKit();
//        visualizerResultsPanel.setEditorKit(new JavaSyntaxKit());
//        benchmarkResultsPanel.setEditorKit(new JavaSyntaxKit());
//        visualizerResultsPanel.setContentType("text/java");
//        benchmarkResultsPanel.setContentType("text/java");

        // Using RSyntaxTextArea
        visualizerResultsPanel.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        benchmarkResultsPanel.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        visualizerResultsPanel.setCodeFoldingEnabled(true);
        benchmarkResultsPanel.setCodeFoldingEnabled(true);

//        frame.setJMenuBar(menuBar);
        frame.setSize(400, 800);
        frame.add(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Adds event listeners (callback functions) to Components in the View
     * @param guiController the object taking caring of processing requests from the view,
     *                      updating the model, and updating the view
     */
    public void registerListener(GUIController guiController) {
        comboBox.addItemListener(guiController);

        // Visualizer ComboBox card1 buttons
        radioButtonLinkedList.addMouseListener(guiController);
        radioButtonStack.addMouseListener(guiController);
        radioButtonQueue.addMouseListener(guiController);
        radioButtonGraph.addMouseListener(guiController);
        radioButtonHashTable.addMouseListener(guiController);

        // Visualizer ComboBox card2 buttons
        radioButtonLinearSearch.addMouseListener(guiController);
        radioButtonBinarySearch.addMouseListener(guiController);
        radioButtonBubbleSort.addMouseListener(guiController);
        radioButtonInsertionSort.addMouseListener(guiController);
        radioButtonSelectionSort.addMouseListener(guiController);
        radioButtonShellSort.addMouseListener(guiController);
        radioButtonQuickSort.addMouseListener(guiController);
        radioButtonMergeSort.addMouseListener(guiController);
        radioButtonMergeSortInPlace.addMouseListener(guiController);

        // Benchmarker buttons
        runBenchmark.addActionListener(guiController);
        radioButtonLinearSearchBenchmark.addMouseListener(guiController);
        radioButtonBinarySearchBenchmark.addMouseListener(guiController);
        radioButtonBubbleSortBenchmark.addMouseListener(guiController);
        radioButtonInsertionSortBenchmark.addMouseListener(guiController);
        radioButtonSelectionSortBenchmark.addMouseListener(guiController);
        radioButtonShellSortBenchmark.addMouseListener(guiController);
        radioButtonQuickSortBenchmark.addMouseListener(guiController);
        radioButtonMergeSortBenchmark.addMouseListener(guiController);
        radioButtonMergeInPlaceBenchmark.addMouseListener(guiController);
    }

    /**
     * swaps the visualizer control panel between algorithms and data structures
     * @param algorithmsPanelSelected whether the algorithms panel is selected
     */
    public void selectVisualizerPanel(boolean algorithmsPanelSelected) {
        if (algorithmsPanelSelected) {
            card1Buttons.setVisible(false);
            card2Buttons.setVisible(true);
        } else {
            card1Buttons.setVisible(true);
            card2Buttons.setVisible(false);
        }
    }

    /**
     * updates the visualizer panel in this view
     * @param Results contains the model changes to update the visualizer for this view
     */
    public void updateVisualizerResults(String Results) {
        visualizerResultsPanel.setText(Results);
    }

    /**
     * updates the benchmarker panel in this view
     * @param results contains the model changes to update the benchmarker for this view
     */
    public void updateBenchmarkerResults(String results) {
        benchmarkResultsPanel.setText(results);
    }


}
