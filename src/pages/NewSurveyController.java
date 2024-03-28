package pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;

import static pages.Router.switchToPage;

public class NewSurveyController {
    @FXML
    private TextField surveyNameField;

    @FXML
    public void initialize() {

    }

    @FXML
    private void createSurvey(ActionEvent event) throws IOException {
        String name = surveyNameField.getText();
        Main.getCorporation().surveyManager.createNewSurvey(name, new ArrayList<>(), Main.getCurrentUser());
        switchToPage(event, "main.fxml");
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        switchToPage(event, "main.fxml");
    }
}
