package pl.pwr.measurement.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.pwr.measurement.data.Strings;
import pl.pwr.measurement.util.WindowUtil;

public class IndustrialMeasurement extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource(Strings.MAIN_LAYOUT_NAME));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle(Strings.APP_NAME);
        primaryStage.show();
        primaryStage.setOnCloseRequest(x -> {
            x.consume();
            WindowUtil.loadWindow(Strings.EXIT_LAYOUT_NAME, Strings.EXIT_ITEM_NAME);
        });
    }
}
