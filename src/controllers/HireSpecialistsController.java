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
    private TableColumn<Specialist, Boolean> allHiredCol, hiredHiredCol;

    private Job selectedJob;
    private ObservableList<Specialist> hiredSpecialistsList;

    public HireSpecialistsController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList,
            Job job
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
        this.selectedJob = job;
        this.hiredSpecialistsList = FXCollections.observableArrayList();
    }

    public Job getSelectedJob() {
        return selectedJob;
    }

    public void setSelectedJob(Job selectedJob) {
        this.selectedJob = selectedJob;
    }

    public ObservableList<Specialist> getHiredSpecialistsList() {
        return hiredSpecialistsList;
    }

    public void setHiredSpecialistsList(ObservableList<Specialist> hiredSpecialistsList) {
        this.hiredSpecialistsList = hiredSpecialistsList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // map Specialist's attributes to columns in allSpecialistsTableView
        this.mapSpecialistAttributesToColumns(allNameCol, allTypeCol, allPriceCol, allExperienceCol, allHiredCol);
        // map Specialist's attributes to columns in hiredSpecialistsTableView
        this.mapSpecialistAttributesToColumns(hiredNameCol, hiredTypeCol, hiredPriceCol, hiredExperienceCol, hiredHiredCol);

        // display all specialists and hired specialists
        allSpecialistsTableView.setItems(this.getSpecialistObservableList());
        hiredSpecialistsTableView.setItems(this.getHiredSpecialistsList());
    }

    public void backToJobsScene(MouseEvent event) {
        // free specialists, who were selected for hiring
        for (Specialist specialist : this.getHiredSpecialistsList()) {
            this.getSpecialistObservableList().remove(specialist);
            specialist.setHired(false);
            this.getSpecialistObservableList().add(specialist);
        }

        this.jobsScene(event);
    }

    public void dismissSpecialist() {
        if (hiredSpecialistsTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp(
                    "Select from will be hired specialist",
                    "You need to select specialist from will be hired specialists table"
            );
        } else {
            Specialist selectedSpecialist = hiredSpecialistsTableView.getSelectionModel().getSelectedItem();
            // remove selected specialist from hired list
            this.getHiredSpecialistsList().remove(selectedSpecialist);
            // set hired to false and refresh both tableViews
            selectedSpecialist.setHired(false);
            hiredSpecialistsTableView.refresh();
            allSpecialistsTableView.refresh();
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
                // set hired specialist to true
                selectedSpecialist.setHired(true);
                // add him to hired specialist list and refresh both tableViews
                this.getHiredSpecialistsList().add(selectedSpecialist);
                hiredSpecialistsTableView.refresh();
                allSpecialistsTableView.refresh();
            }
        }
    }

    public void finishHiring(MouseEvent event) {
        if (this.getHiredSpecialistsList().size() < 1) {
            this.showErrorPopUp(
                    "Not enough specialists",
                    "You have to hire at least one specialist."
            );
        } else {
            // Increase current number of employees for employer
            this.getSelectedJob().getEmployer().setEmployeesNumber(
                    this.getSelectedJob().getEmployer().getEmployeesNumber() + this.getHiredSpecialistsList().size()
            );

            // add new record about hiring to ObservableList with all HiredRecords
            this.getHiringRecordObservableList().add(new HiredRecord(
                    this.getSelectedJob(),
                    this.getHiredSpecialistsList()
            ));
            this.jobsScene(event);
        }
    }
}
