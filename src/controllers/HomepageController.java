package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

import java.io.IOException;

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

    /**
     * End of methods for setting controller and scenePath when switching between scenes.
     */

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
}
