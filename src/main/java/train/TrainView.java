package train;

import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class TrainView extends Canvas {

    private Train train;
    private final Color currentCarBackground = Color.web("#448aff");
    private final Color CarBackground = Color.web("#313b59");
    private final Color CarOnColor = Color.web("#fed357");
    private final Color CarOffColor = Color.web("#855df3");
    private final int LINE_WIDTH = 5;

    public TrainView(){
        super();
        train = new Train();
    }

    @Override public boolean isResizable() { return true; }
    @Override public Orientation getContentBias() { return null; }
    @Override public void resize(double width, double height) {
        setWidth(width);
        setHeight(height);
        redraw_(width, height);
    }

    public void resetTrain() {
        this.train = new Train();
    }

    public Train getTrian() {
        return this.train;
    }

    public void redraw() {
        redraw_(this.getWidth(), this.getHeight());
    }

    private void redraw_(double width, double height){
        GraphicsContext gc = this.getGraphicsContext2D();

        double minSideSize = (width >= height) ? height : width;
        double padding = minSideSize * 0.1;
        double radius = (minSideSize / 2) -  padding;
        double circumference = Math.PI * 2 * radius;

        gc.clearRect(0, 0, width, height);
        gc.translate(width / 2, height / 2);
        gc.setStroke(currentCarBackground);
        gc.setLineWidth(LINE_WIDTH);

        gc.strokeOval(-radius, -radius, radius*2, radius*2);

        int count = train.getCarsCount();
        Train.Car car = train.getHeadCar();
        for (int i = 0; i < count; i++)
        {
            double angel = Math.toRadians(360 * i / count);
            double x = Math.cos(angel) * radius;
            double y = Math.sin(angel) * radius;
            double size = (circumference / count) * 0.7;
            double subSize = size + LINE_WIDTH * 2;

            gc.setFill(car == train.getCurrentCar() ? currentCarBackground : CarBackground);
            gc.fillOval(x - (subSize / 2), y - (subSize / 2), subSize, subSize);

            gc.setFill(car.getCarBooleanState() ? CarOnColor : CarBackground);
            gc.fillOval(x - (size / 2), y - (size / 2), size, size);

            car = car.getNextCar();
        }

        gc.applyEffect(new DropShadow(10, 3, 5,  Color.web("#12172b")));

        gc.translate(-width / 2, -height / 2);

    }

}
