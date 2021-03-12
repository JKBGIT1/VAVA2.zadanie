package models;

import javafx.collections.ObservableList;

public class HiredRecord {
    private Employer employer;
    private ObservableList<Specialist> hiredSpecialists;

    public HiredRecord(Employer employer, ObservableList<Specialist> hiredSpecialists) {
        this.employer = employer;
        this.hiredSpecialists = hiredSpecialists;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public ObservableList<Specialist> getHiredSpecialists() {
        return hiredSpecialists;
    }

    public void setHiredSpecialists(ObservableList<Specialist> hiredSpecialists) {
        this.hiredSpecialists = hiredSpecialists;
    }
}
