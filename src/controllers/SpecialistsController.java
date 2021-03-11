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

public class SpecialistsController extends HomepageController implements Initializable {

    @FXML
    private TableView<Specialist> specialistTableView;
    @FXML
    private TableColumn<Specialist, String> nameCol, typeCol;
    @FXML
    private TableColumn<Specialist, Double> priceCol;
    @FXML
    private TableColumn<Specialist, Integer> experienceCol;
    @FXML
    private TableColumn<Specialist, Boolean> freeCol;

    public SpecialistsController(
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
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("dayPrice"));
        experienceCol.setCellValueFactory(new PropertyValueFactory<>("yearsExperience"));
        freeCol.setCellValueFactory(new PropertyValueFactory<>("hired"));

        specialistTableView.setItems(this.getSpecialistObservableList());
    }

    public void addSpecialistScene(MouseEvent event) {
        this.setScenePath(ADD_SPECIALIST_SCENE);
        this.setController(new AddSpecialistController(
                this.getSpecialistObservableList(),
                this.getEmployerObservableList(),
                this.getJobsObservableList(),
                this.getHiringRecordObservableList(),
                null
        ));
        this.switchScene(event);
    }

    public void detailSpecialistScene() {

    }

    public void hireSpecialistScene() {

    }
}
