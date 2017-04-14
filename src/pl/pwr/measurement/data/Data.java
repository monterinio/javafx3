package pl.pwr.measurement.data;

import javafx.beans.property.SimpleDoubleProperty;

public class Data {

    private SimpleDoubleProperty pressure;
    private SimpleDoubleProperty current;

    public SimpleDoubleProperty getCurrent() {
        return current;
    }
    public void setCurrent(double current) {
        this.current.set(current);
    }
    public SimpleDoubleProperty getPressure() {
        return pressure;
    }
    public void setPressure(double pressure) {
        this.pressure.set(pressure);
    }

    public Data() {
        pressure = new SimpleDoubleProperty(0);
        current = new SimpleDoubleProperty(0);
    }
}
