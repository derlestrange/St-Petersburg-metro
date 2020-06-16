import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestRouteCalculator extends TestCase {

    /**
     * Line 1: i, i1, i2;
     * Line 2: k, k1, k2;
     * Line 3; j, j1, j2;
     * <p>
     * Connections: j1/i1, j3/k1;
     * <p>
     * line2
     * j
     * |
     * |
     * line1                i — — j1/i1 — — i2
     * |
     * |
     * line3                k — — j2/k1 — — k2
     */

    public RouteCalculator routeCalculator;
    public StationIndex stationIndex;
    public List<Station> path;
    public List<Station> pathNoConnections;

    //lines with stations
    Line line1;
    Station i, i1, i2;

    Line line2;
    Station j, j1, j2;

    Line line3;
    Station k, k1, k2;


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

        routeCalculator = new RouteCalculator(creatingStationIndex());

    }

    @Test
    public void testCalculateDuration() {
        path = Stream.of(i, i1, i2, j, j1, j2, k, k1, k2).collect(Collectors.toList());
        double expected = RouteCalculator.calculateDuration(path);
        double actual = 22;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetShortRoutes(){
        pathNoConnections = Stream.of(i, i1, i2).collect(Collectors.toList());
        //
    }


    //creating station index and adding lines, stations and connections
    public StationIndex creatingStationIndex() {
        stationIndex = new StationIndex();

        Stream.of(i, i1, i2, j, j1, j2, k, k1, k2)
                .peek(s -> s.getLine().addStation(s))
                .forEach(stationIndex::addStation);

        Stream.of(line1, line2, line3).forEach(stationIndex::addLine);

        return stationIndex;
    }


}
