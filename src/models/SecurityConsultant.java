package models;

import javafx.collections.ObservableList;

public class SecurityConsultant extends Specialist {
    private boolean cyberSecurity;

    public SecurityConsultant(
            String name,
            double dayPrice,
            int yearsExperience,
            String type,
            String education,
            ObservableList<String> certificates,
            boolean cyberSecurity
    ) {
        super(name, dayPrice, yearsExperience, type, education, certificates);
        this.cyberSecurity = cyberSecurity;
    }

    public boolean isCyberSecurity() {
        return cyberSecurity;
    }

    public void setCyberSecurity(boolean cyberSecurity) {
        this.cyberSecurity = cyberSecurity;
    }
}
