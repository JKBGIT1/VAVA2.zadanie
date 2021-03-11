package models;

public class Administrator extends Programmer {
    private String preferedPlatform;

    public String getPreferedPlatform() {
        return preferedPlatform;
    }

    public void setPreferedPlatform(String preferedPlatform) {
        this.preferedPlatform = preferedPlatform;
    }
}
