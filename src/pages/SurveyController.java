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

public class SurveyController implements Controller {
    @FXML
    private Text surveyName;
    @FXML
    private VBox surveyOptionsBox;
    private ToggleGroup optionsGroup;
    private Main main;

    public void setMain(Main main) {
        this.main = main;
        System.out.print("SURVEY CONTROLLER SURVEY CONTROLLER " + main + "\n");

        Survey openedSurvey = main.currentSurvey;
        surveyName.setText(openedSurvey.getSurveyName());

        surveyOptionsBox.setSpacing(20);
        optionsGroup = new ToggleGroup();

        setupOptions(openedSurvey);
    }

    // setup options data on the page
    private void setupOptions(Survey openedSurvey) {
        ArrayList<SurveyOption> surveyOptions = openedSurvey.getSurveyOption();
        surveyOptionsBox.getChildren().clear();

        for (SurveyOption opt: surveyOptions) {
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
            }
            else {
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

    @FXML
    private void vote() {
        ArrayList<String>ids = new ArrayList<>();

        for (Node node: surveyOptionsBox.getChildren()) {
            AnchorPane box = (AnchorPane) node;
            for (Node elem: box.getChildren()) {
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

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        main.router.switchToPage(event, "main.fxml");
    }
}
