package pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Router {
    public static void loadInitialPage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Router.class.getResource("main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void switchToPage(ActionEvent event, String pageRoute) throws IOException {
        FXMLLoader loader = new FXMLLoader(Router.class.getResource(pageRoute));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
