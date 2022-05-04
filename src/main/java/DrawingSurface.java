import processing.core.PApplet;

import java.awt.*;
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
            for (int i = 0, linesSize = lines.size(); i < linesSize; i++) {
                ArrayList<Line> level = lines.get(i);
                for (Line l : level) {
                    float percent = i / (float) linesSize;

                    stroke(lerpColor(new Color(109, 56, 23).getRGB(), new Color(34, 109, 23).getRGB(), percent));
                    strokeWeight(linesSize - i);
                    Point end = l.getEnd();
                    line(l.startx, l.starty, end.x, end.y);
                    if(i == linesSize-1 && l.hasFlower){
                           drawFlower(end.x, end.y);
                    }
                }
            }
        }

    }
    public void drawFlower(int x, int y){
        pushMatrix();
        translate(x, y);
        stroke(0);
        fill(255,255,255);
        ellipse(0,0, 5, 5);
        fill(255, 192, 203);
        for (int i = 0; i < 5; i++) {
            ellipse(0, -5, 4, 5);
            rotate(radians(72));
        }
        popMatrix();
    }

    @Override
    public void mousePressed() {
        trees.add(new Tree(mouseX));
        if (trees.size() > 3) {
            trees.remove(0);
        }
    }
}
