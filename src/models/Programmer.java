package models;

import javafx.collections.ObservableList;

public class Programmer extends Specialist {
    private String device;

    public Programmer(
            String name,
            double dayPrice,
            int yearsExperience,
            String type,
            String education,
            ObservableList<String> certificates,
            String device
    ) {
        super(name, dayPrice, yearsExperience, type, education, certificates);
        this.device = device;
    }


    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
