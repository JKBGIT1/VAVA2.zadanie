package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSpecialistController extends HomepageController implements Initializable {
    public static final String PROGRAMMER = "Programmer";
    public static final String ADMINISTRATOR = "Administrator";
    public static final String SECURITY_CONSULTANT = "Security consultant";

    @FXML
    ComboBox<String> specialistTypeComboBox = new ComboBox<String>();
    @FXML
    ComboBox<String> deviceComboBox = new ComboBox<String>();
    @FXML
    ComboBox<String> securityConsultantComboBox = new ComboBox<String>();
    @FXML
    TextField tfPreferredPlatform;

    private Specialist specialist;

    public AddSpecialistController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList,
            Specialist specialist
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
        this.specialist = specialist;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (this.getSpecialist() != null) {
            
        }

        specialistTypeComboBox.setItems(FXCollections.observableArrayList(
                "Programmer", "Administrator", "Security consultant"
        ));
    }

    public void finishAddSpecialist(MouseEvent event) {
        this.setScenePath(FINISH_ADD_SPECIALIST_SCENE);
        this.setController(new FinishAddSpecialistController(
                this.getSpecialistObservableList(),
                this.getEmployerObservableList(),
                this.getJobsObservableList(),
                this.getHiringRecordObservableList(),
                this.getSpecialist()
        ));
        this.switchScene(event);
    }

    public void getSelectedSpecialist() {
        String selectedSpecialitst = specialistTypeComboBox.getSelectionModel().getSelectedItem();

        switch (selectedSpecialitst) {
            case PROGRAMMER:
                setProgrammerComboBoxes();
                break;
            case ADMINISTRATOR:
                setAdministratorComboBoxes();
                break;
            case SECURITY_CONSULTANT:
                setSecurityConsultantComboBoxes();
                break;
        }
    }

    private void setProgrammerComboBoxes() {
        tfPreferredPlatform.setText("");
        tfPreferredPlatform.setDisable(true);

        securityConsultantComboBox.setDisable(true);
        securityConsultantComboBox.setPromptText("None");

        deviceComboBox.setDisable(false);
        deviceComboBox.setPromptText("None");
        deviceComboBox.setItems(FXCollections.observableArrayList(
                "Java", "C++", "ABAP", "VBA", "Python", "Ruby", "IOS", "Other"
        ));
    }

    private void setAdministratorComboBoxes() {
        tfPreferredPlatform.setDisable(false);

        securityConsultantComboBox.setDisable(true);
        securityConsultantComboBox.setPromptText("None");

        deviceComboBox.setDisable(false);
        deviceComboBox.setPromptText("None");
        deviceComboBox.setItems(FXCollections.observableArrayList(
                "Application", "Network", "SAP", "Other"
        ));
    }

    private void setSecurityConsultantComboBoxes() {
        tfPreferredPlatform.setText("");
        tfPreferredPlatform.setDisable(true);

        securityConsultantComboBox.setDisable(false);
        securityConsultantComboBox.setItems(FXCollections.observableArrayList("Yes", "No"));

        deviceComboBox.setDisable(true);
        deviceComboBox.setPromptText("None");
    }
}
