import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsRef();
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
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsRef("red", "1");
        /*
         * Call method under test
         */
        q.add("red", "1");
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
        Map<String, String> q = this.createFromArgsTest("red", "1");
        Map<String, String> qExpected = this.createFromArgsRef("red", "1",
                "blue", "2");
        /*
         * Call method under test
         */
        q.add("blue", "2");
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
        Map<String, String> q = this.createFromArgsTest("red", "1", "blue", "2",
                "green", "3");
        Map<String, String> qExpected = this.createFromArgsRef("red", "1",
                "blue", "2", "green", "3", "yellow", "4");
        /*
         * Call method under test
         */
        q.add("yellow", "4");
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
        Map<String, String> q = this.createFromArgsTest("red", "1");
        Map<String, String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Map.Pair<String, String> x = q.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("red", x.key());
        assertEquals("1", x.value());
    }

    @Test
    public final void testRemoveLeavingNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("red", "1", "blue",
                "2");
        Map<String, String> qExpected = this.createFromArgsRef("red", "1");
        /*
         * Call method under test
         */
        Map.Pair<String, String> x = q.remove("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("blue", x);
        assertEquals("2", x.value());
    }

    @Test
    public final void testRemoveLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("red", "1", "green",
                "2", "blue", "3");
        Map<String, String> qExpected = this.createFromArgsRef("green", "blue");
        /*
         * Call method under test
         */
        Map.Pair<String, String> x = q.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("red", x.key());
        assertEquals("1", x.value());
    }

    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsRef();
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
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsRef();
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
        Map<String, String> q = this.createFromArgsTest("red", "1", "green",
                "2", "blue", "3");
        Map<String, String> qExpected = this.createFromArgsRef("red", "1",
                "green", "2", "blue", "3");
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
        Map<String, String> q = this.createFromArgsTest("red", "1");
        Map<String, String> qExpected = this.createFromArgsRef();
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
        Map<String, String> q = this.createFromArgsTest("red", "1", "green",
                "2", "blue", "3");
        Map<String, String> qExpected = this.createFromArgsRef("red", "1",
                "green", "2", "blue", "3");
        /*
         * Call method under test
         */
        Map.Pair<String, String> map1 = q.removeAny();
        qExpected.remove(map1.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testValueNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("red", "1", "green",
                "2", "blue", "3");
        Map<String, String> qExpected = this.createFromArgsRef("red", "1",
                "green", "2", "blue", "3");
        /*
         * Call method under test
         */
        String str1 = q.value("red");
        String str2 = q.value("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(str1, str2);
    }

    @Test
    public final void testHasKeyNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("red", "1", "green",
                "2", "blue", "3");
        Map<String, String> qExpected = this.createFromArgsRef("red", "1",
                "green", "2", "blue", "3");
        /*
         * Call method under test
         */
        boolean judge1 = q.hasKey("red");
        boolean judge2 = q.hasKey("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(judge1, judge2);
    }

    @Test
    public final void testHasKeyEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        boolean judge1 = q.hasKey("red");
        boolean judge2 = q.hasKey("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(judge1, judge2);
    }

}