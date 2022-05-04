import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

public class DrawingSurface extends PApplet {
    private static int height;

    private static ArrayList<Tree> trees = new ArrayList<>();

    public static int getHeight() {
        return height;
    }

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
                    if (i == t.getMaxLvl() && l.hasFlower) {
                        drawFlower(end.x, end.y, t.getType());
                    }
                }
            }
        }

    }

    public void drawFlower(int x, int y, Tree.ColorScheme scheme) {
        pushMatrix();
        translate(x, y);
        stroke(0);
        if (scheme == Tree.ColorScheme.SPRING) {
            fill(255, 255, 255);
        } else if (scheme == Tree.ColorScheme.FALL) {
            fill(150, 32, 7, 170);
        } else {
            fill(236, 248, 127);
        }
        ellipse(0, 0, 5, 5);

        if (scheme == Tree.ColorScheme.SPRING) {
            fill(255, 192, 203);
        } else if (scheme == Tree.ColorScheme.FALL) {
            fill(254,251,234);
        } else {
            fill(247, 148, 13);
        }
        for (int i = 0; i < 5; i++) {
            ellipse(0, -5, 4, 5);
            rotate(radians(72));
        }
        popMatrix();
    }

    @Override
    public void mousePressed() {
        DrawingSurface.height = this.sketchHeight();
        trees.add(new Tree(mouseX));
        if (trees.size() > 3) {
            trees.remove(0);
        }
    }
}
