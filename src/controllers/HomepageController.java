package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class HomepageController {

    public static final String HOMEPAGE_SCENE = "/fxmls/HomepageScene.fxml";
    public static final String SPECIALIST_SCENE = "/fxmls/SpecialistsScene.fxml";
    public static final String ADD_SPECIALIST_SCENE = "/fxmls/AddSpecialistScene.fxml";
    public static final String FINISH_ADD_SPECIALIST_SCENE = "/fxmls/FinishAddSpecialistScene.fxml";

    private String scenePath;
    private Object controller;

    private ObservableList<Specialist> specialistObservableList = FXCollections.observableArrayList();
    private ObservableList<Employer> employerObservableList = FXCollections.observableArrayList();
    private ObservableList<Job> jobsObservableList = FXCollections.observableArrayList();
    private ObservableList<HiredRecord> hiringRecordObservableList = FXCollections.observableArrayList();

    public HomepageController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList)
    {
        this.specialistObservableList = specialistObservableList;
        this.employerObservableList = employerObservableList;
        this.jobsObservableList = jobsObservableList;
        this.hiringRecordObservableList = hiringRecordObservableList;
    }

    public String getScenePath() {
        return scenePath;
    }

    public void setScenePath(String scenePath) {
        this.scenePath = scenePath;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public ObservableList<Specialist> getSpecialistObservableList() {
        return specialistObservableList;
    }

    public void setSpecialistObservableList(ObservableList<Specialist> specialistObservableList) {
        this.specialistObservableList = specialistObservableList;
    }

    public ObservableList<Employer> getEmployerObservableList() {
        return employerObservableList;
    }

    public void setEmployerObservableList(ObservableList<Employer> employerObservableList) {
        this.employerObservableList = employerObservableList;
    }

    public ObservableList<Job> getJobsObservableList() {
        return jobsObservableList;
    }

    public void setJobsObservableList(ObservableList<Job> jobsObservableList) {
        this.jobsObservableList = jobsObservableList;
    }

    public ObservableList<HiredRecord> getHiringRecordObservableList() {
        return hiringRecordObservableList;
    }

    public void setHiringRecordObservableList(ObservableList<HiredRecord> hiringRecordObservableList) {
        this.hiringRecordObservableList = hiringRecordObservableList;
    }

    /**
     * Methods for setting controller and scenePath when switching between scenes.
     */

    public void specialistsScene(MouseEvent event) {
        this.setScenePath(SPECIALIST_SCENE);
        this.setController(new SpecialistsController(
                this.getSpecialistObservableList(),
                this.getEmployerObservableList(),
                this.getJobsObservableList(),
                this.getHiringRecordObservableList()
        ));
        this.switchScene(event);
    }

    public void homepageScene(MouseEvent event) {
        this.setScenePath(HOMEPAGE_SCENE);
        this.setController(new HomepageController(
                this.getSpecialistObservableList(),
                this.getEmployerObservableList(),
                this.getJobsObservableList(),
                this.getHiringRecordObservableList()
        ));
        this.switchScene(event);
    }

    public void employersScene(MouseEvent event) {

    }

    public void jobsScene(MouseEvent event) {

    }

    public void endProgram(MouseEvent event) {
        System.exit(0);
    }

    // When scenePath and controller is set program is going to change a scene on the screen
    public void switchScene(MouseEvent event) {
        try {
            if (!this.getScenePath().equals("")) {
                // This was taken from my school project in second grade on DBS
                // https://github.com/FIIT-DBS2020/project-hlavacka_muller/blob/master/src/GUI/SwitchScene.java
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(this.getScenePath()));
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                loader.setController(this.getController());

                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                // Load css
                scene.getStylesheets().add(getClass().getResource("/fxmls/css/template.css").toExternalForm());
                window.setScene(scene);
                window.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * End of methods for setting controller and scenePath when switching between scenes.
     */

    /**
     * Converting methods
     */

    // NOTE: This function needs to be used inside try and catch
    public double convertStringToDouble(String stringNumber) {
        // Before parse replace commas with dots to prevent error
        double doubleNumber = Double.parseDouble(stringNumber.replaceAll(",","."));

        // If user didn't use zero as an input then we floor this number on two decimals
        if (doubleNumber != 0) {
            doubleNumber = Math.floor(doubleNumber * 100) / 100; // Taken from https://stackoverflow.com/questions/7747469/how-can-i-truncate-a-double-to-only-two-decimal-places-in-java
        }
        return doubleNumber;
    }

    // NOTE: This function needs to be used inside try and catch
    public String convertDateToString(Date date) {
        // Inspiration from https://www.javatpoint.com/java-date-to-string
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }

    // NOTE: This function needs to be used inside try and catch
    public String convertDoubleToString(double price) {
        return String.valueOf(price);
    }

    public Date convertStringToDate(String stringDate) {
        try {
            // Try to handle more types of date format
            stringDate = stringDate.replaceAll("/", ".");
            stringDate = stringDate.replaceAll("-", ".");
            stringDate = stringDate.replaceAll(",", ".");

            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(stringDate); // Inspiration from https://www.javatpoint.com/java-string-to-date
            return date;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * End of Converting methods
     */

    /**
     * Start of PopUps
     */

    // Inspiration for PopUp windows on https://stackoverflow.com/questions/26341152/controlsfx-dialogs-deprecated-for-what/32618003#32618003
    public void showSuccessPopUp(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    public void showErrorPopUp(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    public int showInputDialog(String contentText) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Fill text field");
        dialog.setHeaderText("INPUT");
        dialog.setContentText(contentText);

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            try {
                return Integer.parseInt(result.get());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return 1; // Default value will be 1, if user didn't enter int.
    }

    public boolean showConfirmationPopUp(String headerText, String contentText ) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Choose an option");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            // If condition under this comment is true, user confirm action.
            if (result.get().getText().equals("OK")) {
                return true;
            }
        }

        return false;
    }
}
