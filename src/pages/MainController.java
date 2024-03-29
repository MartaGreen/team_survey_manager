package pages;

import account.User;
import employees.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;
import survey.Survey;

import java.io.IOException;
import java.util.ArrayList;

import static pages.Router.switchToPage;

public class MainController implements Controller {
    @FXML
    private Text fullName;
    @FXML
    private Text teamLabel;
    @FXML
    private ComboBox<String> switchBetweenUsersBar;
    @FXML
    private VBox personalSurveysBox;
    @FXML
    private VBox openedSurveysBox;

    private User user;

    public void initialize() {
        setupPage();
    }

    public void setupPage() {
        this.user = Main.getCurrentUser();
//        user.update();

        fullName.setText(getUserFullName(user));
        teamLabel.setText(user.getTeam());
        ArrayList<Employee> employees = Main.getCorporation().getEmployees();
        for (Employee employee: employees) {
            switchBetweenUsersBar.getItems().add(getUserFullName(employee));
        }
        switchBetweenUsersBar.setValue(getUserFullName(this.user));
        switchToUser();

        ArrayList<Survey> openedSurveys = user.getOpenedSurveys();
        ArrayList<Survey> personalSurveys = user.getPersonalSurveys();

        System.out.print("Opened surveys " + openedSurveys + "\n");
        openedSurveysBox.getChildren().clear();
        if (openedSurveysBox != null) {
            openedSurveys.forEach(this::createSurveyField);
        }

        System.out.print("Personal surveys " + personalSurveys + "\n");
        personalSurveysBox.getChildren().clear();
        if (personalSurveys != null) {
            personalSurveys.forEach(survey -> personalSurveysBox.getChildren().add(new Text(survey.getSurveyName())));
        }
    }

    private void createSurveyField(Survey survey) {
        AnchorPane surveyContainer = new AnchorPane();
        surveyContainer.prefWidth(700);
        surveyContainer.setId(survey.getSurveyId());

        Text surveyName = new Text(survey.getSurveyName());
        surveyName.prefWidth(500);
        surveyName.setText(survey.getSurveyName());
        surveyName.setLayoutY(20);
        surveyName.setFont(Font.font(16));

        Button voteBtn = new Button("vote");
        voteBtn.setLayoutX(650);
        voteBtn.setLayoutY(10);

        surveyContainer.getChildren().addAll(surveyName, voteBtn);

        voteBtn.setOnAction(event -> {
            try {
                user.vote(survey.getSurveyId());
                switchToPage(event, "survey.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        openedSurveysBox.getChildren().add(surveyContainer);
    }

    private String getUserFullName(Employee user) {
        return (user.getName() + " " + user.getSurname());
    }

    private void switchToUser() {
        switchBetweenUsersBar.setOnAction(event -> {
            String selectedUser = switchBetweenUsersBar.getSelectionModel().getSelectedItem();
            System.out.println(selectedUser);
            switchBetweenUsersBar.setValue(selectedUser);

            Main.setCurrentUser(selectedUser);

            setupPage();
        });
    }

    @FXML
    private void createNewSurvey(ActionEvent event) throws IOException {
        switchToPage(event, "newSurvey.fxml");
    }
}
