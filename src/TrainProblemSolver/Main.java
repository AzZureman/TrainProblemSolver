package TrainProblemSolver;

import Train.TrainView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TrainProblemSolver.fxml"));
        Parent root = fxmlLoader.load();


        AnchorPane anchorPane = (AnchorPane)fxmlLoader.getNamespace().get("TrainViewContainer");
        TrainView trainView = new TrainView();
        AnchorPane.setTopAnchor(trainView, 5.0);
        AnchorPane.setLeftAnchor(trainView, 5.0);
        AnchorPane.setBottomAnchor(trainView, 5.0);
        AnchorPane.setRightAnchor(trainView, 5.0);
        anchorPane.getChildren().add(trainView);


        ObservableList<String> options = FXCollections.observableArrayList("1", "2", "3" );
        ComboBox comboBox = (ComboBox)fxmlLoader.getNamespace().get("solverSelector");
        comboBox.setItems(options);

        //Scene scene = new Scene(root, 300, 275);
        //scene.getStylesheets().add(getClass().getResource("TrainProblemSolver.css").toExternalForm());

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
