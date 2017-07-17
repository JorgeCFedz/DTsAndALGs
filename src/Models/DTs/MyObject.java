package Models.DTs;

/**
 * <p><b>MyObject</b> is the root of the Model.DTs classes hierarchy. All Model.DTs implement the methods
 * of this class</p>
 *
 * @author JorgeCFedz
 * @version 1.1
 * @since 1.1
 */
public class MyObject {
    public MyObject() {
    }

    /**
     * Compares the given MyObject with this one for equality
     * Properties:
     * Reflexive: x.equals(x) returns true
     * Symmetric: x.equals(y) and y.equals(x) returns same result
     * Transitive: x.equals(y) and y.equals(z) are true? then x.equals(z) is true
     * Consistent: x.equals(y) return same result for different calls
     * This implementation only returns true for x.equals(y) when x and y refer to the same object
     *
     * @param other the MyObject to be compared with this list for equality
     * @return whether both MyObjects are equal
     */
    public boolean equals(MyObject other) {
        return super.equals(other);
    }

    /**
     * Returns a hash code value for this MyObject
     * Contract:
     * If 2 MyObjects are equal, their hashCode will be equal
     * if 2 MyObjects are not equal, their hashCode could be equal
     *
     * @return the hash code value for this MyObject
     */
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns a string representation for this MyObject
     *
     * @return a string representation of this MyObject
     */
    public String toString() {
        return super.toString();
    }
}
