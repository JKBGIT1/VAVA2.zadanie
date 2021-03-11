package models;

import javafx.collections.ObservableList;

public class Specialist {
    private String name;
    private double dayPrice;
    private int yearsExperience;
    private String education;
    private ObservableList<String> certificates;

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

    public ObservableList<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(ObservableList<String> certificates) {
        this.certificates = certificates;
    }
}
