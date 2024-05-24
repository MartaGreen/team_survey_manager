package pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;
import survey.MultipleChoiceSurvey;
import survey.Survey;
import survey.SurveyOption;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Controller survey interactions page.
 */
public class SurveyController implements Controller {
    /** The text field for the survey name. */
    @FXML
    private Text surveyName;
    /** The text field for the survey description. */
    @FXML
    private Text descriptionField;

    /** The container for survey options. */
    @FXML
    private VBox surveyOptionsBox;

    /** The toggle group container for options. */
    private ToggleGroup optionsGroup;

    /** The current state of app. */
    private Main main;

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
     * Sets up the survey page.
     */
    public void setupPage() {
        System.out.print("SURVEY CONTROLLER SURVEY CONTROLLER " + main + "\n");

        Survey openedSurvey = main.currentSurvey;
        surveyName.setText(openedSurvey.getSurveyName());
        descriptionField.setText(openedSurvey.getSurveyDescription());

        surveyOptionsBox.setSpacing(20);
        optionsGroup = new ToggleGroup();

        setupOptions(openedSurvey);
    }

    /**
     * Collect options data for survey's voting
     * @param openedSurvey current survey
     */
    private void setupOptions(Survey openedSurvey) {
        ArrayList<SurveyOption> surveyOptions = openedSurvey.getSurveyOption();
        surveyOptionsBox.getChildren().clear();

        for (SurveyOption opt : surveyOptions) {
            AnchorPane box = new AnchorPane();
            box.prefWidth(500);
            box.prefHeight(50);

            Label percent = new Label();
            percent.setText(new DecimalFormat("#.##").format(opt.percentage * 100) + "%");
            percent.setLayoutX(450);
            percent.setLayoutY(10);

            if (openedSurvey instanceof MultipleChoiceSurvey) {
                CheckBox checkbox = new CheckBox(opt.getName());
                checkbox.setId(opt.getOptionId());
                checkbox.setFont(Font.font(18));
                checkbox.setSelected(opt.containsVoter(main.getCurrentUser()));
                checkbox.setLayoutY(10);
                box.getChildren().addAll(checkbox, percent);
            } else {
                RadioButton rbtn = new RadioButton(opt.getName());
                rbtn.setId(opt.getOptionId());
                rbtn.setToggleGroup(optionsGroup);
                rbtn.setFont(Font.font(18));
                rbtn.setSelected(opt.containsVoter(main.getCurrentUser()));
                rbtn.setLayoutY(10);
                box.getChildren().addAll(rbtn, percent);
            }

            surveyOptionsBox.getChildren().add(box);
        }
    }

    /**
     * Handles voting in the survey.
     */
    @FXML
    private void vote() {
        ArrayList<String> ids = new ArrayList<>();

        for (Node node : surveyOptionsBox.getChildren()) {
            AnchorPane box = (AnchorPane) node;
            for (Node elem : box.getChildren()) {
                if (elem instanceof RadioButton && ((RadioButton) elem).isSelected())
                    ids.add(elem.getId());
                if (elem instanceof CheckBox && ((CheckBox) elem).isSelected())
                    ids.add(elem.getId());
            }
        }

        main.getSurveyManager().voteInSurvey(ids);
        // update options data on the page
        setupOptions(main.currentSurvey);
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
}