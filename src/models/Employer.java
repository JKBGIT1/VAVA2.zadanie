package models;

import javafx.scene.image.Image;

public class Employer {
    private String name;
    private String businessArea;
    private int employeesNumber;
    private Image logo;

    public Employer(String name, String businessArea, int employeesNumber, Image logo) {
        this.name = name;
        this.businessArea = businessArea;
        this.employeesNumber = employeesNumber;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }
}
