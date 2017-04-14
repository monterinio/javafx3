package pl.pwr.measurement.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pl.pwr.measurement.data.ConnectionData;

public final class SaveLoadUtil {
    private SaveLoadUtil() { }
    public static void saveApplicationState(ConnectionData connectionData, String fileName) {
        try (FileOutputStream fs = new FileOutputStream(fileName);
                ObjectOutputStream os = new ObjectOutputStream(fs);) {
            os.writeObject(connectionData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionData loadApplicationState(String fileName) {
        ConnectionData connectionData = null;
        try (FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            connectionData = (ConnectionData) ois.readObject();

        } catch (FileNotFoundException e) {
            connectionData = new ConnectionData();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connectionData;
    }
}
