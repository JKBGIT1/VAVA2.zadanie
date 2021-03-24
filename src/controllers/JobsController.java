package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

import java.net.URL;
import java.util.ResourceBundle;

public class JobsController extends HomepageController implements Initializable {

    @FXML
    private TableView<Job> jobsTableView;
    @FXML
    private TableColumn<Job, String> jobNameCol, typeCol, highestEducationCol;
    @FXML
    private TableColumn<Job, Integer> experienceCol;
    @FXML
    private TableColumn<Job, Double> priceCol;

    public JobsController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // map columns to attributes in Job class
        jobNameCol.setCellValueFactory(new PropertyValueFactory<>("jobName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("specialistType"));
        highestEducationCol.setCellValueFactory(new PropertyValueFactory<>("highestEducation"));
        experienceCol.setCellValueFactory(new PropertyValueFactory<>("experience"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        jobsTableView.setItems(this.getJobsObservableList());
    }

    public void detailJobScene(MouseEvent event) {
        if (jobsTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp(
                    "Select job",
                    "You need to select a job, which you want to display in detail"
            );
        } else {
            Job selectedJob = jobsTableView.getSelectionModel().getSelectedItem();

            this.setScenePath(DETAIL_JOB_SCENE);
            this.setController(new DetailJobController(
                    this.getSpecialistObservableList(),
                    this.getEmployerObservableList(),
                    this.getJobsObservableList(),
                    this.getHiringRecordObservableList(),
                    selectedJob
            ));

            this.switchScene(event);
        }
    }

    public void hireSpecialistsScene(MouseEvent event) {
        // User need to select an employer, who will hire specialists
        if (jobsTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp("Select job", "Select job from table, where you want to sign specialists");
        } else {
            Job selectedJob = jobsTableView.getSelectionModel().getSelectedItem();

            this.setScenePath(HIRE_SPECIALISTS_SCENE);
            this.setController(new HireSpecialistsController(
                    this.getSpecialistObservableList(),
                    this.getEmployerObservableList(),
                    this.getJobsObservableList(),
                    this.getHiringRecordObservableList(),
                    selectedJob
            ));

            this.switchScene(event);
        }
    }
}
