package Models.ALGs;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * <p><b>Algorithms</b> is a static class implementing common algorithms
 * <p>It is 'static' because the constructor is private and all methods are static</p>
 *
 * @author JorgeCFedz
 * @version 1.1
 * @since 1.0
 */
public final class Algorithms {

    private Algorithms() {
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	INTERVIEW ALGORITHMS
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * DP Recursive algorithm with a running sum where at each recursion
     * we calculate the number of goldCoins if we choose each available
     * option (left or right), assuming the opponent will pick the best option
     *
     * @param goldCoins array of coins to pick from
     * @param start     current leftMost coin available to be picked
     * @param end       current rightMost coin available to be picked
     * @return the maximum number of Coins that we can collect if both us and the opponent play the best game
     */
    public static int maxCoins(int[] goldCoins, int start, int end) {
        if (start > end) {
            return 0;
        }
        int a = goldCoins[start] + Math.min(maxCoins(goldCoins, start + 2, end), maxCoins(goldCoins, start + 1, end - 1));
        int b = goldCoins[end] + Math.min(maxCoins(goldCoins, start, end - 2), maxCoins(goldCoins, start + 1, end - 1));
        return Math.max(a, b);
    }

    /**
     * houseRobber returns the biggest Running Sum in an array of ints
     * condition: no 2 consecutive elements of the array can be added
     * @param nums the given array of ints
     * @return the max running Sum
     */
    public static int houseRobber(int[] nums) {
        int i = 0;                  // Largest running Sum including this house
        int e = 0;                  // Largest running Sum excluding this house
        for (int k = 0; i <= nums.length; i++) {
            int lastI = i;
            i = e + nums[k];
            e = Math.max(lastI, e);
        }

        return Math.max(e, i);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	PRIMALITY
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * hashTwoPrimeFactors checks if the given number can be decomposed
     * in exactly 2 prime factors.
     * In increasing order it finds factors for the given integer and verifies
     * they are prime. If so, it checks whether the quotient is prime too
     * Obeys the fundamental theorem of arithmetic: every integer greater than 1
     * is either prime itself or the product of prime numbers (and this product is unique)
     * Implemented:
     * optimization1: if the number is divisible by 2
     * //     we can remove all multiples following the sieve
     * //     of Eratosthenes
     * //     optimization2: Every composite number has at least one
     * //     prime factor smaller than its square root
     * //     optimization3: No need to check if the candidate divisor is
     * //     composite. if it is composite we already checked its factors
     *
     * @param a, the given positive integer to scan for prime factors
     * @return whether the given integer has exactly 2 prime factors or not
     */
    public static boolean hasTwoPrimeFactors(int a) {
        // Sieve of Eratosthenes
        if ((a % 2 == 0) && (a > 2)) {
            return isPrime(a / 2);
        }
        // No need to check 1 (prime factors are only divisible by itself or 1)
        for (int i = 3; i <= Math.sqrt(a); i += 2) {
            // No need to check i is prime (if is composite we already checked its factors during
            // generation (increasing order)
            // try to divide a by i - check remainder is 0
            // check result is prime (if result is not prime then it has more than two prime factors or a is prime)
            if (a % i == 0) {
                // quotient must be prime too
                return isPrime(a / i);
            }
        }
        return false;
    }

    /**
     * isPrime generates in increasing order possible factors for the given
     * number and tests if they are divisors
     *
     * @param a, the given number to check for primality
     * @return whether the given number is prime
     */
    public static boolean isPrime(int a) {
        // Sieve of Eratosthenes
        if ((a > 2) && (a % 2 == 0)) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(a); i += 2) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	EXPRESSION PARSING
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Parses and computes a postfix expression
     * Assumes a valid postfix expression is given as input
     * TO DO: check that the operands are valid
     * TO DO: currently it only works for single digits
     * FIX: StringUtils.isNumeric() / or check that each char is a digit and keep parsing them
     * Note: I know I can implement this method easier using Java libraries but I wanted to play with
     * numeric ASCII manipulation
     * Can cast the char to Character objects to use Java libraries
     *
     * @param input the given input to parse
     * @return the computed result of evaluating the given expression
     * @throws IllegalArgumentException if the given input is not a valid postFix expression
     */
    public static double parsePostfix(String input) {
        Stack operandStack = new Stack<Double>();
        Scanner expressionScanner = new Scanner(input);
        double result = 0;
        while (expressionScanner.hasNext()) {
            String nextTerm = expressionScanner.next();
            if (nextTerm.length() > 1)
                throw new IllegalArgumentException("The given string " +
                        "is not a valid postFix expression");
            char nextChar = nextTerm.charAt(0);
            //int nextValue = Character.getNumericValue(nextChar);                  // get numeric value using a library
            if (nextChar - '0' > 9 || nextChar - '0' < 0) {
                if (operandStack.size() < 2) {
                    throw new IllegalArgumentException("The given string " +
                            "is not a valid postFix expression");
                }
                double operand1 = (double) operandStack.pop();
                double operand2 = (double) operandStack.pop();
                switch (nextChar) {
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        result = operand2 / operand1;
                        break;
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '^':
                        result = Math.pow(operand2, operand1);
                        break;
                    default:
                        throw new IllegalArgumentException("The given string " +
                                "is not a valid postFix expression");
                }
                operandStack.push(result);
            } else {
                operandStack.push((double) (nextChar - '0'));
            }
        }
        return result;
    }

    // testing helper method to parsePostfix
    public static void expressionParser() {
        String result = "";
        System.out.println("Result is equal to: " + result);
        String expression1 = "2 3 +";
        System.out.println("The result of computing the expression: "
                + expression1 + " is " + parsePostfix(expression1));
        String expression2 = "2 3 + 4 *";
        System.out.println("The result of computing the expression: "
                + expression2 + " is " + parsePostfix(expression2));
        String expression3 = "2 3 4 + *";
        System.out.println("The result of computing the expression: "
                + expression3 + " is " + parsePostfix(expression3));
        String expression4 = "2 3 / 4 5 / + 7 ^";
        System.out.println("The result of computing the expression: "
                + expression4 + " is " + parsePostfix(expression4));
        String expression5 = "2 3 + 4 5 + *";
        System.out.println("The result of computing the expression: "
                + expression5 + " is " + parsePostfix(expression5));
        String expression6 = "2 3 + 4 * 5 -";
        System.out.println("The result of computing the expression: "
                + expression6 + " is " + parsePostfix(expression6));
        String expression7 = "2 3 / 4 5 / + 8 ^";
        System.out.println("The result of computing the expression: "
                + expression7 + " is " + parsePostfix(expression7));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	SEARCHING ALGORITHMS
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Searches for the first occurrence of the key in the given list
     *
     * @param list the list where to look for the key
     * @param key  the key to search
     * @return the first occurrence of the key or -1 if the key is not found
     */
    public static int linearSearch(int[] list, int key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == key)
                return i;
        }
        return -1;
    }

    /**
     * Binary Search Algorithm using an array
     * Note: shouldn't mid calculation round down thus never reaching upper bounds?
     * Does it return first occurrence of the key?
     *
     * @param list the list where to look for the key
     * @param key the key to search
     * @return the position of the given key or -1 if not found
     */
    public static int binarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length;
        while (low <= high) {
            //int mid = (high-low) / 2;
            int mid = low + (high - low) / 2;                 // note: preferred mode because division truncates for negatives
            if (list[mid] < key) {
                low = mid + 1;
            } else if (list[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Interpolation Search Algorithm using an array
     * NOTES: Review edge cases (changed high = mid to high = mid - 1)
     * Does it return first occurrence of the key?
     *
     * @param list the list where to look for the key
     * @param key the key to search
     * @return the position of the given key or -1 if not found
     */
    public static int interpolationSearch(int[] list, int key) {
        int low = 0;
        int high = list.length;
        while (low <= high) {
            int mid = low + ((high - low) / (list[high] - list[low])) * (key - list[low]);
            System.out.print("the new mid is: " + mid + "...");
            if (list[mid] < key) {
                low = mid + 1;
            } else if (list[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	SORTING ALGORITHMS
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * In-place/ Stable/ sorting algorithm (very inefficient)
     * Complexity:
     * Best:       O(n)    nearly sorted
     * Average:    O(n^2)
     * Worst:      O(n^2)
     * bubble Sort reorders a given list of n size by reorganizing the list
     * n-1 times. During each reorganization each 2 adjacent objects of the list
     * are reordered.
     * bubble Sort consecutively finds the next max value in the array and places
     * it in descending order at the right side of the array
     * Note: can add swapped boolean so that if during a reorganization no
     * 2 adjacent elements were swapped the algorithm will terminate
     * Note/ FIX: after every iteration the highest values settles down at the end of the array
     * no need to iterate over already sorted elements during each reorganization
     * j &lt;= (list.length - 2) - i
     *
     * @param list the given list to reorder
     */
    public static void bubbleSort(int[] list) {
        // the last element is automatically ordered after
        // we have ordered the top n-1 elements of the array
        for (int i = 0; i < list.length - 1; i++) {
            // at every iteration the next max value is placed at
            // the right side of the unordered portion of the array
            // no need to reorder that portion of the array
            for (int j = 0; j < list.length - 1 - i; j++) {
                int left = list[j];
                int right = list[j + 1];
                if (left > right) {
                    list[j] = right;
                    list[j + 1] = left;
                }
            }
        }
    }

    /**
     * In-place/ Stable/ sorting algorithm (very efficient for small arrays)
     * Complexity:
     * Best:       O(n)    nearly sorted
     * Average:    O(n^2)
     * Worst:      O(n^2)
     * Insertion Sort swaps the next element in the list with all the previously
     * sorted elements consecutively until the previously reordered portion of the list becomes
     * ordered again
     * NOTE/ FIX: Include first swap inside for loop swapping iteration
     * Only need to swap iterate left until a swap is not needed (rest ordered)
     *
     * @param list the given list to reorder
     */
    public static void insertionSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] > list[i + 1]) {                            // can do without the if (just jump into while loop)
                int j = i;
                while (j >= 0 && (list[j] > list[j + 1])) {
                    int oldLeft = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = oldLeft;
                    j--;
                }
            }
        }
    }

    /**
     * In-place/ Stable/ sorting algorithm
     * Complexity:
     * Best:       O(n^2)
     * Average:    O(n^2)
     * Worst:      O(n^2)
     * selection Sort reorders a given list of n size by performing n-1 iterations
     * over the list. During each iterations the next lowest element is selected and swapped
     * with the next unordered element
     *
     * @param list the given list to reorder
     */
    public static void selectionSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int low = list[i];
            int posLow = i;
            for (int j = i + 1; j < list.length; j++) {
                if (low > list[j]) {
                    low = list[j];
                    posLow = j;
                }
            }
            list[posLow] = list[i];
            list[i] = low;
        }
    }

    /**
     * In-place/ Unstable/ sorting algorithm (Knuth gap sequence..1, 4, 13..)
     * Complexity:
     * Worst case:     O( n^(3/2) )    for Knuth's sequence
     * Shell Sort combines insertion sort with a decreasing gap algorithm
     * First it divides the list into sublist with gap distance between their elements
     * It then performs insertion Sort in every sublist
     * This algorithm is repeated for sub-lists of decreasing gap until reaching a gap = 1
     *
     * @param list the given list to reorder
     */
    public static void shellSort(int[] list) {
        // Using knuth gap sequence = (3^k – 1) / 2
        // other found* Knuth sequence = 3k + 1
        // calculate max gap or interval (written verbose for clarity purposes)
        int k = 1;
        while ((Math.pow(3, (k + 1)) - 1) / 2 < list.length - 1) {
            k++;
        }
        int interval = ((int) Math.pow(3, (k + 1)) - 1) / 2;
        while (interval >= 1) {                                                         // for every gap in the sequence starting by the biggest gap possible
            // for (interval = ((int) Math.pow(3,(k)) - 1)/2; k >= 1; k--) {
            for (int i = 0; i < interval && i + interval <= list.length - 1; i++) {         // m possible sub-lists in a gap of size m
                // insertion Sort
                for (int j = i; j <= (list.length - 1) - interval; j += interval) {      // for every element in the sublist
                    if (list[j] > list[j + interval]) {
                        int oldLeft = list[j];
                        list[j] = list[j + interval];
                        list[j + interval] = oldLeft;
                        for (int l = j; l >= i + interval; l -= interval) {            // insert the element in the right order
                            if (list[l - interval] > list[l]) {
                                int oldLeft2 = list[l - interval];
                                list[l - interval] = list[l];
                                list[l] = oldLeft2;
                            }
                        }
                    }
                }
            }
            k--;
            interval = ((int) Math.pow(3, (k)) - 1) / 2;
        }
    }

    /**
     * Out-of-place/ Stable/ recursive sorting algorithm
     * Complexity:
     * Best Case:      O( n*log(n) )
     * Average Case:   O( n*log(n) )
     * Worst Case:     O( n*log(n) )
     * Recursively divides an array in two halves that are sorted and merged
     * following a BOTTOM-UP approach (solve for the smallest case and find your
     * "way-up" the final solution)
     * NOTE: Could be implemented faster by doubling the size of the original array
     * and using the extra space to store temporal results during merges
     * NOTE: Could be implemented in place by avoiding unmerged elements
     * from being overwritten. (Use link list instead of array and change links)
     * NOTE: Can reduce in half by copying the left sub-array into the helper array
     * before merging, then proceed to merge. This also removes the need to copy the
     * helper array into the original array at the end of the merge
     * NOTE: Can avoid passing the array object during all recursive calls by
     * creating a sorting class with an auxiliary array field
     * NOTE: Can collapse the second mergeSort helper method by moving the base case
     * to the 2nd helper method (simply check low &lt; high to do anything) and
     * doing the first division of the array in the original called method
     *
     * @param array   the given array to sort
     * @param inPlace whether to run merge sort in place or out of place
     */
    public static void mergeSort(int[] array, boolean inPlace) {
        if (array == null)
            throw new IllegalArgumentException("The given array is null");
        int[] result = new int[array.length];
        if (!inPlace)
            mergeSort(array, result, 0, array.length - 1);
        else
            mergeSortInPlace(array, 0, array.length - 1);
    }

    /**
     * helper method to divide an original array into two halves to be
     * sorted
     *
     * @param array  the original array to divide in two halves
     * @param result the helper array to store temp results during merge calls
     * @param low    the lowest index of the original sub-array being sorted
     * @param high   the highest index of the original sub-array being sorted
     */
    private static void mergeSort(int[] array, int[] result, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(array, result, low, mid);
            mergeSort(array, result, mid + 1, high);
            merge(array, result, low, mid, high);
        }
    }

    /**
     * helper method to divide an original array into two halves to be
     * sorted
     * <p>
     * NOTE: In place (also stable)
     * if lefLow <= rightLow then just move leftLow one spot right
     * if leftLow > rightLow swap the value in leftLow with the value in rightLow and move leftLow one spot right
     * once leftLow > leftHigh or rightLow > rightHigh the sub-array is ordered (the rest of the array not looped through is already ordered)
     *
     * @param array the original array to divide in two halves
     * @param low   the lowest index of the original sub-array being sorted
     * @param high  the highest index of the original sub-array being sorted
     */
    private static void mergeSortInPlace(int[] array, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSortInPlace(array, low, mid);
            mergeSortInPlace(array, mid + 1, high);
            mergeInPlace(array, low, mid, high);
        }
    }

    /**
     * Original (out of place mergeSort)
     * Merges and sorts two original sub-arrays by storing results in a temp sub-array
     * and copying these results into the original array at the end
     *
     * @param array  the original array we are sorting during the merges
     * @param result the helper array used to store temporal sorted merges before copying them
     * @param low    the lowest index of the first original sub-array being sorted
     * @param mid    the highest index of the first original sub-array being sorted
     * @param high   the highest index of the second original sub-array being sorted
     */
    private static void merge(int[] array, int[] result, int low, int mid, int high) {
        int leftCursor = low;
        int rightCursor = mid + 1;
        int resultCursor = low;

        while (leftCursor <= mid && rightCursor <= high) {
            if (array[leftCursor] <= array[rightCursor]) {
                result[resultCursor] = array[leftCursor];
                leftCursor++;
            } else {
                result[resultCursor] = array[rightCursor];
                rightCursor++;
            }
            resultCursor++;
        }
        while (leftCursor <= mid) {
            result[resultCursor] = array[leftCursor];
            leftCursor++;
            resultCursor++;
        }

        while (rightCursor <= high) {
            result[resultCursor] = array[rightCursor];
            rightCursor++;
            resultCursor++;
        }
//        assert resultCursor > high;
        for (int i = low; i <= high; i++)
            array[i] = result[i];
    }

    /**
     * In Place mergeSort O(n^2)
     * Not anymore O( n * log(n) ) because it could have to swap to the right array every value in the right array
     * and be forced to perform insertion Sort in all of them
     * However equal performance for non inverted arrays (worst case scenario)
     * FIX!!!!
     *
     * @param array the original array we are sorting during the merges
     * @param low   the lowest index of the first original sub-array being sorted
     * @param mid   the highest index of the first original sub-array being sorted
     * @param high  the highest index of the second original sub-array being sorted
     */
    private static void mergeInPlace(int[] array, int low, int mid, int high) {
        int leftLow = low;
        int rightLow = mid + 1;

        while (leftLow <= mid) {
            if (array[leftLow] <= array[rightLow]) {
                leftLow++;
            } else {
                int temp = array[leftLow];
                array[leftLow] = array[rightLow];
                leftLow++;
                // this value may not be the smallest in the right array anymore/ breaks order (insertion sort it?)
                array[rightLow] = temp;
                // insertion Sort of the new value on right array
                int i = rightLow;
                while (i < high && array[i] > array[i + 1]) {
                    int temp2 = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp2;
                    i++;
                }
            }
        }
    }

    /**
     * In-place/ Unstable/ recursive sorting algorithm
     * Complexity:
     * Best Case:      O( n*log(n) )
     * Average Case:   O( n*log(n) )
     * Worst Case:     O(n^2)
     * Recursively
     * selects a pivot value from a sub-list
     * divides it into two halves (greater and lesser than the pivot)
     * following a TOP-DOWN approach (Solve as you divide problem in
     * smaller halves until problem is fully solved)
     * Note: Uses insertion Sort for arrays &lt;~ 4 (more efficient for &lt;~10)
     * NOTE: Chooses middle element as pivot to avoid worst case performance
     * for sorted/ reverse sorted/ or nearly sorted lists
     * NOTE: Improve for nearly sorted lists
     * NOTE: Improve insertion sort so it can be called from this method (given array, upper, and lower bound)
     *
     * @param array the given array to sort
     */
    public static void quickSort(int[] array) {
        int low = 0;
        int high = array.length - 1;
        quickSort(array, low, high);
    }

    private static void quickSort(int[] array, int low, int high) {
        // choose a threshold (inefficient for small arrays)
        if (high - low >= 4) {
            // quick-sort
            int mid = low + (high - low) / 2;
            int pivot = array[mid];
            array[mid] = array[high];
            array[high] = pivot;
            int leftCursor = low;
            int rightCursor = high - 1;
            while (leftCursor < rightCursor) {
                // displace left Cursor to the right until the value is bigger than pivot
                while (array[leftCursor] < pivot) {
                    leftCursor++;
                }
                // displace right Cursor to the left until the value is smaller than pivot
                while (array[rightCursor] > pivot && rightCursor > 0) {
                    rightCursor--;
                }
                // swap the left value with the right value
                if (leftCursor < rightCursor) {
                    int oldLeft = array[leftCursor];
                    array[leftCursor] = array[rightCursor];
                    array[rightCursor] = oldLeft;
                    leftCursor++;
                    rightCursor--;
                }
            }
            // swap pivot with left Cursor unless the array is ordered
            array[high] = array[leftCursor];
            array[leftCursor] = pivot;
            quickSort(array, low, --leftCursor);
            quickSort(array, ++leftCursor, high);
        } else {
            // insertion sort
            for (int i = low; i < high; i++) {
                if (array[i] > array[i + 1]) {
                    int j = i;
                    while (j >= low && (array[j] > array[j + 1])) {
                        int oldLeft = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = oldLeft;
                        j--;
                    }
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Randomly shuffles a copy of the given array
     * NOTE: Modified Code from method java.util.Collections.shuffle();
     *
     * @param array the array to copy and shuffle
     * @return a new shuffled copy of the given array
     */
    public static int[] shuffle(int[] array) {
        Random random = new Random();
        int[] result = array.clone();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            swap(result, i - 1, random.nextInt(i));
        }
        return result;
    }

    /**
     * Helper method that swaps the value of 2 elements of an array
     *
     * @param array the array to swap elements from
     * @param i     the leftmost element to swap
     * @param j     the rightmost element to swap
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
