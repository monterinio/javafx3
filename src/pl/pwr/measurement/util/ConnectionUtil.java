package pl.pwr.measurement.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import pl.pwr.measurement.data.ConnectionData;
import pl.pwr.measurement.data.Data;
import pl.pwr.measurement.data.Strings;

public class ConnectionUtil {
    private ConnectionUtil() {  }
    public static void run(ConnectionData connectionData, Data data) {
        try (
            Socket socket = new Socket(connectionData.getServerIP(), Strings.PORT_NUMBER);
            OutputStream outputStream = socket.getOutputStream();
                ) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
