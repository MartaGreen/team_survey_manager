package pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;

import static pages.Router.switchToPage;

public class NewSurveyController {
    @FXML
    private TextField surveyNameField;
    @FXML
    private ListView<String> participantsBox;

    @FXML
    public void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList(
                "product", "manager", "design", "frontend", "backend"
        );
        participantsBox.setItems(items);
        participantsBox.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    }

    @FXML
    private void createSurvey(ActionEvent event) throws IOException {
        String name = surveyNameField.getText();
        ObservableList<String> selectedTeams = participantsBox.getSelectionModel().getSelectedItems();

        Main.getCorporation().surveyManager.createNewSurvey(name, selectedTeams, Main.getCurrentUser());
        switchToPage(event, "main.fxml");
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        switchToPage(event, "main.fxml");
    }
}
