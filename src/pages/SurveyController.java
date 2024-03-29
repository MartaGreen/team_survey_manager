package pages;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import main.Main;
import survey.Survey;

public class SurveyController {
    @FXML
    private Text surveyName;

    @FXML
    public void initialize() {
        Survey openedSurvey = Main.getCurrentUser().openedSurvey;

        surveyName.setText(openedSurvey.getSurveyName());
    }
}
