import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestRouteCalculator extends TestCase {

    /**
     * Line 1: i, i1, i2, i3, i4;
     * Line 2: k, k1, k2;
     * Line 3; j, j1, j2;
     *
     * Connections: j1/i1, j2/k1;
     *
     *                            line2
     *                              j
     *                              |
     *                              |
     *             line1    i — — j1/i1 — — i2 -- i3 -- i4
     *                              |
     *                              |
     *             line3    k — — j2/k1 — — k2
     */

    public RouteCalculator routeCalculator;
    public StationIndex stationIndex;

    public List<Station> path;
    public List<Station> actualPathConnections;

    //lines with stations
    public Line line1;
    public Station i, i1, i2, i3, i4;

    public Line line2;
    public Station j, j1, j2;

    public Line line3;
    public Station k, k1, k2;


    public void setUp() {

        line1 = new Line(1, "FirstLine");
        i = new Station("i", line1);
        i1 = new Station("i1", line1);
        i2 = new Station("i2", line1);
        i3 = new Station("i3", line1);
        i4 = new Station("i4", line1);

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

    /*testing shortest routes on the line
     *
     *             line1    i — — j1/i1 — — i2 -- i3 -- i4
     *
     */

    @Test
    public void testGetShortRoutesOnTheLine(){
        path = Stream.of(i, i1, i2, i3, i4).collect(Collectors.toList());
        actualPathConnections = routeCalculator.getShortestRoute(i, i4);
        assertEquals(path, actualPathConnections);
    }

    /*testing shortest routes with one connections

     *                            line2
     *                              j
     *                              |
     *                              |
     *             line1          j1/i1 — — i2 -- i3 -- i4
     *
     */
    @Test
    public void testGetShortestRouteWithOneConnections(){
        path = Stream.of(i4, i3, i2, i1, j1, j).collect(Collectors.toList());
        actualPathConnections = routeCalculator.getShortestRoute(i4, j);
        assertEquals(path, actualPathConnections);
    }

    /*testing shortest routes with two connections
     *                            line2
     *
     *             line1    i — — j1/i1
     *                              |
     *                              |
     *             line3          j2/k1 — — k2
     */
    @Test
    public void testGetShortestRouteWithTwoConnections(){
        path = Stream.of(i, i1, j1, j2, k1, k2).collect(Collectors.toList());
        actualPathConnections = routeCalculator.getShortestRoute(i, k2);
        assertEquals(path, actualPathConnections);
    }

    //testing calculate duration
    @Test
    public void testCalculateDuration() {
        path = Stream.of(i, i1, i2, i3, i4, j, j1, j2, k, k1, k2).collect(Collectors.toList());
        double expected = RouteCalculator.calculateDuration(path);
        double actual = 27;
        assertEquals(expected, actual);
    }

    //creating station index and adding lines, stations and connections
    public StationIndex creatingStationIndex() {
        stationIndex = new StationIndex();

        Stream.of(i, i1, i2, i3, i4, j, j1, j2, k, k1, k2)
                .peek(s -> s.getLine().addStation(s))
                .forEach(stationIndex::addStation);

        Stream.of(line1, line2, line3).forEach(stationIndex::addLine);

        List<Station> connections1 = Arrays.asList(i1, j1);
        List<Station> connections2 = Arrays.asList(j2, k1);

        stationIndex.addConnection(connections1);
        stationIndex.addConnection(connections2);

        return stationIndex;
    }
}