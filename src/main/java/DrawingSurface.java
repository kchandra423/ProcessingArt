import processing.core.PApplet;

public class DrawingSurface extends PApplet {
    public void setup() {

    }

    public void draw() {
        drawTreeRecur(200f, 200f, 400f, -(float) (Math.PI) / 2);
    }

    public void drawTree() {

    }

    public void drawTreeRecur(float length, float startx, float starty, float angle) {
        if (length < 10) {
            return;
        }
        float endx = startx + cos(angle) * length, endy = starty + sin(angle) * length;
        line(startx, starty, endx, endy);
        drawTreeRecur( length / 2, endx, endy, (float) (angle + Math.PI / 5));
        drawTreeRecur(length/2, endx, endy, (float) (angle - Math.PI / 5));
    }
}
