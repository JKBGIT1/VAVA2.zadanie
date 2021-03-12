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

public class HiredRecordsController extends HomepageController implements Initializable {

    @FXML
    private TableView<HiredRecord> hiredRecordsTableView;
    @FXML
    private TableColumn<HiredRecord, String> companyNameCol, businessAreaCol;
    @FXML
    private TableColumn<HiredRecord, Integer> hiredEmployeesNumberCol;

    public HiredRecordsController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO need to make better mapping for businessArea companyName and hiredEmployeesNumber
        companyNameCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        companyNameCol.setCellValueFactory(new PropertyValueFactory<>("businessArea"));
        companyNameCol.setCellValueFactory(new PropertyValueFactory<>("hiredEmployeesNumber"));

        hiredRecordsTableView.setItems(this.getHiringRecordObservableList());
    }

    public void detailHiredRecordScene(MouseEvent event) {

    }
}