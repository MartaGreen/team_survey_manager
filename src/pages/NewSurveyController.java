package pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;

import static pages.Router.switchToPage;

public class NewSurveyController {
    @FXML
    private TextField surveyNameField;
    @FXML
    private TextArea surveyDescriptionField;
    @FXML
    private ListView<String> participantsBox;
    @FXML
    private VBox surveyOptionsField;

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
        ArrayList<String> options = new ArrayList<>();

        for (Node option: surveyOptionsField.getChildren()) {
            System.out.print(option);
            options.add(((TextField) option).getText());
        }

        Main.getSurveyManager().createNewSurvey(name, selectedTeams, options);
        switchToPage(event, "main.fxml");
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        switchToPage(event, "main.fxml");
    }

    @FXML
    private void addOption(ActionEvent event) {
        AnchorPane box = new AnchorPane();
        box.prefWidth(600);

        TextField optionName =  new TextField();
        optionName.setPromptText("option");
        optionName.setLayoutY(5);
        optionName.setLayoutX(5);
        optionName.setPrefWidth(500);
        optionName.setFont(Font.font(14));

        Button removeBtn = new Button();
        removeBtn.setText("remove");
        removeBtn.setFont(Font.font(14));
        removeBtn.setOnAction(this::deleteOption);
        removeBtn.setLayoutY(5);
        removeBtn.setLayoutX(550);

        box.getChildren().addAll(optionName, removeBtn);

        surveyOptionsField.getChildren().add(box);
    }

    public void deleteOption(ActionEvent event) {
        Parent employeeToDelete = ((Node)event.getSource()).getParent();
        System.out.print(employeeToDelete);
        surveyOptionsField.getChildren().remove(employeeToDelete);
    }
}
