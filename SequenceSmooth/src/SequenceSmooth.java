import components.sequence.Sequence;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Give a smooth sequence
     *
     * @param s1
     *            the sequence to smooth
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth1(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";
        Sequence<Integer> s2 = s1.newInstance();
        for (int index = 0; index < s1.length() - 1; index++) {
            int num1 = s1.entry(index);
            int num2 = s1.entry(index + 1);
            int average = num1 / 2 + num2 / 2;
            if (num1 >= 0 && num2 >= 0 && num1 % 2 != 0 && num2 % 2 != 0) {
                average++;
            } else if (num1 <= 0 && num2 < 0 && num1 % 2 != 0
                    && num2 % 2 != 0) {
                average--;
            } else if ((num1 >= 0) != (num2 >= 0)) {
                if ((num1 % 2 != 0) != (num2 % 2 != 0)) {
                    if (Math.abs(num1) > Math.abs(num2)) {
                        average++;
                    } else if (Math.abs(num1) < Math.abs(num2)) {
                        average--;
                    }
                }
            }
            s2.add(s2.length(), average);
        }
        return s2;
    }

    /**
     * Give a smooth sequence
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth2(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";
        Sequence<Integer> s2 = s1.newInstance();
        if (s1.length() > 1) {
            int num1 = s1.remove(0);
            int num2 = s1.entry(0);
            int average = num1 / 2 + num2 / 2;
            if (num1 >= 0 && num2 >= 0 && num1 % 2 != 0 && num2 % 2 != 0) {
                average++;
            } else if (num1 <= 0 && num2 < 0 && num1 % 2 != 0
                    && num2 % 2 != 0) {
                average--;
            } else if ((num1 >= 0) != (num2 >= 0)) {
                if ((num1 % 2 != 0) != (num2 % 2 != 0)) {
                    if (Math.abs(num1) > Math.abs(num2)) {
                        average++;
                    } else if (Math.abs(num1) < Math.abs(num2)) {
                        average--;
                    }
                }
            }
            s2.add(s2.length(), average);
            s2.append(smooth2(s1));
            s1.add(0, num1);
        }
        return s2;
    }

}