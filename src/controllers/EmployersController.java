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

public class EmployersController extends HomepageController implements Initializable {

    @FXML
    private TableView<Employer> employerTableView;
    @FXML
    private TableColumn<Employer, String> nameCol, businessAreaCol;
    @FXML
    private TableColumn<Employer, Integer> employeesNumberCol;

    public EmployersController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        businessAreaCol.setCellValueFactory(new PropertyValueFactory<>("businessArea"));
        employeesNumberCol.setCellValueFactory(new PropertyValueFactory<>("employeesNumber"));
    }

    public void addEmployerScene() {

    }
}
