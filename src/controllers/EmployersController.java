package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployersController extends HomepageController implements Initializable {

    @FXML
    private TableView<Employer> employersTableView;
    @FXML
    private TableColumn<Employer, String> nameCol, businessAreaCol;
    @FXML
    private TableColumn<Employer, Integer> employeesNumberCol;
    @FXML
    private TableColumn<Employer, Image> logoCol;

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

        // Taken from https://stackoverflow.com/questions/22005115/how-to-add-an-image-into-a-javafx-tableview-column
        logoCol.setCellFactory(param -> {
            // My explanation to code
            // Firstly program creates ImageView object,
            // which will be in tableColumn and sets it's height and width
            ImageView imageview = new ImageView();
            imageview.setFitHeight(50);
            imageview.setFitWidth(50);

            // Now program creates TableCell object for Employer class with Image object content
            TableCell<Employer, Image> cell = new TableCell<Employer, Image>() {
                // In this function it will set image to previously created imageview
                public void updateItem(Image item, boolean empty) {
                    if (item != null) {
                        imageview.setImage(item);
                    }
                }
            };
            // this will attach the image to the cell
            cell.setGraphic(imageview);
            return cell;
        });
        logoCol.setCellValueFactory(new PropertyValueFactory<Employer, Image>("logo"));

        // Fill tableView with Employers data.
        employersTableView.setItems(this.getEmployerObservableList());
    }

    public void addEmployerScene(MouseEvent event) {
        this.setScenePath(ADD_EMPLOYER_SCENE);
        this.setController(new AddEmployerController(
                this.getSpecialistObservableList(),
                this.getEmployerObservableList(),
                this.getJobsObservableList(),
                this.getHiringRecordObservableList()
        ));
        this.switchScene(event);
    }

    // Employer wants to hire specialists
    public void hireSpecialistsScene(MouseEvent event) {
        if (employersTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp("Select employer", "Select employer from table, who wants to hire specialists.");
        } else {
            Employer selectedEmployer = employersTableView.getSelectionModel().getSelectedItem();

            this.setScenePath(HIRE_SPECIALISTS_SCENE);
            this.setController(new HireSpecialistsController(
                    this.getSpecialistObservableList(),
                    this.getEmployerObservableList(),
                    this.getJobsObservableList(),
                    this.getHiringRecordObservableList(),
                    selectedEmployer
            ));

            this.switchScene(event);
        }
    }
}
