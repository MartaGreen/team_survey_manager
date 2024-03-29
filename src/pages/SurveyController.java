package pages;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;
import survey.Survey;

import java.util.ArrayList;

public class SurveyController {
    @FXML
    private Text surveyName;
    @FXML
    private VBox surveyOptionsBox;

    @FXML
    public void initialize() {
        Survey openedSurvey = Main.getCurrentUser().openedSurvey;
        surveyName.setText(openedSurvey.getSurveyName());
        ArrayList<String> surveyOptions = openedSurvey.getSurveyOption();

        surveyOptionsBox.setSpacing(20);
        ToggleGroup group = new ToggleGroup();
        for (String opt: surveyOptions) {
            RadioButton rbtn = new RadioButton(opt);
            rbtn.setToggleGroup(group);
            rbtn.setText(opt);
            rbtn.setFont(Font.font(18));
            surveyOptionsBox.getChildren().add(rbtn);
        }
    }
}
