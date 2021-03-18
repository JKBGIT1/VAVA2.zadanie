package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        jobNameCol.setCellValueFactory(new PropertyValueFactory<>("jobName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("specialistType"));
        highestEducationCol.setCellValueFactory(new PropertyValueFactory<>("highestEducation"));
        experienceCol.setCellValueFactory(new PropertyValueFactory<>("experience"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        jobsTableView.setItems(this.getJobsObservableList());
    }
}
