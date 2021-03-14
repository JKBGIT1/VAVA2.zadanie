package controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        // map table columns on methods from HiredRecord, to get their values
        // inspiration https://stackoverflow.com/questions/14413040/converting-integer-to-observablevalueinteger-in-javafx
        // inspiration https://stackoverflow.com/questions/25204068/how-do-i-point-a-propertyvaluefactory-to-a-value-of-a-map
        companyNameCol.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(data.getValue().getEmployer().getName()));
        businessAreaCol.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(data.getValue().getEmployer().getBusinessArea()));
        hiredEmployeesNumberCol.setCellValueFactory(
                data -> new SimpleIntegerProperty(data.getValue().getHiredEmployeesNumber()).asObject());

        hiredRecordsTableView.setItems(this.getHiringRecordObservableList());
    }

    public void detailHiredRecordScene(MouseEvent event) {
        if (hiredRecordsTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp(
                    "Select Hired record",
                    "Select hired record, which you want to display in detail."
            );
        } else {
            HiredRecord hiredRecord = hiredRecordsTableView.getSelectionModel().getSelectedItem();

            this.setScenePath(DETAIL_HIRED_RECORD_SCENE);
            this.setController(new DetailHiredRecordController(
                    this.getSpecialistObservableList(),
                    this.getEmployerObservableList(),
                    this.getJobsObservableList(),
                    this.getHiringRecordObservableList(),
                    hiredRecord
            ));

            this.switchScene(event);
        }
    }
}
