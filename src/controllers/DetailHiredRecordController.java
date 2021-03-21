package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailHiredRecordController extends HomepageController implements Initializable {

    private HiredRecord hiredRecord;

    @FXML
    private TableView<Specialist> specialistTableView;
    @FXML
    private Label companyNameLabel, businessAreaLabel;
    @FXML
    private TableColumn<Specialist, String> nameCol, typeCol;
    @FXML
    private TableColumn<Specialist, Double> priceCol;
    @FXML
    private TableColumn<Specialist, Integer> experienceCol;
    @FXML
    private TableColumn<Specialist, Boolean> hiredCol;
    @FXML
    private ImageView logoImageView;

    public DetailHiredRecordController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList,
            HiredRecord hiredRecord
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
        this.hiredRecord = hiredRecord;
    }

    public HiredRecord getHiredRecord() {
        return hiredRecord;
    }

    public void setHiredRecord(HiredRecord hiredRecord) {
        this.hiredRecord = hiredRecord;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // map Specialist's attributes to columns in tableView
        this.mapSpecialistAttributesToColumns(nameCol, typeCol, priceCol, experienceCol, hiredCol);
        // display hired specialists in table
        specialistTableView.setItems(this.getHiredRecord().getHiredSpecialists());
        // display information about employer
        companyNameLabel.setText(this.getHiredRecord().getEmployer().getName());
        businessAreaLabel.setText(this.getHiredRecord().getEmployer().getBusinessArea());
        logoImageView.setImage(this.getHiredRecord().getEmployer().getLogo());
    }

    public void dismissSpecialist() {
        // If user didn't select specialist which he/she wants to dismiss, then error will popup.
        if (specialistTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp(
                    "Select specialist",
                    "You need to select specialist, which you want to dismiss."
            );
        } else {
            Specialist selectedSpecialist = specialistTableView.getSelectionModel().getSelectedItem();

            // Remove specialists from lists
            this.getSpecialistObservableList().remove(selectedSpecialist);
            this.getHiredRecord().getHiredSpecialists().remove(selectedSpecialist);

            // specialist is now ready for new work
            selectedSpecialist.setHired(false);

            // add him back to list
            this.getSpecialistObservableList().add(selectedSpecialist);
        }
    }
}
