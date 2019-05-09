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

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    //Constructor test
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> map = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
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
        Map<String, String> qExpected = this.createFromArgsRef("A", "1");
        /*
         * Call method under test
         */
        q.add("A", "1");
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
        Map<String, String> q = this.createFromArgsTest("A", "1");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1", "B",
                "2");
        /*
         * Call method under test
         */
        q.add("B", "2");
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
        Map<String, String> q = this.createFromArgsTest("A", "1", "B", "2", "C",
                "3");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1", "B",
                "2", "C", "3", "D", "4");
        /*
         * Call method under test
         */
        q.add("D", "4");
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
        Map<String, String> q = this.createFromArgsTest("A", "1");
        Map<String, String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Map.Pair<String, String> x = q.remove("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("A", x.key());
        assertEquals("1", x.value());
    }

    @Test
    public final void testRemoveLeavingNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("A", "1", "B", "2");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1");
        /*
         * Call method under test
         */
        Map.Pair<String, String> x = q.remove("B");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("B", x.key());
        assertEquals("2", x.value());
    }

    @Test
    public final void testRemoveLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("A", "1", "B", "2", "C",
                "3");
        Map<String, String> qExpected = this.createFromArgsRef("B", "2", "C",
                "3");
        /*
         * Call method under test
         */
        Map.Pair<String, String> x = q.remove("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("A", x.key());
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
        Map<String, String> q = this.createFromArgsTest("A", "1");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1");
        /*
         * Call method under test
         */
        int i = q.size();
        int k = qExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(i, k);
    }

    @Test
    public final void testSizeNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("A", "1", "B", "2", "C",
                "3");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1", "B",
                "2", "C", "3");
        /*
         * Call method under test
         */
        int i = q.size();
        int k = qExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(i, k);
    }

    @Test
    public final void testRemoveAnyNonEmptyOnlyOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("A", "1");
        Map<String, String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Map.Pair<String, String> remove = q.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("A", remove.key());
        assertEquals("1", remove.value());
    }

    @Test
    public final void testRemoveAnyNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("A", "1", "B", "2", "C",
                "3");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1", "B",
                "2", "C", "3");
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
    public final void testValueNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("A", "1");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1");
        /*
         * Call method under test
         */
        String str1 = q.value("A");
        String str2 = q.value("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(str1, str2);
    }

    @Test
    public final void testValueNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("A", "1", "B", "2", "C",
                "3");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1", "B",
                "2", "C", "3");
        /*
         * Call method under test
         */
        String str1 = q.value("A");
        String str2 = q.value("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(str1, str2);
    }

    @Test
    public final void testHasKeyNonEmptyMoreThanOneFalse() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("A", "1", "B", "2", "C",
                "3");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1", "B",
                "2", "C", "3");
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
    public final void testHasKeyEmptyFalse() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        boolean judge1 = q.hasKey("A");
        boolean judge2 = q.hasKey("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(judge1, judge2);
    }

    @Test
    public final void testHasKeyNonEmptyOneTrue() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("A", "1");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1");
        /*
         * Call method under test
         */
        boolean judge1 = q.hasKey("A");
        boolean judge2 = q.hasKey("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(judge1, judge2);
    }

    @Test
    public final void testHasKeyNonEmptyMoreThanOneTrue() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("A", "1", "B", "2", "C",
                "3");
        Map<String, String> qExpected = this.createFromArgsRef("A", "1", "B",
                "2", "C", "3");
        /*
         * Call method under test
         */
        boolean judge1 = q.hasKey("A");
        boolean judge2 = q.hasKey("A");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(judge1, judge2);
    }
}
