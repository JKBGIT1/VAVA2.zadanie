package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateJobController extends HomepageController implements Initializable {

    @FXML
    private ComboBox<String> specialistTypeComboBox = new ComboBox<String>();
    @FXML
    private ComboBox<String> highestEducationComboBox = new ComboBox<String>();
    @FXML
    private TextField tfName, tfPrice, tfExperience;
    @FXML
    private TextArea taJobDescription;

    private Employer selectedEmployer;

    public CreateJobController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList,
            Employer selectedEmployer
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
        this.selectedEmployer = selectedEmployer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set items for specialist type to ComboBox
        specialistTypeComboBox.setItems(FXCollections.observableArrayList(
                "Programmer", "Administrator", "Security consultant"
        ));
        // set items for specialist highest education to ComboBox
        highestEducationComboBox.setItems(FXCollections.observableArrayList(
                "Grade school", "High school", "University"
        ));
    }

    public void createJob(MouseEvent event) {
        try {
            // get job's information from TextFields and ComboBoxes
            String jobName = tfName.getText();
            double price = Double.parseDouble(tfPrice.getText());
            int experience = Integer.parseInt(tfExperience.getText());
            String jobDescription = taJobDescription.getText();
            String highestEducation = highestEducationComboBox.getSelectionModel().getSelectedItem();
            String specialistType = specialistTypeComboBox.getSelectionModel().getSelectedItem();

            // add newly created job to list with all jobs
            this.getJobsObservableList().add(new Job(
                    this.selectedEmployer.getName(),
                    jobName,
                    specialistType,
                    experience,
                    price,
                    highestEducation,
                    jobDescription
            ));

            // go back to employers scene
            this.employersScene(event);
        } catch (Exception e) {
            this.showErrorPopUp("Wrong input", "Price and experience has to be number.");
            System.out.println(e.getMessage());
        }
    }
}
