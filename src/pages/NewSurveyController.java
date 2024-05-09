package pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import main.Main;
import validation.*;
import validation.components.CListView;
import validation.components.CTextField;
import validation.components.CVBox;
import validation.components.ValidationComponent;

import java.io.IOException;
import java.util.ArrayList;

public class NewSurveyController implements Controller {
    @FXML
    private CTextField surveyNameField;
    @FXML
    private TextArea surveyDescriptionField;
    @FXML
    private CListView teamsBox;
    @FXML
    private CVBox surveyOptionsField;
    @FXML
    private CheckBox multipleChoiceSetter;
    @FXML
    private Label errorMsg;
    private Main main;

    private Validator<ValidationComponent> emptyFieldValidator;
    private Validator<ValidationComponent>  shortFieldValidator;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    public void initialize() {
        this.emptyFieldValidator = new Validator<ValidationComponent>(new EmptyValidation<ValidationComponent>());
        this.shortFieldValidator = new Validator<ValidationComponent>(new ShortValidation<ValidationComponent>());

        ObservableList<String> items = FXCollections.observableArrayList(
                "product", "manager", "design", "frontend", "backend", "tester"
        );
        teamsBox.setItems(items);
        teamsBox.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        teamsBox.setName("Teams field");
        emptyFieldValidator.add(teamsBox);

        surveyNameField.setName("Survey field name");
        emptyFieldValidator.add(surveyNameField);
        shortFieldValidator.add(surveyNameField);

        surveyOptionsField.setName("Options box");
        emptyFieldValidator.add(surveyOptionsField);
    }

    @FXML
    private void createSurvey(ActionEvent event) throws IOException {
        try {
            errorMsg.setText("");
            emptyFieldValidator.validate();
            shortFieldValidator.validate();

            String name = surveyNameField.getText();

            ArrayList<String> selectedTeams = new ArrayList<>(teamsBox.getSelectionModel().getSelectedItems());
            ArrayList<String> options = new ArrayList<>();

            for (Node option: surveyOptionsField.getChildren()) {
                AnchorPane box = (AnchorPane) option;
                for (javafx.scene.Node node : box.getChildren()) {
                    if (node instanceof TextField) {
                        String optionName = ((TextField) node).getText();
                        options.add(optionName);
                    }
                }
            }

            boolean isMultipleChoice = multipleChoiceSetter.isSelected();

            main.getSurveyManager().createNewSurvey(name, selectedTeams, options, isMultipleChoice);
            main.router.switchToPage(event, "main.fxml");
        } catch (CustomFieldException err) {
            System.out.println(err.getMessage());
            errorMsg.setText(err.getMessage());
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        main.router.switchToPage(event, "main.fxml");
    }

    @FXML
    private void addOption(ActionEvent event) {
        AnchorPane box = new AnchorPane();
        box.prefWidth(600);

        CTextField optionName =  new CTextField();
        optionName.setPromptText("option");
        optionName.setLayoutY(5);
        optionName.setLayoutX(5);
        optionName.setPrefWidth(500);
        optionName.setFont(Font.font(14));
        optionName.getStyleClass().add("optionName");
        surveyNameField.setName("Option field");
        emptyFieldValidator.add(optionName);
        shortFieldValidator.add(optionName);

        Button removeBtn = new Button();
        removeBtn.setText("remove");
        removeBtn.setFont(Font.font(14));
        removeBtn.setOnAction(btnEvent -> deleteOption(optionName, btnEvent));
        removeBtn.setLayoutY(5);
        removeBtn.setLayoutX(550);

        box.getChildren().addAll(optionName, removeBtn);

        surveyOptionsField.getChildren().add(box);
    }

    public void deleteOption(CTextField field, ActionEvent event) {
        Parent employeeToDelete = ((Node)event.getSource()).getParent();
        System.out.print(employeeToDelete);
        surveyOptionsField.getChildren().remove(employeeToDelete);
        emptyFieldValidator.delete(field);
        shortFieldValidator.delete(field);
    }
}
