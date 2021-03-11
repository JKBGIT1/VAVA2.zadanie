package models;

public class Programmer extends Specialist {
    private String device;

    public Programmer(String name, double dayPrice, int yearsExperience, String type, String device) {
        super(name, dayPrice, yearsExperience, type);
        this.device = device;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
