package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import models.Employer;
import models.HiredRecord;
import models.Job;
import models.Specialist;

import java.io.File;

public class AddEmployerController extends HomepageController {
    @FXML
    private TextArea taBusinessArea;
    @FXML
    private TextField tfName, tfNumberOfEmployees, tfLogo;
    @FXML
    private ImageView logoImageView;

    private Image uploadedImage;

    public AddEmployerController(
            ObservableList<Specialist> specialistObservableList,
            ObservableList<Employer> employerObservableList,
            ObservableList<Job> jobsObservableList,
            ObservableList<HiredRecord> hiringRecordObservableList
    ) {
        super(specialistObservableList, employerObservableList, jobsObservableList, hiringRecordObservableList);
        this.uploadedImage = null;
    }

    // This will remove uploaded from ImageView and uploadedImage attribute
    public void removeImage() {
        logoImageView.setImage(null);
        this.uploadedImage = null;
    }

    public void uploadImage(MouseEvent event) {
        try {
            // inspiration for FileChooser from https://stackoverflow.com/questions/60184035/java-fx-image-upload-from-file
            FileChooser fileChooser = new FileChooser();

            // create extensions for FileChooser
            // user will be able to choose only these types of files from filesystem
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG", "*.PNG");
            FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg", "*.jpg");
            FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png", "*.png");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterjpg, extFilterpng);

            // show new dialog and store selected image into File object
            File file = fileChooser.showOpenDialog(null);
            // make Image as big as ImageView
            this.uploadedImage = new Image(
                    file.toURI().toString(),
                    logoImageView.getFitWidth(),
                    logoImageView.getFitHeight(),
                    false,
                    false
            );
            logoImageView.setImage(uploadedImage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createEmployer(MouseEvent event) {
        try {
            String name = tfName.getText();
            String businessArea = taBusinessArea.getText();
            int numberOfEmployees = Integer.parseInt(tfNumberOfEmployees.getText());

            // add newly created Employer to the list of all Employers
            this.getEmployerObservableList().add(new Employer(name, businessArea, numberOfEmployees, this.uploadedImage));

            this.employersScene(event);
        } catch (Exception e) {
            this.showErrorPopUp("Input error", "Number of employees must be an integer.");
            System.out.println(e.getMessage());
        }
    }
}
