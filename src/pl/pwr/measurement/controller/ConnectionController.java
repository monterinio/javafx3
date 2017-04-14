package pl.pwr.measurement.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.pwr.measurement.util.RegexUtil;
import pl.pwr.measurement.util.SaveLoadUtil;
import pl.pwr.measurement.util.WindowUtil;
import pl.pwr.measurement.data.ConnectionData;
import pl.pwr.measurement.data.Strings;

public class ConnectionController implements Initializable, ConnectionDataProvider {

    private ConnectionData connectionData;

    @FXML
    private Button cancel;

    @FXML
    private Button saveAndExit;

    @FXML
    private TextField serverIP;

    @FXML
    private TextField coComputerIP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveAndExit.setOnAction(x -> {
            if (RegexUtil.checkIP(serverIP.getText())) {
                connectionData.setServerIP(serverIP.getText());
                connectionData.setCoComputerIP(coComputerIP.getText());
                SaveLoadUtil.saveApplicationState(getConnectionData(), Strings.FILE_NAME);
                ((Stage) saveAndExit.getScene().getWindow()).close();
            } else {
                WindowUtil.loadWindow(Strings.IP_ERROR_LAYOUT_NAME, Strings.IP_ERROR_ITEM_NAME);
            }
        });
        cancel.setOnAction(x -> ((Stage) cancel.getScene().getWindow()).close());
    }

    public void getConnectionData(ConnectionData connectionData) {
        setConnectionData(connectionData);
        serverIP.setText(connectionData.getServerIP());
        coComputerIP.setText(connectionData.getCoComputerIP());
    }

    public ConnectionData getConnectionData() {
        return connectionData;
    }

    public void setConnectionData(ConnectionData connectionData) {
        this.connectionData = connectionData;
    }

}
