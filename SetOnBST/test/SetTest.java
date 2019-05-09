import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for kernel methods
     */

    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef("A");
        /*
         * Call method under test
         */
        q.add("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddNonEmptyOne() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A");
        Set<String> qExpected = this.createFromArgsRef("A", "B");
        /*
         * Call method under test
         */
        q.add("B");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A", "B", "C");
        Set<String> qExpected = this.createFromArgsRef("A", "B", "C", "D");
        /*
         * Call method under test
         */
        q.add("D");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A");
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String x = q.remove("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("A", x);
    }

    @Test
    public final void testRemoveLeavingNonEmptyOne() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A", "B");
        Set<String> qExpected = this.createFromArgsRef("A");
        /*
         * Call method under test
         */
        String x = q.remove("B");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("B", x);
    }

    @Test
    public final void testRemoveLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A", "B", "C");
        Set<String> qExpected = this.createFromArgsRef("B", "C");
        /*
         * Call method under test
         */
        String x = q.remove("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("A", x);
    }

    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int i = q.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(0, i);
    }

    @Test
    public final void testSizeNonEmptyOne() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A");
        Set<String> qExpected = this.createFromArgsRef("A");
        /*
         * Call method under test
         */
        int i = q.size();
        int k = qExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(1, i);
        assertEquals(1, k);
    }

    @Test
    public final void testSizeNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A", "B", "C");
        Set<String> qExpected = this.createFromArgsRef("A", "B", "C");
        /*
         * Call method under test
         */
        int i = q.size();
        int k = qExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(3, i);
        assertEquals(3, k);
    }

    @Test
    public final void testRemoveAnyNonEmptyOnlyOne() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A");
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        q.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testRemoveAnyNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A", "B", "C");
        Set<String> qExpected = this.createFromArgsRef("A", "B", "C");
        /*
         * Call method under test
         */
        String str = q.removeAny();
        qExpected.remove(str);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testContainsNonEmptyMoreThanOneTrue() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A", "B", "C");
        Set<String> qExpected = this.createFromArgsRef("A", "B", "C");
        /*
         * Call method under test
         */
        boolean judge1 = q.contains("A");
        boolean judge2 = q.contains("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(judge1, judge2);
    }

    @Test
    public final void testContainsNonEmptyMoreThanOneFalse() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A", "B", "C");
        Set<String> qExpected = this.createFromArgsRef("A", "B", "C");
        /*
         * Call method under test
         */
        boolean judge1 = q.contains("D");
        boolean judge2 = q.contains("D");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(judge1, judge2);
    }

    @Test
    public final void testContainsNonEmptyOneTrue() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A");
        Set<String> qExpected = this.createFromArgsRef("A");
        /*
         * Call method under test
         */
        boolean judge1 = q.contains("A");
        boolean judge2 = q.contains("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(judge1, judge2);
    }

    @Test
    public final void testContainsNonEmptyOneFalse() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("A");
        Set<String> qExpected = this.createFromArgsRef("A");
        /*
         * Call method under test
         */
        boolean judge1 = q.contains("B");
        boolean judge2 = q.contains("B");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(judge1, judge2);
    }

    @Test
    public final void testContainsEmpty() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        boolean judge1 = q.contains("A");
        boolean judge2 = q.contains("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(judge1, judge2);
    }
}
