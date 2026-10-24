import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test class for GradeBook.
 */
public class GradeBookTest {

    private static final double DELTA = 0.0001;

    // Test fixtures: shared objects used in multiple tests
    private GradeBook g1;
    private GradeBook g2;

    /**
     * Runs BEFORE each @Test method: creates fresh GradeBook objects.
     */
    @BeforeEach
    public void setUp() {
        g1 = new GradeBook(5);
        g1.addScore(50.0);
        g1.addScore(75.0);

        g2 = new GradeBook(5);
        g2.addScore(90.0);
        g2.addScore(85.0);
        g2.addScore(70.0);
    }

    /**
     * Runs AFTER each @Test method.
     */
    @AfterEach
    public void tearDown() {
        g1 = null;
        g2 = null;
    }

    @Test
    public void testAddScoreAndToString() {
        assertEquals("50.0 75.0 ", g1.toString());
        assertEquals("90.0 85.0 70.0 ", g2.toString());

        // Order of insertion is preserved when adding more scores
        g1.addScore(60.0);
        assertEquals("50.0 75.0 60.0 ", g1.toString());

        // An empty GradeBook has an empty string representation
        GradeBook empty = new GradeBook(5);
        assertEquals("", empty.toString());
    }

    @Test
    public void testGetScoreSize() {
        assertEquals(2, g1.getScoreSize());
        assertEquals(3, g2.getScoreSize());

        g1.addScore(60.0);
        assertEquals(3, g1.getScoreSize());

        GradeBook empty = new GradeBook(5);
        assertEquals(0, empty.getScoreSize());
    }

    @Test
    public void testSum() {
        assertEquals(125.0, g1.sum(), DELTA);
        assertEquals(245.0, g2.sum(), DELTA);

        GradeBook empty = new GradeBook(5);
        assertEquals(0.0, empty.sum(), DELTA);
    }

    @Test
    public void testMinimum() {
        assertEquals(50.0, g1.minimum(), DELTA);
        assertEquals(70.0, g2.minimum(), DELTA);

        // Minimum is not necessarily the first or last score
        GradeBook middle = new GradeBook(5);
        middle.addScore(80.0);
        middle.addScore(40.0);
        middle.addScore(95.0);
        assertEquals(40.0, middle.minimum(), DELTA);

        // Empty GradeBook returns 0
        GradeBook empty = new GradeBook(5);
        assertEquals(0.0, empty.minimum(), DELTA);
    }

    @Test
    public void testFinalScore() {
        // At least two scores: lowest is dropped
        assertEquals(75.0, g1.finalScore(), DELTA);
        assertEquals(175.0, g2.finalScore(), DELTA);

        // Exactly one score: returns that score
        GradeBook single = new GradeBook(5);
        single.addScore(100.0);
        assertEquals(100.0, single.finalScore(), DELTA);

        // No scores: returns 0
        GradeBook empty = new GradeBook(5);
        assertEquals(0.0, empty.finalScore(), DELTA);

        // Sanity check: finalScore equals sum minus minimum
        assertEquals(g2.sum() - g2.minimum(), g2.finalScore(), DELTA);
    }
}
