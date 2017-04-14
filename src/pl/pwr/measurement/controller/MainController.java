package pl.pwr.measurement.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import pl.pwr.measurement.data.ConnectionData;
import pl.pwr.measurement.data.Data;
import pl.pwr.measurement.data.Strings;
import pl.pwr.measurement.util.ConnectionUtil;
import pl.pwr.measurement.util.GenerateDataUtil;
import pl.pwr.measurement.util.SaveLoadUtil;
import pl.pwr.measurement.util.WindowUtil;

public class MainController implements Initializable {

    private Data data;
    private ConnectionData connectionData;

    @FXML
    private MenuItem connectionSettingsItem;
    @FXML
    private MenuItem closeItem;
    @FXML
    private MenuItem aboutItem;
    @FXML
    private Button startMeasurement;
    @FXML
    private Button stopMeasurement;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Slider currentSlider;
    @FXML
    private Slider pressureSlider;

    public MainController() {
        data = new Data();
        connectionData = SaveLoadUtil.loadApplicationState(Strings.FILE_NAME);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSliders();
        configureMenuItems();
        initializeButtons();
        startMeasurementButton();
        stopMeasurementButton();
    }

    private void initializeSliders() {
        initializeSlider(currentSlider,  data.getCurrent());
        initializeSlider(pressureSlider, data.getPressure());
    }

    private void initializeSlider(Slider slider, SimpleDoubleProperty data) {
        slider.setDisable(true);
        slider.valueProperty().bind(data);
    }

    private void configureMenuItems() {
        connectionSettingsItem.setOnAction(x -> WindowUtil.loadWindowAndSendData(Strings.CONNECTION_LAYOUT_NAME,
                Strings.CONNECTION_SETTINGS_ITEM_NAME, connectionData));
        aboutItem.setOnAction(x -> WindowUtil.loadWindow(Strings.ABOUT_LAYOUT_NAME, Strings.ABOUT_ITEM_NAME));
        closeItem.setOnAction(x -> WindowUtil.loadWindow(Strings.EXIT_LAYOUT_NAME, Strings.EXIT_ITEM_NAME));
    }

    private void initializeButtons() {
        startMeasurementButton();
        stopMeasurementButton();
    }

    private void startMeasurementButton() {
        startMeasurement.setOnAction(x -> {
            progressBar.progressProperty().bind(startMeasurementDevicesService.progressProperty());
            progressIndicator.progressProperty().bind(startMeasurementDevicesService.progressProperty());
            startMeasurementDevicesService.start();

            startMeasurementDevicesService.setOnSucceeded(e -> {
                currentSlider.setDisable(startMeasurementDevicesService.getValue());
                pressureSlider.setDisable(startMeasurementDevicesService.getValue());
                startMeasurementDataService.start();
            });

        });
    }

    private void stopMeasurementButton() {
        stopMeasurement.setOnAction(x-> {
            startMeasurementDevicesService.cancel();
            startMeasurementDevicesService.reset();

            startMeasurementDataService.cancel();
            startMeasurementDataService.reset();

            currentSlider.setDisable(true);
            pressureSlider.setDisable(true);
        });
    }

    //klasa anonimowa
    Service<Boolean> startMeasurementDevicesService = new Service<Boolean>() {
        @Override
        protected Task<Boolean> createTask() {
            return new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    double max = 100;
                    for (int i = 0; i <= max; i++) {
                        if(isCancelled()) {
                            break;
                        }
                        updateProgress(i, max);
                        Thread.sleep(1);
                    }
                    return false;
                }
            };
        }
    };

    Service<Boolean> startMeasurementDataService = new Service<Boolean>() {
        @Override
        protected Task<Boolean> createTask() {
            return new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    while(true) {
                        if(isCancelled()) {
                            break;
                        }
                        generateMeasurementData();
                        ConnectionUtil.run(connectionData, data);
                        System.out.println(data.getCurrent());
                        Thread.sleep(2000);
                    }
                    return true;
                }
            };
        }
    };

    private void generateMeasurementData() {
        data.setCurrent(GenerateDataUtil.generateCurrent());
        data.setPressure(GenerateDataUtil.generatePressure());
    }

    public ConnectionData getConnectionData() {
        return connectionData;
    }
}
