package models;

import javafx.collections.ObservableList;

public class Specialist {
    private String name;
    private double dayPrice;
    private int yearsExperience;
    private String education;
    private String type;
    private boolean hired;
    private ObservableList<String> certificates;

    public Specialist(String name, double dayPrice, int yearsExperience, String type) {
        this.name = name;
        this.dayPrice = dayPrice;
        this.yearsExperience = yearsExperience;
        this.type = type;
        this.hired = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(double dayPrice) {
        this.dayPrice = dayPrice;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHired() {
        return hired;
    }

    public void setHired(boolean hired) {
        this.hired = hired;
    }

    public ObservableList<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(ObservableList<String> certificates) {
        this.certificates = certificates;
    }
}
