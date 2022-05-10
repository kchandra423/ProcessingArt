import java.awt.*;
import java.util.ArrayList;

public class Tree {
    public ColorScheme getType() {
        return type;
    }

    public enum ColorScheme {
        FALL, WINTER, SPRING;

        public static ColorScheme getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    private final ColorScheme type;
    private final ArrayList<ArrayList<Line>> lines = new ArrayList<>();
    private final double growthRate;// growth percentage per second
    private int curLevel = 0;
    private double curPercent = 0;
    private final int maxLvl;

    private boolean done = false;

    public Tree(int startx) {
        type = ColorScheme.getRandom();
        this.growthRate = 0.3 + Math.random() * 0.3;
        maxLvl = (int) (5 + Math.random() * 5);
        for (int i = 0; i <= maxLvl; i++) {
            lines.add(new ArrayList<Line>());
        }
        drawTreeRecur((int) (100 + Math.random() * 100), startx, DrawingSurface.getHeight() - 100, -Math.PI / 2  + (float)((Math.random()-0.5) * Math.PI / 40), 0);
    }

    public void drawTreeRecur(int length, int startx, int starty, double angle, int level) {
        if (level > maxLvl) {
            return;
        }
        Line curLine = new Line(new Point(startx, starty), length, angle);
        Point cur = curLine.getEnd();
        lines.get(level).add(curLine);
        drawTreeRecur((int) (2 * length / 3 + (Math.random() - 0.5) / 3), cur.x, cur.y, (float) (angle + (Math.PI / 8 + Math.random() * Math.PI / 5 * (float) level / maxLvl)), level + 1);
        drawTreeRecur((int) (2 * length / 3 + (Math.random() - 0.5) / 3), cur.x, cur.y, (float) (angle - (Math.PI / 8 + Math.random() * Math.PI / 5 * (float) level / maxLvl)), level + 1);
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

    public int getMaxLvl() {
        return maxLvl;
    }

    public boolean isDone() {
        return done;
    }
}
