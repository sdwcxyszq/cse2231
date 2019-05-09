
public interface WaitingLine<T> extends WaitingLineKernel<T> {
    /**
     * Reports the position of the entry
     * 
     * @param entry
     * @return the position of the entry
     * @requires this entry contains {@code entry}
     *
     */
    int position(T entry);

    /**
     * Replaces the entry at pos of {@code this} with {@code x}, and returns the
     * old front.
     *
     * @param x
     *            the new front entry
     * @return the old front entry
     * @aliases reference {@code x}
     * @updates this
     * @requires this /= <>
     * @ensures <pre>
     * <replaceFront> is prefix of #this  and
     * this = <x> * #this[1, |#this|)
     * </pre>
     */
    T replaceEntry(int pos, T x);
}
