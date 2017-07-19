package Models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p><b>Model</b> stores changes to the <b>DTs&amp;ALGs</b> Views template</p>
 * <p>The Model contains all the data required to recreate the state of the View</p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class Model {
    private static Map<String, String> fileNames;
    private Set<String> benchmarkButtonsSelected;
    private String visualizerButtonSelected;
    private String visualizerText;
    private String benchmarkText;

    public Model() {
        benchmarkButtonsSelected = new TreeSet();
        visualizerButtonSelected = "";
        visualizerText = "";
        benchmarkText = "";
        initFileNames();
    }

    /**
     * returns the associated file name in this project for the given data structure or algorithm
     * @param name the data structure or algorithm requests
     * @return the associated file name
     */
    public String getFileName(String name) {
        return fileNames.get(name);
    }

    /**
     * adds the most recently selected benchmarker button
     * @param btnText the text associated with the benchmarker button selected
     */
    public void addBenchmarkButtonSelected(String btnText) {
        if (!benchmarkButtonsSelected.contains(btnText)) {
            benchmarkButtonsSelected.add(btnText);
        } else {
            benchmarkButtonsSelected.remove(btnText);
        }
    }

    /**
     * returns all the benchmarker buttons currently selected
     * @return the benchmarker buttons selected
     */
    public Set<String> getBenchmarkButtonsSelected() {
        return new TreeSet<>(benchmarkButtonsSelected);
    }

    /**
     * returns the text contained in the benchmarker results panel
     * @return the text contained in the benchmarker results panel
     */
    public String getBenchmarkText() {
        return benchmarkText;
    }

    /**
     * stores the text contained in the benchmarker results panel
     * @param text the text contained in the benchmarker results panel
     */
    public void setBenchmarkText(String text) {
        this.benchmarkText = text;
    }

    /**
     * returns the visualizer button currently selected
     * @return the visualizer button selected
     */
    public String getVisualizerButtonSelected() {
        return visualizerButtonSelected;
    }

    /**
     * adds the most recently selected benchmarker button
     * @param btnText the text associated with the benchmarker button selected
     */
    public void setVisualizerButtonSelected(String btnText) {
        visualizerButtonSelected = btnText;
    }

    /**
     * returns the text contained in the visualizer results panel
     * @return the text contained in the visualizer results panel
     */
    public String getVisualizerText() {
        return visualizerText;
    }

    /**
     * stores the text contained in the visualizer results panel
     * @param text the text contained in the visualizer results panel
     */
    public void setVisualizerText(String text) {
        this.visualizerText = text;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Initializes the structure containing the mapping between the
     * algorithms and data structures names and the file names in this
     * project
     */
    private static void initFileNames() {
        if (fileNames == null) {
            fileNames = new HashMap<>();
        }
        fileNames.put("Linked List (simple)", "MySimpleLinkedList");
        fileNames.put("Linked List", "MyLinkedList");
        fileNames.put("Stack", "MyStack");
        fileNames.put("Queue", "MyArrayDeque");
        fileNames.put("Graph", "MyGraph");
        fileNames.put("HashTable", "MyHashTable");
        fileNames.put("Linear Search", "Algorithms");
        fileNames.put("Binary Search", "Algorithms");
        fileNames.put("Bubble Sort", "Algorithms");
        fileNames.put("Insertion Sort", "Algorithms");
        fileNames.put("Selection Sort", "Algorithms");
        fileNames.put("Shell Sort", "Algorithms");
        fileNames.put("Merge Sort", "Algorithms");
        fileNames.put("Merge Sort (in-place)", "Algorithms");
        fileNames.put("Quick Sort", "Algorithms");
    }
}
