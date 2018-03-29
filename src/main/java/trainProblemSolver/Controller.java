package trainProblemSolver;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.Pair;
import solvers.ISolver;
import solvers.Solver;
import train.TrainView;


public class Controller {

    private ISolver solver;
    private Timeline timeline;

    @FXML
    protected ComboBox solverSelector;

    @FXML
    private Slider speedSelector;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 30;
    private final int DEFAULT_SPEED = 10;

    @FXML
    protected Button runButton;
    private boolean run = false;

    @FXML
    private AnchorPane TrainViewContainer;
    private final TrainView trainView;

    @FXML
    private TableView paramTable;
    @FXML
    private TableColumn<Pair,String> paramNameCol;
    @FXML
    private TableColumn<Pair,String> paramValueCol;


    public Controller() {
        trainView = new TrainView();
        timeline = new Timeline();
    }

    @FXML
    private void initialize() {

        solverSelector.setItems(FXCollections.observableArrayList(Solver.getSolversNames()));
        solverSelector.setOnAction(this::onSolverSelected);

        speedSelector.setValue(DEFAULT_SPEED);
        speedSelector.setMax(MAX_SPEED);
        speedSelector.setMin(MIN_SPEED);

        runButton.setDisable(true);
        runButton.setOnAction(this::onRunButtonClick);

        AnchorPane.setTopAnchor(trainView, 5.0);
        AnchorPane.setLeftAnchor(trainView, 5.0);
        AnchorPane.setBottomAnchor(trainView, 5.0);
        AnchorPane.setRightAnchor(trainView, 5.0);
        TrainViewContainer.getChildren().add(trainView);

        paramNameCol.setCellValueFactory(new PropertyValueFactory<>("Key"));
        paramValueCol.setCellValueFactory(new PropertyValueFactory<>("Value"));

        timeline.setOnFinished(e -> setTimer());

    }

    private void onSolverSelected(Event event) {
        solver = Solver.getSolverByName((String)solverSelector.getValue());
        solver.setTrain(trainView.getTrian());
        paramTable.setItems(FXCollections.observableArrayList(solver.getState()));
        runButton.setDisable(false);
    }

    private void onRunButtonClick(Event event) {
        if (!run) start();
        else stop();
    }

    private void start() {
        runButton.textProperty().setValue("Stop");
        solverSelector.setDisable(true);
        run = true;
        setTimer();
    }

    private void stop() {
        runButton.textProperty().setValue("Run");
        solverSelector.setDisable(false);
        run = false;
        timeline.stop();
    }

    private void setTimer() {
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1/speedSelector.getValue()),null));
        timeline.play();
        step();
    }

    private void step() {
        if(solver.step() > 0) stop();
        trainView.redraw();
        paramTable.setItems(FXCollections.observableArrayList(solver.getState()));
    }


}
