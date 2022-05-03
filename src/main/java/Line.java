import java.awt.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Line {
    final float startx, starty, length;
    final double angle;

    //    public Line(Point start, Point end) {
//        this.startx = start.x;
//        this.starty = start.y;
//        this.endx = end.x;
//        this.endy = end.y;
//    }
    public Line(Point start, float length, double angle) {
        this.startx = start.x;
        this.starty = start.y;
        this.angle = angle;
        this.length = length;
    }

    public Point getEnd() {
        return new Point((int) (startx + cos(angle) * length), (int) (starty + sin(angle) * length));
    }

    public Line get(double percent) {
        return new Line(new Point((int) startx, (int) starty), (float) (length * percent), angle);
    }
}
