import java.awt.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Line {
    final float startx, starty, length;
    final double angle;
    final boolean hasFlower;

    public Line(Point start, float length, double angle) {
        this.startx = start.x;
        this.starty = start.y;
        this.angle = angle;
        this.length = length;
        hasFlower = Math.random() < 0.1;
    }
    private Line(Point start, float length, double angle, boolean hasFlower) {
        this.startx = start.x;
        this.starty = start.y;
        this.angle = angle;
        this.length = length;
        this.hasFlower = hasFlower;
    }

    public Point getEnd() {
        return new Point((int) (startx + cos(angle) * length), (int) (starty + sin(angle) * length));
    }

    public Line get(double percent) {
        return new Line(new Point((int) startx, (int) starty), (float) (length * percent), angle, hasFlower);
    }
}
