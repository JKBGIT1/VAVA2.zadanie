package controllers;

import javafx.collections.FXCollections;
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

public class HireSpecialistsController extends HomepageController implements Initializable {

    @FXML
    private TableView<Specialist> allSpecialistsTableView, hiredSpecialistsTableView;
    @FXML
    private TableColumn<Specialist, String> allNameCol, allTypeCol, hiredNameCol, hiredTypeCol;
    @FXML
    private TableColumn<Specialist, Double> allPriceCol, hiredPriceCol;
    @FXML
    private TableColumn<Specialist, Integer> allExperienceCol, hiredExperienceCol;
    @FXML
    private TableColumn<Specialist, Boolean> allFreeCol, hiredFreeCol;

    private Employer selectedEmployer;
    private ObservableList<Specialist> hiredSpecialistsList;

    public HireSpecialistsController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList,
            Employer employer
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
        this.selectedEmployer = employer;
        this.hiredSpecialistsList = FXCollections.observableArrayList();
    }

    public Employer getSelectedEmployer() {
        return selectedEmployer;
    }

    public void setSelectedEmployer(Employer selectedEmployer) {
        this.selectedEmployer = selectedEmployer;
    }

    public ObservableList<Specialist> getHiredSpecialistsList() {
        return hiredSpecialistsList;
    }

    public void setHiredSpecialistsList(ObservableList<Specialist> hiredSpecialistsList) {
        this.hiredSpecialistsList = hiredSpecialistsList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // map Specialist's attributes to columns in tableView
        this.mapSpecialistAttributesToColumns(allNameCol, allTypeCol, allPriceCol, allExperienceCol, allFreeCol);
        this.mapSpecialistAttributesToColumns(hiredNameCol, hiredTypeCol, hiredPriceCol, hiredExperienceCol, hiredFreeCol);

        allSpecialistsTableView.setItems(this.getSpecialistObservableList());
        hiredSpecialistsTableView.setItems(this.getHiredSpecialistsList());
    }

    public void dismissSpecialist() {
        if (hiredSpecialistsTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp(
                    "Select from will be hired specialist",
                    "You need to select specialist from will be hired specialists table"
            );
        } else {
            Specialist selectedSpecialist = hiredSpecialistsTableView.getSelectionModel().getSelectedItem();
            selectedSpecialist.setHired(false);
            // Specialist will be automatically removed from tableView
            // Remove and add the specialist from list, to make sure,
            // that data in tableView with all specialists are updated.
            this.getSpecialistObservableList().remove(selectedSpecialist);
            this.getSpecialistObservableList().add(selectedSpecialist);

            this.getHiredSpecialistsList().remove(selectedSpecialist);
        }
    }

    public void hireSpecialist() {
        if (allSpecialistsTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp(
                    "Select from table with all specialist",
                    "You need to select specialist from all specialists table, who you want to hire."
            );
        } else {
            Specialist selectedSpecialist = allSpecialistsTableView.getSelectionModel().getSelectedItem();

            if (selectedSpecialist.isHired()) {
                this.showErrorPopUp("Already hired", "You can hire only free specialists.");
            } else {
                // Specialist might be finally hired
                selectedSpecialist.setHired(true);
                // Remove and add the specialist from list, to make sure, that data in tableView are updated.
                this.getSpecialistObservableList().remove(selectedSpecialist);
                this.getSpecialistObservableList().add(selectedSpecialist);
                this.getHiredSpecialistsList().add(selectedSpecialist);
            }
        }
    }

    public void finishHiring(MouseEvent event) {
        // add new record about hiring to ObservableList with all HiredRecords
        this.getHiringRecordObservableList().add(new HiredRecord(
                this.getSelectedEmployer(),
                this.getHiredSpecialistsList()
        ));
        this.employersScene(event);
    }
}
