import components.standard.Standard;

public interface WaitingLineKernel<T>
        extends Standard<WaitingLine<T>>, Iterable<T> {
    /**
     * Adds {@code x} to the end of {@code this}.
     *
     * @param x
     *            the entry to be added
     * @aliases reference {@code x}
     * @updates this
     * @ensures this = #this * <x>
     */
    void addLine(T customer);

    /**
     * Removes and returns the entry at the front of {@code this}.
     *
     * @return the entry removed
     * @updates this
     * @requires this /= <>
     * @ensures #this = <dequeue> * this
     */
    T removeLine();

    /*
     * Get the front of the line
     *
     * @return the first entry of the line
     */
    T frontCustomer();

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length();
}
