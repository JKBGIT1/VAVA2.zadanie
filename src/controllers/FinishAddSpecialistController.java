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

    }

    public void addEducation() {
        if (tfEducation.getText().equals("")) { // Need to write some education before adding to listview

        } else {
            educationListView.getItems().add(tfEducation.getText());
            tfEducation.setText("");
        }
    }

    public void removeEducation() {
        int selectedEducation = educationListView.getSelectionModel().getSelectedIndex();

        if (selectedEducation == -1) { // No education is selected

        } else { // Remove education from listview
            educationListView.getItems().remove(selectedEducation);
        }
    }

    public void addCertificate() {
        if (tfCertificate.getText().equals("")) { // Need to write some education before adding to listview

        } else {
            certificatesListView.getItems().add(tfCertificate.getText());
            tfCertificate.setText("");
        }
    }

    public void removeCertificate() {
        int selectedCertificate = certificatesListView.getSelectionModel().getSelectedIndex();

        if (selectedCertificate == -1) { // No education is selected

        } else { // Remove education from listview
            certificatesListView.getItems().remove(selectedCertificate);
        }
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
