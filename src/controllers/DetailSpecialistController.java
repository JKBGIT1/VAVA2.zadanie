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
    private Label tfName, tfPrice, tfExperience, tfType, tfHighestEducation,
                        tfDevice, tfPreferredPlatform, tfCyberSecurity;
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
        tfName.setText(this.selectedSpecialist.getName());
        tfPrice.setText(String.valueOf(this.selectedSpecialist.getDayPrice()));
        tfExperience.setText(String.valueOf(this.selectedSpecialist.getYearsExperience()));
        tfType.setText(this.selectedSpecialist.getType());
        tfHighestEducation.setText(this.selectedSpecialist.getEducation());
        tfDevice.setText("None");
        tfPreferredPlatform.setText("None");
        tfCyberSecurity.setText("None");

        // Display specialists certificates in listView
        certificatesListView.setItems(this.selectedSpecialist.getCertificates());

        // Now fill information based on specialist type
        switch (this.selectedSpecialist.getType()) {
            case "Programmer":
                // Firstly change specialist type to Programmer then set device
                tfDevice.setText(((Programmer) this.selectedSpecialist).getDevice());
                break;
            case "Administrator":
                tfDevice.setText(((Administrator) this.selectedSpecialist).getDevice());
                tfPreferredPlatform.setText(((Administrator) this.selectedSpecialist).getPreferredPlatform());
                break;
            case "Security consultant":
                String cyberSecurity = ((SecurityConsultant) this.selectedSpecialist).isCyberSecurity() ? "Yes" : "No";
                tfCyberSecurity.setText(cyberSecurity);
                break;
        }
    }
}
