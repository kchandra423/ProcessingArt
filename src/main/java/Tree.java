import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Tree {
    private final ArrayList<ArrayList<Line>> lines = new ArrayList<>();
    private final double growthRate;// growth percentage per second
    private int curLevel = 0;
    private double curPercent = 0;
    private final int maxLvl;

    private boolean done = false;

    public Tree(int startx) {
        this.growthRate = 0.3 + Math.random() * 0.2;
        maxLvl = (int) (5 + Math.random() * 5);
        for (int i = 0; i <= maxLvl; i++) {
            lines.add(new ArrayList<Line>());
        }
        drawTreeRecur((int) (100 + Math.random() * 50), startx, 500, -Math.PI / 2, 0);
    }

    public void drawTreeRecur(int length, int startx, int starty, double angle, int level) {
        if (level > maxLvl) {
            return;
        }
        Line curLine = new Line(new Point(startx, starty), length, angle);
        Point cur = curLine.getEnd();
        lines.get(level).add(curLine);
        drawTreeRecur((int) (2 * length / 3 + (Math.random() - 0.5) / 3), cur.x, cur.y, (float) (angle + (Math.PI / 8 + Math.random() * Math.PI / 8)), level + 1);
        drawTreeRecur((int) (2 * length / 3 + (Math.random() - 0.5) / 3), cur.x, cur.y, (float) (angle - (Math.PI / 8 + Math.random() * Math.PI / 8)), level + 1);
    }

    public ArrayList<ArrayList<Line>> getLines() {
        if (done) {
            return lines;
        }
        ArrayList<ArrayList<Line>> answer = new ArrayList<>();
        for (int i = 0; i < curLevel; i++) {
            answer.add(lines.get(i));
        }
        ArrayList<Line> current = new ArrayList<>();
        for (int i = 0; i < lines.get(curLevel).size(); i++) {
            current.add(lines.get(curLevel).get(i).get(curPercent));
        }
        answer.add(current);
        this.curPercent += growthRate;
        if (curPercent > 1) {
            curPercent = 0;
            curLevel++;
        }
        if (curLevel > maxLvl) {
            curLevel = maxLvl;
            done = true;
        }
        return answer;
    }

    public boolean isDone() {
        return done;
    }
}
