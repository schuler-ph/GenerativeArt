import codedraw.*;
import codedraw.Image;

import java.awt.*;

public class RotationXYZ {
    private static final double dim = 500;
    public static void main(String[] args) {
        CodeDraw cd = new CodeDraw((int) dim, (int) dim);

        double a = 1, b = 1, c = 1;
        double offsetX = dim / 2;
        double offsetY = dim / 2;
        double stretch = 30;
        int imageIndex = 0;

        cd.setColor(Palette.WHITE);
        cd.fillRectangle(0, 0, dim, dim);

        double qDelta = 0;
        double tDelta = 0.01;
        for(int i = 0; i <= 10; i++) {
            if (i != 10){
                cd.setColor(new Color(150 + i * 20));
                qDelta += 0.01;
            }
            else{
                cd.setColor(Palette.WHITE);
                qDelta += 0.5;
                tDelta += 0.1;
            }
            for(double q = 0; q <= Math.PI * 2; q += qDelta) {
                for(double t = -Math.PI * 2; t <= Math.PI * 2; t += tDelta) {
                    double x3 = stretch * Math.cos(t);
                    double y3 = stretch * Math.sin(t);
                    double z3 = 0;

                    double v = a * x3 + b * y3 + c * z3;
                    double alpha = x3 * Math.cos(q) + (b * z3 - c * y3) * Math.sin(q) + a* v *(1 - Math.cos(q));
                    double beta = y3 * Math.cos(q) + (c * x3 - a * z3) * Math.sin(q) + b* v *(1 - Math.cos(q));
                    double gamma = z3 * Math.cos(q) + (a * y3 - b * x3) * Math.sin(q) + c* v *(1 - Math.cos(q));

                    double x2 = (30 * alpha) / (30 - gamma);
                    double y2 = (30 * beta) / (30 - gamma);
                    cd.drawPoint(x2 + offsetX, y2 + offsetY);
                }
                cd.show();
                if(imageIndex % 10 == 0) {
                    if(imageIndex < 10) Image.save(cd, "img/image000" + imageIndex + ".png", ImageFormat.PNG);
                    else if (imageIndex < 100) Image.save(cd, "img/image00" + imageIndex + ".png", ImageFormat.PNG);
                    else if (imageIndex < 1000) Image.save(cd, "img/image0" + imageIndex + ".png", ImageFormat.PNG);
                    else Image.save(cd, "img/image" + imageIndex + ".png", ImageFormat.PNG);
                }
                imageIndex++;
            }
        }
        Image.save(cd, "img/image" + imageIndex + ".png", ImageFormat.PNG);
    }
}
