package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

import java.net.URL;
import java.util.ResourceBundle;

public class FinishAddSpecialistController extends AddSpecialistController implements Initializable {

    @FXML
    private ListView<String> educationListView, certificatesListView;
    @FXML
    private TextField tfEducation, tfCertificate;

    public FinishAddSpecialistController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList,
            Specialist specialist)
    {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList, specialist);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        educationListView.setItems(this.getSpecialist().getEducation());
        certificatesListView.setItems(this.getSpecialist().getCertificates());
    }

    public void addEducation() {
        if (tfEducation.getText().equals("")) { // Need to write some education before adding to listview
            this.showErrorPopUp("Enter education", "Enter education in the text field under Education table.");
        } else {
            String enteredEducation = tfEducation.getText();

            this.getSpecialist().getEducation().add(enteredEducation); // education will be automatically added to listView
            tfEducation.setText("");
        }
    }

    public void removeEducation() {
        int selectedEducation = educationListView.getSelectionModel().getSelectedIndex();

        if (selectedEducation == -1) { // No education is selected
            this.showErrorPopUp("Select from Education table", "Select education, which you want to remove");
        } else {
            // deleting from ObservableList based on index
            // education will be automatically removed from listview
            this.getSpecialist().getEducation().remove(selectedEducation);
        }
    }

    public void addCertificate() {
        if (tfCertificate.getText().equals("")) { // Need to write some education before adding to listview
            this.showErrorPopUp("Enter certificate", "Enter certificate in the text field under Certificates table.");
        } else {
            String enteredCertificate = tfCertificate.getText();

            this.getSpecialist().getCertificates().add(enteredCertificate); // certificate will be automatically added to listView
            tfCertificate.setText("");
        }
    }

    public void removeCertificate() {
        int selectedCertificate = certificatesListView.getSelectionModel().getSelectedIndex();

        if (selectedCertificate == -1) { // No education is selected
            this.showErrorPopUp("Select from Certificates table", "Select certificate, which you want to remove");
        } else {
            // deleting from ObservableList based on index
            // certificate will be automatically removed from listview
            this.getSpecialist().getCertificates().remove(selectedCertificate);
        }
    }

    public void createSpecialist(MouseEvent event) {
        // add created specialist to ObservableList of Specialist before switching scene
        this.getSpecialistObservableList().add(this.getSpecialist());
        this.specialistsScene(event);
    }

    public void addSpecialistScene(MouseEvent event) {
        this.setScenePath(ADD_SPECIALIST_SCENE);
        this.setController(new AddSpecialistController(
                this.getSpecialistObservableList(),
                this.getEmployerObservableList(),
                this.getJobsObservableList(),
                this.getHiringRecordObservableList(),
                this.getSpecialist()
        ));
        this.switchScene(event);
    }
}
