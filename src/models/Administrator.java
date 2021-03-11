package models;

public class Administrator extends Programmer {
    private String preferredPlatform;

    public Administrator(
            String name,
            double dayPrice,
            int yearsExperience,
            String type,
            String device,
            String preferredPlatform
    ) {
        super(name, dayPrice, yearsExperience, type, device);
        this.preferredPlatform = preferredPlatform;
    }

    public String getPreferredPlatform() {
        return preferredPlatform;
    }

    public void setPreferredPlatform(String preferredPlatform) {
        this.preferredPlatform = preferredPlatform;
    }
}
