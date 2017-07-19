package Views;

import Controllers.Controller;
import Models.Model;

import java.util.Scanner;

/**
 * <p><b>ConsoleView</b> is a user interface for the <b>DTs&amp;ALGs</b> application</p>
 * <p> It is responsible for displaying the information through the java console</p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class ConsoleView {
    private Scanner consoleScanner;
    private Model model;
    private Controller controller;

    public ConsoleView(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
        consoleScanner = null;
    }

    /**
     * Starts a session using the console UI
     */
    public void run() {
        System.out.println("Hello! This is the Data Structures and Algorithms visualizer");
        System.out.println("This program lets you run benchmarks on different algorithms or " +
                "visualize the code for different data structures!");
        String option = "";
        consoleScanner = new Scanner(System.in);
        while (!option.equals("0")) {
            System.out.println("General menu options:");
            System.out.println("0. Type 0 to terminate this program");
            System.out.println("1. Type 1 to launch the benchmarker");
            System.out.println("2. Type 2 to launch the visualizer");
            option = consoleScanner.next();
            switch (option) {
                case "0":
                    break;
                case "1":
                    System.out.println("Launching the algorithms benchmark utility...");
                    runAlgorithms();
                    break;
                case "2":
                    System.out.println("Launching the visualizer...");
                    runVisualizer();
                    break;
                default:
                    System.out.println("The option selected is invalid");
                    break;
            }
        }
        consoleScanner.close();
    }

    /**
     * Console UI for running different algorithms
     */
    public void runAlgorithms() {
        String option = "";
        while (!option.equals("0")) {
            System.out.println("Welcome to the algorithms benchmark!");
            System.out.println("This utility lets you run benchmarks on different algorithms");
            System.out.println("Benchmark options:");
            System.out.println("0. Type 0 to return to the main menu");
            System.out.println("1. Type 1 to run a search algorithm of a key value");
            System.out.println("2. Type 2 to run a sorting algorithm on a list of unordered values");
            option = consoleScanner.next();
            switch (option) {
                case "0":
                    break;
                case "1":
                    System.out.println("Selected search algorithm...");
                    runSearchAlgorithm();
                    break;
                case "2":
                    System.out.println("Selected sorting algorithm...");
                    runSortAlgorithm();
                    break;
                default:
                    System.out.println("The option selected is invalid");
                    break;
            }
        }
    }

    /**
     * Console UI for running different searching algorithms
     */
    private void runSearchAlgorithm() {
        String searchAlgorithm = "";
        while (!searchAlgorithm.equals("0")) {

            System.out.println("search options:");
            System.out.println("0. Type 0 to return to the algorithms benchmark");
            System.out.println("1. Type 1 to use linear search");
            System.out.println("2. Type 2 to use binary search");

            searchAlgorithm = consoleScanner.next();
            switch (searchAlgorithm) {
                case "0":
                    break;
                case "1":
                    System.out.print("Selected search algorithm...");
                    controller.search("Linear Search");
                    break;
                case "2":
                    System.out.print("Selected search algorithm...");
                    controller.search("Binary Search");
                    break;
                default:
                    System.out.println("The option selected is invalid");
                    break;
            }
        }
    }

    /**
     * Console UI for running different sorting algorithms
     */
    private void runSortAlgorithm() {
        String sortAlgorithm = "";
        while (!sortAlgorithm.equals("0")) {
            System.out.println("sorting options:");
            System.out.println("0. Type 0 to return to the algorithms benchmark");
            System.out.println("1. Type 1 to use bubble sort");
            System.out.println("2. Type 2 to use insertion sort");
            System.out.println("3. Type 3 to use selection sort");
            System.out.println("4. Type 4 to use shell sort");
            System.out.println("5. Type 5 to use merge sort");
            System.out.println("6. Type 6 to use in-place merge sort");
            System.out.println("7. Type 7 to use quick sort");

            sortAlgorithm = consoleScanner.next();
            switch (sortAlgorithm) {
                case "0":
                    break;
                case "1":
                    System.out.println("Selected bubble sort algorithm...");
                    controller.sort("Bubble Sort");
                    break;
                case "2":
                    System.out.println("Selected insertion sort algorithm...");
                    controller.sort("Insertion Sort");
                    break;
                case "3":
                    System.out.println("Selected selection sort algorithm...");
                    controller.sort("Selection Sort");
                    break;
                case "4":
                    System.out.println("Selected shell sort algorithm...");
                    controller.sort("Shell Sort");
                    break;
                case "5":
                    System.out.println("Selected merge sort algorithm...");
                    controller.sort("Merge Sort");
                    break;
                case "6":
                    System.out.println("Selected merge sort in-place algorithm...");
                    controller.sort("Merge (in-place)");
                    break;
                case "7":
                    System.out.println("Selected quick sort algorithm...");
                    controller.sort("Quick Sort");
                    break;
                default:
                    System.out.println("The option selected is invalid");
                    break;
            }
        }

    }

    /**
     * Console UI for visualizing data structures or algorithms
     */
    public void runVisualizer() {
        String option = "";
        while (!option.equals("0")) {
            System.out.println("Welcome to the Visualizer");
            System.out.println("This utility lets you inspect the code implementation for different algorithms and data structures");
            System.out.println("Visualizer options:");
            System.out.println("0. Type 0 to return to the main menu");
            System.out.println("1. Type 1 to see a list of available algorithms");
            System.out.println("2. Type 2 to see a list of available data structures");
            option = consoleScanner.next();
            switch (option) {
                case "0":
                    break;
                case "1":
                    System.out.println("Selected algorithms visualizer...");
                    runAlgorithmsVisualizer();
                    break;
                case "2":
                    System.out.println("Selected data structures visualizer...");
                    runDataStructuresVisualizer();
                    break;
                default:
                    System.out.println("The option selected is invalid");
                    break;
            }
        }
    }

    /**
     * Console UI for visualizing data structures
     */
    private void runDataStructuresVisualizer() {
        String dataStructure = "";
        while (!dataStructure.equals("0")) {

            System.out.println("Data Structures options:");
            System.out.println("0. Type 0 to return to the Visualizer");
            System.out.println("1. Type 1 to inspect Linked List");
            System.out.println("2. Type 2 to inspect Stack");
            System.out.println("3. Type 3 to inspect Queue");
            System.out.println("4. Type 4 to inspect HashTable");
            System.out.println("5. Type 5 to inspect Graph");
            System.out.println("6. Type 6 to inspect Linked List (Simple)");

            dataStructure = consoleScanner.next();
            Boolean valid = true;
            switch (dataStructure) {
                case "1":
                    System.out.println("Selected data structure \"Linked List\"...");
                    controller.visualizeDataStructure("Linked List");
                    break;
                case "2":
                    System.out.println("Selected data structure \"Stack\"...");
                    controller.visualizeDataStructure("Stack");
                    break;
                case "3":
                    System.out.println("Selected data structure \"Queue\"...");
                    controller.visualizeDataStructure("Queue");
                    break;
                case "4":
                    System.out.println("Selected data structure \"HashTable\"...");
                    controller.visualizeDataStructure("HashTable");
                    break;
                case "5":
                    System.out.println("Selected data structure \"Graph\"...");
                    controller.visualizeDataStructure("Graph");
                    break;
                case "6":
                    System.out.println("Selected data structure \"Linked List (simple)\"...");
                    controller.visualizeDataStructure("Linked List (simple)");
                    break;
                case "0":
                    valid = false;
                    break;
                default:
                    System.out.println("The option selected is invalid");
                    valid = false;
                    break;
            }
            if (valid) {
                System.out.println(model.getVisualizerText());
            }
        }
    }

    /**
     * Console UI for visualizing algorithms
     */
    private void runAlgorithmsVisualizer() {
        String algorithm = "";
        while (!algorithm.equals("0")) {

            System.out.println("Algorithms options:");
            System.out.println("0. Type 0 to return to the Visualizer");
            System.out.println("1. Type 1 to inspect linear search");
            System.out.println("2. Type 2 to inspect binary search");
            System.out.println("3. Type 3 to inspect bubble sort");
            System.out.println("4. Type 4 to inspect insertion sort");
            System.out.println("5. Type 5 to inspect selection sort");
            System.out.println("6. Type 6 to inspect shell sort");
            System.out.println("7. Type 7 to inspect merge sort");
            System.out.println("8. Type 8 to inspect merge sort (in-place)");
            System.out.println("9. Type 9 to inspect quick sort");

            algorithm = consoleScanner.next();
            boolean valid = true;
            switch (algorithm) {
                case "0":
                    valid = false;
                    break;
                case "1":
                    System.out.print("Selected searching algorithm \"Linear Search\"...");
                    controller.visualizeAlgorithm("Linear Search");
                    break;
                case "2":
                    System.out.print("Selected searching algorithm \"Binary Search\"...");
                    controller.visualizeAlgorithm("Binary Search");
                    break;
                case "3":
                    System.out.print("Selected sorting algorithm \"Bubble Sort\"...");
                    controller.visualizeAlgorithm("Bubble Sort");
                    break;
                case "4":
                    System.out.print("Selected sorting algorithm \"Insertion Sort\"...");
                    controller.visualizeAlgorithm("Insertion Sort");
                    break;
                case "5":
                    System.out.print("Selected sorting algorithm \"Selection Sort\"...");
                    controller.visualizeAlgorithm("Selection Sort");
                    break;
                case "6":
                    System.out.print("Selected sorting algorithm \"Shell Sort\"...");
                    controller.visualizeAlgorithm("Shell Sort");
                    break;
                case "7":
                    System.out.print("Selected sorting algorithm \"Merge Sort\"...");
                    controller.visualizeAlgorithm("Merge Sort");
                    break;
                case "8":
                    System.out.print("Selected sorting algorithm \"Merge Sort (in-place)\"...");
                    controller.visualizeAlgorithm("Merge Sort (in-place)");
                    break;
                case "9":
                    System.out.print("Selected sorting algorithm \"Quick Sort\"...");
                    controller.visualizeAlgorithm("Quick Sort");
                    break;
                default:
                    System.out.println("The option selected is invalid");
                    valid = false;
                    break;
            }
            if (valid) {
                System.out.println(model.getVisualizerText());
            }
        }
    }
}
