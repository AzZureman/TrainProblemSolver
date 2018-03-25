package Train;

import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class TrainView extends Canvas {

    Train train;
    Color currentCarBackground = Color.web("#448aff");
    Color CarBackground = Color.web("#313b59");
    Color CarOnColor = Color.web("#fed357");
    Color CarOffColor = Color.web("#855df3");
    int lineWidth = 5;

    public TrainView(){
        super();
        train = new Train();
    }

    @Override public boolean isResizable() { return true; }
    @Override public Orientation getContentBias() { return null; }
    @Override public void resize(double width, double height) {
        setWidth(width);
        setHeight(height);
        redraw(width, height);
    }

    public void redraw(double width, double height){
        GraphicsContext gc = this.getGraphicsContext2D();
//        SolidBrush blackBrush = new SolidBrush(Color.Black);
//        SolidBrush yellowBrush = new SolidBrush(Color.Yellow);
//        SolidBrush greenBrush = new SolidBrush(Color.Green);
//        Pen penBlack = new Pen(blackBrush, 2);
//        Pen penGreen = new Pen(greenBrush, 3);
//        double width = this.getWidth();
//        double height = this.getHeight();
        double minSideSize = (width >= height) ? height : width;
        double padding = minSideSize * 0.1;
        double radius = (minSideSize / 2) -  padding;
        double circumference = Math.PI * 2 * radius;


//        g.TranslateTransform(gViewWidth / 2, gViewHeigth / 2);
//        g.SmoothingMode = SmoothingMode.AntiAlias;

        gc.clearRect(0, 0, width, height);
        gc.translate(width / 2, height / 2);
        gc.setStroke(currentCarBackground);
        gc.setLineWidth(lineWidth);

        gc.strokeOval(-radius, -radius, radius*2, radius*2);


        int count = train.CarsCount;
        for (int i = 0; i < count; i++)
        {
            double angel = Math.toRadians(360 * i / count);
            double x = Math.cos(angel) * radius;
            double y = Math.sin(angel) * radius;
            double size = (circumference / count) * 0.7;
            double subSize = size + lineWidth * 2;

            gc.setFill(i == train.CurrentCarNumber ? currentCarBackground : CarBackground);
            gc.fillOval(x - (subSize / 2), y - (subSize / 2), subSize, subSize);

            gc.setFill(train.getCarValue(i) ? CarOnColor : CarBackground);
            gc.fillOval(x - (size / 2), y - (size / 2), size, size);
        }

        gc.applyEffect(new DropShadow(10, 3, 5,  Color.web("#12172b")));

        gc.translate(-width / 2, -height / 2);

    }

}
