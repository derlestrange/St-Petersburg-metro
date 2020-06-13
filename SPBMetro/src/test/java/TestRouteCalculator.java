import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class TestRouteCalculator extends TestCase {

    /**
     * Line 1: i, i1, i2;
     * Line 2: k, k1, k2;
     * Line 3; j, j1, j2;
     *
     * Connections: j1/i1, j3/k1;
     *
     *                                 line2
     *                                   j
     *                                   |
     *                                   |
     *      line1                i — — j1/i1 — — i2
     *                                   |
     *                                   |
     *      line3                k — — j2/k1 — — k2
     *
     */

    RouteCalculator routeCalculator;
    StationIndex stationIndex;

    //lines with stations
    Line line1;
    Station i, i1, i2;

    Line line2;
    Station j, j1, j2;

    Line line3;
    Station k, k1, k2;

    @Before
    public void setUp() {

        line1 = new Line(1, "FirstLine");
        i = new Station("i", line1);
        i1 = new Station("i1", line1);
        i2 = new Station("i2", line1);

        line2 = new Line(2, "SecondLine");
        j = new Station("j", line2);
        j1 = new Station("j1", line2);
        j2 = new Station("j2", line2);

        line3 = new Line(3, "ThirdLine");
        k = new Station("k", line3);
        k1 = new Station("k1", line3);
        k2 = new Station("k2", line3);

    }

    @Test
    public void testCalculateDuration(){
//        double actual = RouteCalculator.calculateDuration(path);
//        double expected = 8.5;
//        assertEquals(expected, actual);
    }


}
