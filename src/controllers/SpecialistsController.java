package controllers;

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
    private TableColumn<Specialist, Boolean> hiredCol;

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
        // map Specialist's attributes to columns in tableView
        this.mapSpecialistAttributesToColumns(nameCol, typeCol, priceCol, experienceCol, hiredCol);

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

    public void detailSpecialistScene(MouseEvent event) {
        if (specialistTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp(
                    "Select specialist",
                    "You need to select specialist, which you want to see in detail"
            );
        } else {
            Specialist specialist = specialistTableView.getSelectionModel().getSelectedItem();

            this.setScenePath(DETAIL_SPECIALIST_SCENE);
            this.setController(new DetailSpecialistController(
                    this.getSpecialistObservableList(),
                    this.getEmployerObservableList(),
                    this.getJobsObservableList(),
                    this.getHiringRecordObservableList(),
                    specialist
            ));
            this.switchScene(event);
        }
    }
}
