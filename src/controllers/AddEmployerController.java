package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

public class AddEmployerController extends HomepageController {

    @FXML
    private TextArea taBusinessArea;
    @FXML
    private TextField tfName, tfNumberOfEmployees, tfLogo;

    public AddEmployerController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
    }

    public void createEmployer(MouseEvent event) {
        try {
            String name = tfName.getText();
            String businessArea = taBusinessArea.getText();
            String logo = tfLogo.getText();
            int numberOfEmployees = Integer.parseInt(tfNumberOfEmployees.getText());

            // add newly created Employer to the list of all Employers
            this.getEmployerObservableList().add(new Employer(name, businessArea, numberOfEmployees, logo));

            this.employersScene(event);
        } catch (Exception e) {
            this.showErrorPopUp("Input error", "Number of employees has to be not decimal number.");
            e.printStackTrace();
        }
    }
}
