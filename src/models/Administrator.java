package models;

import javafx.collections.ObservableList;

public class Administrator extends Programmer {
    private String preferredPlatform;

    public Administrator(
            String name,
            double dayPrice,
            int yearsExperience,
            String type,
            ObservableList<String> education,
            ObservableList<String> certificates,
            String device,
            String preferredPlatform
    ) {
        super(name, dayPrice, yearsExperience, type, education, certificates, device);
        this.preferredPlatform = preferredPlatform;
    }

    public String getPreferredPlatform() {
        return preferredPlatform;
    }

    public void setPreferredPlatform(String preferredPlatform) {
        this.preferredPlatform = preferredPlatform;
    }
}
