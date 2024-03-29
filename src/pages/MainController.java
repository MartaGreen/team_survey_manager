package pages;

import account.User;
import employees.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
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
    private ComboBox switchBetweenUsersBar;
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
        };
        switchBetweenUsersBar.setValue(getUserFullName(this.user));
        switchToUser();

        ArrayList<Survey> openedSurveys = user.getOpenedSurveys();
        ArrayList<Survey> personalSurveys = user.getPersonalSurveys();

        System.out.print("Opened surveys " + openedSurveys + "\n");
        openedSurveysBox.getChildren().clear();
        if (openedSurveysBox != null) {
            openedSurveys.forEach(survey -> openedSurveysBox.getChildren().add(new Text(survey.getSurveyName())));
        }

        System.out.print("Personal surveys " + personalSurveys + "\n");
        personalSurveysBox.getChildren().clear();
        if (personalSurveys != null) {
            personalSurveys.forEach(survey -> personalSurveysBox.getChildren().add(new Text(survey.getSurveyName())));
        }
    }

    private String getUserFullName(Employee user) {
        return (user.getName() + " " + user.getSurname());
    }

    private void switchToUser() {
        switchBetweenUsersBar.setOnAction(event -> {
            String selectedUser = (String) switchBetweenUsersBar.getSelectionModel().getSelectedItem();
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
