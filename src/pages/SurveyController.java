package pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;
import survey.Survey;
import survey.SurveyOption;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static pages.Router.switchToPage;

public class SurveyController {
    @FXML
    private Text surveyName;
    @FXML
    private VBox surveyOptionsBox;
    private ToggleGroup optionsGroup;

    @FXML
    public void initialize() {
        Survey openedSurvey = Main.currentSurvey;
        surveyName.setText(openedSurvey.getSurveyName());

        surveyOptionsBox.setSpacing(20);
        optionsGroup = new ToggleGroup();

        setupOptions(openedSurvey);
    }

    private void setupOptions(Survey openedSurvey) {
        ArrayList<SurveyOption> surveyOptions = openedSurvey.getSurveyOption();
        surveyOptionsBox.getChildren().clear();

        for (SurveyOption opt: surveyOptions) {
            AnchorPane box = new AnchorPane();
            box.prefWidth(500);
            box.prefHeight(50);

            RadioButton rbtn = new RadioButton(opt.getName());
            rbtn.setId(opt.getOptionId());
            rbtn.setToggleGroup(optionsGroup);
            rbtn.setFont(Font.font(18));
            rbtn.setSelected(opt.containsVoter(Main.getCurrentUser()));
            rbtn.setLayoutY(10);

            Label percent = new Label();
            percent.setText(new DecimalFormat("#.##").format(opt.percentage * 100) + "%");
            percent.setLayoutX(450);
            percent.setLayoutY(10);

            box.getChildren().addAll(rbtn, percent);
            surveyOptionsBox.getChildren().add(box);
        }
    }

    @FXML
    private void vote() {
        RadioButton chosenBtn = (RadioButton) optionsGroup.getSelectedToggle();
        String id = chosenBtn.getId();
        Main.getSurveyManager().voteInSurvey(id);
        setupOptions(Main.currentSurvey);
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        switchToPage(event, "main.fxml");
    }
}
