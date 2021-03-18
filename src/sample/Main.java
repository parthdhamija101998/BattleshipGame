package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Controller.Controller;
import sample.Model.Model;
import sample.View.View;

import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        View view = new View();
        Model model = new Model();
        Controller controller =new Controller();
        controller.create(primaryStage,model,view, controller);
        controller.generateShipLocations();
    }

    public static void main(String[] args) {
        launch(args);
    }
}