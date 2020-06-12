import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestRouteCalculator extends TestCase {
    List<Station> path;

    @Override
    protected void setUp() throws Exception {
        path = new ArrayList<>();

        Line line1 = new Line(1, "FirstLine");
        Line line2 = new Line(2, "SecondLine");
        Line line3 = new Line(3, "ThirdLine");
        Line line4 = new Line(4, "FourthLine");

        path.add(new Station("Утренняя", line1));
    }
}
