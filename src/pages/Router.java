package pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class Router {
    Main main;

    public Router(Main main) {
        this.main = main;
    }
    public void loadInitialPage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Router.class.getResource("main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setMain(main);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToPage(ActionEvent event, String pageRoute) throws IOException {
        FXMLLoader loader = new FXMLLoader(Router.class.getResource(pageRoute));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setMain(this.main);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
