import processing.core.PApplet;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class DrawingSurface extends PApplet {
    private static ArrayList<Tree> trees = new ArrayList<>();

    public void setup() {
    }

    public void draw() {
        background(200);
        for (Tree t :
                trees) {
            ArrayList<ArrayList<Line>> lines = t.getLines();
            for (ArrayList<Line> level:
                    lines) {
                for (Line l :
                        level) {
                    line(l.startx, l.starty, l.getEnd().x, l.getEnd().y);
                }
            }
        }

    }

    @Override
    public void mousePressed() {
        trees.add(new Tree(mouseX));
        if (trees.size()>3){
            trees.remove(0);
        }
    }
}
