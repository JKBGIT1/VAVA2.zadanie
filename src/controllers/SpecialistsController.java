package controllers;

import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

public class SpecialistsController extends HomepageController {
    public SpecialistsController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
    }

    public void addSpecialistScene(MouseEvent event) {
        this.setScenePath(ADD_SPECIALIST_SCENE);
        this.setController(new AddSpecialistController(
                this.getSpecialistObservableList(),
                this.getEmployerObservableList(),
                this.getJobsObservableList(),
                this.getHiringRecordObservableList(),
                null
        ));
        this.switchScene(event);
    }

    public void detailSpecialistScene() {

    }

    public void hireSpecialistScene() {

    }
}
