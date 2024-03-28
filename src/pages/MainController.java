package pages;

import account.User;
import employees.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Main;

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

        fullName.setText(getUserFullName(user));
        teamLabel.setText(user.getTeam());
        ArrayList<Employee> employees = Main.getCorporation().getEmployees();
        for (Employee employee: employees) {
            switchBetweenUsersBar.getItems().add(getUserFullName(employee));
        };
        switchBetweenUsersBar.setValue(getUserFullName(this.user));
        switchToUser();
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
}
