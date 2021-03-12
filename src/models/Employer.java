package models;

public class Employer {
    private String name;
    private String businessArea;
    private int employeesNumber;
    private String logo;

    public Employer(String name, String businessArea, int employeesNumber, String logo) {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
