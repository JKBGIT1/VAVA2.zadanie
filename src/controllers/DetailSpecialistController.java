package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailSpecialistController extends HomepageController implements Initializable {

    @FXML
    private Label labelName, labelPrice, labelExperience, labelType, labelHighestEducation,
                        labelPosition, labelPreferredPlatform, labelCyberSecurity, labelHired;
    @FXML
    private ListView<String> certificatesListView;

    private final Specialist selectedSpecialist;

    public DetailSpecialistController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList,
            Specialist specialist
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
        this.selectedSpecialist = specialist;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Firstly set generic information for each specialist
        labelName.setText(this.selectedSpecialist.getName());
        labelPrice.setText(String.valueOf(this.selectedSpecialist.getDayPrice()));
        labelExperience.setText(String.valueOf(this.selectedSpecialist.getYearsExperience()));
        labelType.setText(this.selectedSpecialist.getType());
        labelHighestEducation.setText(this.selectedSpecialist.getEducation());
        labelHired.setText(this.selectedSpecialist.isHired() ? "Yes" : "No");
        labelPosition.setText("None");
        labelPreferredPlatform.setText("None");
        labelCyberSecurity.setText("None");

        // Display specialists certificates in listView
        certificatesListView.setItems(this.selectedSpecialist.getCertificates());

        // Now fill information based on specialist type
        switch (this.selectedSpecialist.getType()) {
            case "Programmer":
                // Firstly change specialist type to Programmer then set device
                labelPosition.setText(((Programmer) this.selectedSpecialist).getPosition());
                break;
            case "Administrator":
                labelPosition.setText(((Administrator) this.selectedSpecialist).getPosition());
                labelPreferredPlatform.setText(((Administrator) this.selectedSpecialist).getPreferredPlatform());
                break;
            case "Security consultant":
                String cyberSecurity = ((SecurityConsultant) this.selectedSpecialist).isCyberSecurity() ? "Yes" : "No";
                labelCyberSecurity.setText(cyberSecurity);
                break;
        }
    }
}
