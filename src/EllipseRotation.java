import codedraw.*;

public class Main {
    private static int width = 650;
    private static int height = 450;
    private static CodeDraw cd;
    private static double zoom = 20;
    public static void main(String[] args) {
        cd = new CodeDraw(width, height);

        double cameraZPos = 30;

        double elXTranslate = 5, elYTranslate = 5;
        double elWidth = 20, elHeight = 2;
        double elQ = Math.PI / 4;

        double rotAxX = 1;
        double rotAxY = 1;
        double rotAxZ = 0;

        double normalizing = Math.sqrt(Math.pow(rotAxX, 2) + Math.pow(rotAxY, 2) + Math.pow(rotAxZ, 2));

        double rotationAxisX = rotAxX / normalizing;
        double rotationAxisY = rotAxY / normalizing;
        double rotationAxisZ = rotAxZ / normalizing;

        double tDelta = 0.01;

        for(int i = -20; i <= 20; i++) {for(int j = -12; j <= 12; j++) {if(i == 0 || j == 0) drawPoint(i, j, 1);}}

        for(double rotationQ = 0; rotationQ < Math.PI * 1; rotationQ += 0.1) {
            //cd.clear();

            for (double t = 0; t <= Math.PI * 2; t += tDelta) {
                double x = elWidth * Math.cos(t) + elXTranslate;
                double y = elHeight * Math.sin(t) + elYTranslate;

                double xNew = (x - elXTranslate) * Math.cos(elQ) - (y - elYTranslate) * Math.sin(elQ) + elXTranslate;
                double yNew = (x - elXTranslate) * Math.sin(elQ) + (y - elYTranslate) * Math.cos(elQ) + elYTranslate;
                double zNew = 0;

                double v = rotationAxisX * xNew + rotationAxisY * yNew + rotationAxisZ * zNew;
                double alpha = xNew * Math.cos(rotationQ) + (rotationAxisY * zNew - rotationAxisZ * yNew) * Math.sin(rotationQ) + rotationAxisX* v *(1 - Math.cos(rotationQ));
                double beta = yNew * Math.cos(rotationQ) + (rotationAxisZ * xNew - rotationAxisX * zNew) * Math.sin(rotationQ) + rotationAxisY* v *(1 - Math.cos(rotationQ));
                double gamma = zNew * Math.cos(rotationQ) + (rotationAxisX * yNew - rotationAxisY * xNew) * Math.sin(rotationQ) + rotationAxisZ* v *(1 - Math.cos(rotationQ));

                double x2D = (cameraZPos * alpha) / (cameraZPos - gamma);
                double y2D = (cameraZPos * beta) / (cameraZPos - gamma);

                drawPoint(x2D, y2D, 2);
            }

            cd.show(100);
        }
    }

    private static void drawPoint(double x, double y, double r) {
        cd.fillCircle(width / 2. + zoom * x, height / 2. - zoom * y, r);
    }
}
