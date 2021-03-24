package models;

import javafx.collections.ObservableList;

public class HiredRecord {
    private Job job;
    private ObservableList<Specialist> hiredSpecialists;

    public HiredRecord(Job job, ObservableList<Specialist> hiredSpecialists) {
        this.job = job;
        this.hiredSpecialists = hiredSpecialists;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public ObservableList<Specialist> getHiredSpecialists() {
        return hiredSpecialists;
    }

    public void setHiredSpecialists(ObservableList<Specialist> hiredSpecialists) {
        this.hiredSpecialists = hiredSpecialists;
    }

    /**
     * Those getters are for tableView in HiredRecordsScene
     */

    public int getHiredEmployeesNumber() {
        return this.hiredSpecialists.size();
    }
}
