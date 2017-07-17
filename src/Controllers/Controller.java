package Controllers;

import Models.Model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Models.ALGs.Algorithms.*;

/**
 * <p><b>Controller</b> controls the <b>DTs&amp;ALGs</b> application</p>
 * <p> It is responsible for connecting the view with the models by</p>
 * <ul>
 *     <li>Processing user requests through the Views</li>
 *     <li>storing template edits in the model</li>
 *     <li>updating view with model changes</li>
 * </ul>
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class Controller {
    private int[] bigList1;
    private int[] orderedBigList1;
    private Model model;
    private Long startTime;
    private Long endTime;
    private Long executionTime;


    /**
     * Initializes the controller for the data structures and algorithms application
     *
     * @param model the model for this MVC application to store temporary results
     */
    public Controller(Model model) {
        this.model = model;
        bigList1 = new int[]{3, 5, 2, 6, 7, 4, 5, 9, 0, 3, 1, 3, 5, 1, 8, 7};
        orderedBigList1 = Arrays.copyOf(bigList1, bigList1.length);
        Arrays.sort(orderedBigList1);
        startTime = null;
        endTime = null;
        executionTime = null;
    }

    /**
     * Process the given searching algorithm and prints a benchmark of the results
     *
     * @param searchType the search algorithm to process
     */
    public void search(String searchType) {
        System.out.println("The ordered array is: " + Arrays.toString(orderedBigList1));
        int key = (int) (Math.random() * 9);
        System.out.println("Selecting a random valid key: " + key);
        int solutionIndex = -1;
        System.out.print("Starting " + searchType + "...");
        startTime = System.nanoTime();
        switch (searchType) {
            case "Linear Search":
                solutionIndex = linearSearch(orderedBigList1, key);
                break;
            case "Binary Search":
                solutionIndex = binarySearch(orderedBigList1, key);
                break;
            default:
                break;
        }
        endTime = System.nanoTime();
        executionTime = endTime - startTime;
        System.out.println(searchType + " found the key at index: " + solutionIndex + " in " + executionTime + "\n");
    }

    /**
     * Process the given sorting algorithm and prints a benchmark of the results
     *
     * @param sortType the sorting algorithm to process
     */
    public void sort(String sortType) {
        System.out.println("The unordered array is: " + Arrays.toString(bigList1));
        int[] result = Arrays.copyOf(bigList1, bigList1.length);
        System.out.println("Starting " + sortType + "...");
        startTime = System.nanoTime();
        switch (sortType) {
            case "Bubble Sort":
                bubbleSort(result);
                break;
            case "Insertion Sort":
                insertionSort(result);
                break;
            case "Selection Sort":
                selectionSort(result);
                break;
            case "Shell Sort":
                shellSort(result);
                break;
            case "Merge Sort":
                mergeSort(result, false);
                break;
            case "Merge (in-place)":
                mergeSort(result, true);
                break;
            case "Quick Sort":
                quickSort(result);
                break;
            default:
                break;
        }
        endTime = System.nanoTime();
        executionTime = endTime - startTime;
        System.out.println(sortType + " ordered the array in " + executionTime);
        System.out.println("The ordered array is: " + Arrays.toString(result) + "\n");
    }

    /**
     * Updates the visualizer results panel with the code for the given data structure
     *
     * @param algorithm the name of the algorithm to inspect
     */
    public void visualizeAlgorithm(String algorithm) {
        visualizeObject("\\src\\Models\\ALGs\\", algorithm);
    }

    /**
     * Updates the visualizer results panel with the code for the given data structure
     *
     * @param dataStructure the name of the data structure to inspect
     */
    public void visualizeDataStructure(String dataStructure) {
        visualizeObject("\\src\\Models\\DTs\\", dataStructure);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Updates the visualizer results panel with the code for the given object
     *
     * @param objectPath the relative path of the object with respect to the current directory
     * @param objectName the name of the object to inspect
     */
    private void visualizeObject(String objectPath, String objectName) {
        String dirPath = getCurrDirPath() + objectPath;
        String filePath = getFilePath(dirPath, objectName);
//        String filePath = getFilePath("files\\", objectName);                                                         // only used for jar distributions

        String visualizerPanelText = inspectFile(filePath);
        model.setVisualizerText(visualizerPanelText);
    }

    /**
     * returns the directory path for the current project
     *
     * @return the current path for the project directory
     */
    private String getCurrDirPath() {
        File temp = new File("tempFile.txt");

        String tempFilePath = null;
        try {
            tempFilePath = temp.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            temp.delete();
        }
        String dirPath = tempFilePath.substring(0, tempFilePath.lastIndexOf(File.separator));
        System.out.println(dirPath);
        return dirPath;
    }

    /**
     * returns the curent path for the given data structure or algorithm
     *
     * @param dirPath the directory path for the current project
     * @param name    the name of the data structure or algorithm
     * @return the complete file path for the given data structure or algorithm
     */
    private String getFilePath(String dirPath, String name) {
        String fileName = model.getFileName(name);
        String filePath = dirPath + fileName + ".java";
        return filePath;
    }

    /**
     * returns the contents of the chosen file
     *
     * @param filePath the path for the file to retrieve the contents from
     * @return the contents of the file with the given filePath path
     */
    private String inspectFile(String filePath) {
        Path path = Paths.get(filePath);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder resultBuffer = new StringBuilder();
        for (String line : lines) {
            resultBuffer.append(line);
            resultBuffer.append(System.lineSeparator());
        }
        String result = resultBuffer.toString();
        return result;
    }
}
