package TrainProblemSolver;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    Timer timer;
    int speed;
    boolean stop = true;

    @FXML
    private Button runButton;

    public Controller(){
        speed = 50;
    }

    public class StepTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("тик");
            timer.schedule(new StepTask(), speed*10);
        }
    }

    @FXML
    private void addButtonAction(ActionEvent event) {
        if (stop) {
            runButton.textProperty().setValue("Stop");
            timer = new Timer();
            timer.schedule(new StepTask(), speed*10);
            stop = false;
        } else {
            timer.cancel();
            timer.purge();
            runButton.textProperty().setValue("Run");
            stop = true;
        }
    }

}
