package models;

public class SecurityConsultant extends Specialist {
    private boolean cyberSecurity;

    public SecurityConsultant(String name, double dayPrice, int yearsExperience, String type, boolean cyberSecurity) {
        super(name, dayPrice, yearsExperience, type);
        this.cyberSecurity = cyberSecurity;
    }

    public boolean isCyberSecurity() {
        return cyberSecurity;
    }

    public void setCyberSecurity(boolean cyberSecurity) {
        this.cyberSecurity = cyberSecurity;
    }
}
