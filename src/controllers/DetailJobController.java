package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailJobController extends HomepageController implements Initializable {

    @FXML
    private Label labelJobName, labelType, labelPrice, labelEmployerName,
                    labelExperience, labelHighestEducation, labelJobDescription;

    private Job selectedJob;

    public DetailJobController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList,
            Job selectedJob
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
        this.selectedJob = selectedJob;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelJobName.setText(this.selectedJob.getJobName());
        labelType.setText(this.selectedJob.getSpecialistType());
        labelPrice.setText(String.valueOf(this.selectedJob.getPrice()));
        labelEmployerName.setText(this.selectedJob.getEmployerName());
        labelExperience.setText(String.valueOf(this.selectedJob.getExperience()));
        labelHighestEducation.setText(this.selectedJob.getHighestEducation());
        labelJobDescription.setText(this.selectedJob.getJobDescription());
    }
}
