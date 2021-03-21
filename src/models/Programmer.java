package models;

import javafx.collections.ObservableList;

public class Programmer extends Specialist {
    private String position;

    public Programmer(
            String name,
            double dayPrice,
            int yearsExperience,
            String type,
            String education,
            ObservableList<String> certificates,
            String position
    ) {
        super(name, dayPrice, yearsExperience, type, education, certificates);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
