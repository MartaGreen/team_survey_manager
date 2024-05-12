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

/**
 * Controller for a new survey page.
 */
public class NewSurveyController implements Controller {
    /** The custom text field for the survey name. */
    @FXML
    private CTextField surveyNameField;

    /** The text area for the survey description. */
    @FXML
    private TextArea surveyDescriptionField;

    /** The custom list for selecting teams. */
    @FXML
    private CListView teamsBox;

    /** The container for survey options. */
    @FXML
    private CVBox surveyOptionsField;

    /** The checkbox for selecting multiple choice. */
    @FXML
    private CheckBox multipleChoiceSetter;

    /** The label for displaying error messages. */
    @FXML
    private Label errorMsg;

    /** The main instance. */
    private Main main;

    /** The validator for empty fields. */
    private Validator<ValidationComponent> emptyFieldValidator;

    /** The validator for short fields. */
    private Validator<ValidationComponent> shortFieldValidator;

    /**
     * Sets the main to get data about current state of app.
     *
     * @param main The main instance.
     */
    public void setMain(Main main) {
        this.main = main;
        setupPage();
    }

    /**
     * Sets up new survey page.
     */
    public void setupPage() {
        this.emptyFieldValidator = new Validator<>(new EmptyValidation<>());
        this.shortFieldValidator = new Validator<>(new ShortValidation<>());

        ObservableList<String> items = FXCollections.observableArrayList(
                "product", "manager", "design", "frontend", "backend", "tester"
        );
        teamsBox.setItems(items);
        teamsBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        teamsBox.setName("Teams field");
        emptyFieldValidator.add(teamsBox);

        surveyNameField.setName("Survey field name");
        emptyFieldValidator.add(surveyNameField);
        shortFieldValidator.add(surveyNameField);

        surveyOptionsField.setName("Options box");
        emptyFieldValidator.add(surveyOptionsField);
    }

    /**
     * Creates a new survey.
     *
     * @param event The action event triggered by clicking the "Create Survey" button.
     * @throws IOException If an I/O exception occurs while switching to the main page.
     */
    @FXML
    private void createSurvey(ActionEvent event) throws IOException {
        try {
            errorMsg.setText("");
            emptyFieldValidator.validate();
            shortFieldValidator.validate();

            String name = surveyNameField.getText();

            ArrayList<String> selectedTeams = new ArrayList<>(teamsBox.getSelectionModel().getSelectedItems());
            ArrayList<String> options = new ArrayList<>();

            for (Node option : surveyOptionsField.getChildren()) {
                AnchorPane box = (AnchorPane) option;
                for (Node node : box.getChildren()) {
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

    /**
     * Navigates back to the main page.
     *
     * @param event The action event triggered by clicking the "Go Back" button.
     * @throws IOException If an I/O exception occurs while switching to the main page.
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        main.router.switchToPage(event, "main.fxml");
    }

    /**
     * Adds an option in the survey creation field.
     */
    @FXML
    private void addOption() {
        AnchorPane box = new AnchorPane();
        box.prefWidth(600);

        CTextField optionName = new CTextField();
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

    /**
     * Deletes an option from the survey creation field.
     *
     * @param field The text field representing the option.
     * @param event The action event triggered by clicking the "Remove" button.
     */
    public void deleteOption(CTextField field, ActionEvent event) {
        Parent employeeToDelete = ((Node) event.getSource()).getParent();
        System.out.print(employeeToDelete);
        surveyOptionsField.getChildren().remove(employeeToDelete);
        emptyFieldValidator.delete(field);
        shortFieldValidator.delete(field);
    }
}