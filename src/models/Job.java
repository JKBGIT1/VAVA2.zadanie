package models;

public class Job {
    private String employerName;
    private String jobName;
    private String specialistType;
    private int experience;
    private double price;
    private String highestEducation;
    private String jobDescription;

    public Job(String employerName, String jobName, String specialistType, int experience, double price, String highestEducation, String jobDescription) {
        this.employerName = employerName;
        this.jobName = jobName;
        this.specialistType = specialistType;
        this.experience = experience;
        this.price = price;
        this.highestEducation = highestEducation;
        this.jobDescription = jobDescription;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSpecialistType() {
        return specialistType;
    }

    public void setSpecialistType(String specialistType) {
        this.specialistType = specialistType;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
