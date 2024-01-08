import codedraw.*;
import java.awt.*;

public class Main {
    private static final double dim = 500;

    public static void main(String[] args) {
        CodeDraw cd = new CodeDraw((int) dim, (int) dim);
        cd.setTitle("Blume");
        cd.setColor(Color.RED);

        double h = 150, k = 150, a = 100, b = 60, q = Math.PI / 2 - Math.PI / 6;
        double bFifth = b / 5;

        drawEllipse(cd, h, k, a, b, q, bFifth);

        h = 400;
        a = 90;
        q = Math.PI / 2 + Math.PI / 4;
        drawEllipse(cd, h, k, a, b, q, bFifth);

        cd.setColor(Palette.DARK_OLIVE_GREEN);
        for(double x = 251; x <= 335; x+=0.05) {
            double y = 70 * Math.log(x-250)-30;
            cd.fillCircle(x, 500-y, 2);
        }
        for(double x = 251; x <= 295; x+=0.05) {
            double y = 70 * Math.log(x-250)-5;
            cd.fillCircle(500-x, 500-y, 2);
        }

        cd.show();
    }

    private static void drawEllipse(CodeDraw cd, double h, double k, double a, double b, double q, double bFifth) {
        for(int i = 0; i <= 5; i++) {
            for (double t = 0; t <= Math.PI * 2; t += Math.PI / 40) {
                double x = a * Math.cos(t) + h;
                double y = b * Math.sin(t) + k;

                double xNew = (x - h) * Math.cos(q) - (y - k) * Math.sin(q) + h;
                double yNew = (x - h) * Math.sin(q) + (y - k) * Math.cos(q) + k;

                cd.fillCircle(xNew, yNew, 3);
            }
            b -= bFifth;
        }
    }
}