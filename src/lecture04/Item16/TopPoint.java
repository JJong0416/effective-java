package lecture04.Item16;

public class TopPoint {
    private static class Point {
        public double x;
        public double y;
    }

    public Point getPoint() {
        Point point = new Point();
        point.x = 3.5;
        point.y = 4.5;
        return point;
    }
}