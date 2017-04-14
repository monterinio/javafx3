package pl.pwr.measurement.data;

import java.io.Serializable;

public class ConnectionData implements Serializable {

    private static final long serialVersionUID = -2278811223419907722L;
    private String serverIP;
    private String coComputerIP;

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getCoComputerIP() {
        return coComputerIP;
    }

    public void setCoComputerIP(String coComputerIP) {
        this.coComputerIP = coComputerIP;
    }
}
