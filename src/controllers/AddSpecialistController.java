package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.*;

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
    ComboBox<String> highestEducationComboBox = new ComboBox<String>();
    @FXML
    ComboBox<String> securityConsultantComboBox = new ComboBox<String>();
    @FXML
    TextField tfName, tfPrice, tfExperience, tfPreferredPlatform;

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
            String specialistType = this.getSpecialist().getType();
            Specialist createdSpecialist = this.getSpecialist();

            // Fill all information, which are common for each specialist
            tfName.setText(createdSpecialist.getName());
            tfPrice.setText(String.valueOf(createdSpecialist.getDayPrice()));
            tfExperience.setText(String.valueOf(createdSpecialist.getYearsExperience()));
            specialistTypeComboBox.getSelectionModel().select(createdSpecialist.getType());
            highestEducationComboBox.getSelectionModel().select(createdSpecialist.getEducation());

            // Before filling information about specific specialist scene needs to be prepared
            this.getSelectedSpecialist();

            // Need to fill information about specific specialist separately
            if (specialistType.equals(PROGRAMMER)) {
                Programmer programmer = (Programmer) createdSpecialist;
                deviceComboBox.getSelectionModel().select(programmer.getDevice());

            } else if (specialistType.equals(ADMINISTRATOR)) {
                Administrator administrator = (Administrator) createdSpecialist;
                deviceComboBox.getSelectionModel().select(administrator.getDevice());
                tfPreferredPlatform.setText(administrator.getPreferredPlatform());

            } else if (specialistType.equals(SECURITY_CONSULTANT)) {
                SecurityConsultant securityConsultant = (SecurityConsultant) createdSpecialist;

                if (securityConsultant.isCyberSecurity()) {
                    securityConsultantComboBox.getSelectionModel().select("Yes");
                } else {
                    securityConsultantComboBox.getSelectionModel().select("No");
                }
            }
        }

        // create items in education and specialist type CombBoxes
        highestEducationComboBox.setItems(FXCollections.observableArrayList(
                "Grade school", "High school", "University"
        ));
        specialistTypeComboBox.setItems(FXCollections.observableArrayList(
                "Programmer", "Administrator", "Security consultant"
        ));
    }

    public void finishAddSpecialist(MouseEvent event) {
        String selectedSpecialist = specialistTypeComboBox.getSelectionModel().getSelectedItem();
        if (selectedSpecialist == null) { // Need to select specialist type
            this.showErrorPopUp("No type selected", "You need to select specialist type.");
        } else {
            try {
                // get generic information about each specialist
                String specialistName = tfName.getText();
                String highestEducation = highestEducationComboBox.getSelectionModel().getSelectedItem();
                double specialistPrice = this.convertStringToDouble(tfPrice.getText());
                int specialistExperience = Integer.parseInt(tfExperience.getText());
                ObservableList<String> certificatesList = FXCollections.observableArrayList();

                if (this.getSpecialist() != null) {
                    certificatesList = this.getSpecialist().getCertificates();
                }

                switch (selectedSpecialist) {
                    case PROGRAMMER:
                        setSpecialistAsProgrammer(specialistName, specialistPrice, specialistExperience, highestEducation, certificatesList);
                        break;
                    case ADMINISTRATOR:
                        setSpecialistAsAdministrator(specialistName, specialistPrice, specialistExperience, highestEducation, certificatesList);
                        setAdministratorComboBoxes();
                        break;
                    case SECURITY_CONSULTANT:
                        setSpecialistAsConsultant(specialistName, specialistPrice, specialistExperience, highestEducation, certificatesList);
                        break;
                }

                this.setScenePath(FINISH_ADD_SPECIALIST_SCENE);
                this.setController(new FinishAddSpecialistController(
                        this.getSpecialistObservableList(),
                        this.getEmployerObservableList(),
                        this.getJobsObservableList(),
                        this.getHiringRecordObservableList(),
                        this.getSpecialist()
                ));
                this.switchScene(event);
            } catch (Exception e) {
                this.showErrorPopUp("Error", "All text fields have to be filled and check entered types.");
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Creating specific type of specialist, when going to the next step of creation.
     * @param name
     * @param price
     * @param experience
     * @param education
     * @param experienceList
     */

    private void setSpecialistAsProgrammer(String name, double price, int experience, String education, ObservableList<String> experienceList) {
        String selectedDevice = deviceComboBox.getSelectionModel().getSelectedItem();
        this.setSpecialist(new Programmer(name, price, experience, PROGRAMMER, education, experienceList, selectedDevice));
    }

    private void setSpecialistAsAdministrator(String name, double price, int experience, String education, ObservableList<String> experienceList) {
        String selectedDevice = deviceComboBox.getSelectionModel().getSelectedItem();
        String platform = tfPreferredPlatform.getText();

        this.setSpecialist(new Administrator(name, price, experience, ADMINISTRATOR, education, experienceList, selectedDevice, platform));
    }

    private void setSpecialistAsConsultant(String name, double price, int experience, String education, ObservableList<String> experienceList) {
        String selectedCyberSecurity = securityConsultantComboBox.getSelectionModel().getSelectedItem();

        if (selectedCyberSecurity != null && selectedCyberSecurity.equals("Yes")) {
            this.setSpecialist(new SecurityConsultant(name, price, experience, SECURITY_CONSULTANT, education, experienceList, true));
        } else {
            this.setSpecialist(new SecurityConsultant(name, price, experience, SECURITY_CONSULTANT, education, experienceList, false));
        }
    }

    /*
     * End of creating specific type of specialist
     */

    public void getSelectedSpecialist() {
        String selectedSpecialist = specialistTypeComboBox.getSelectionModel().getSelectedItem();

        switch (selectedSpecialist) {
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
        deviceComboBox.getSelectionModel().select("No");
    }
}
