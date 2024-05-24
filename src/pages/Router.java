package pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

/**
 * Class responsible for routing (navigation) between different pages in a JavaFX application.
 */
public class Router {
    /** The current state of app. */
    Main main;

    /**
     * Constructor for the Router class.
     *
     * @param main The main class of the application, containing resources and the application's state.
     */
    public Router(Main main) {
        this.main = main;
    }

    /**
     * Load the initial page of the application.
     *
     * @param stage         The main stage of the application.
     * @throws IOException  If an input/output error occurs while loading the FXML file.
     */
    public void loadInitialPage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Router.class.getResource("main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setMain(main);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switch to another page.
     *
     * @param event         The event that triggered the page switch.
     * @param pageRoute     The route to the FXML file of the page.
     * @throws IOException  If an input/output error occurs while loading the FXML file.
     */
    public void switchToPage(ActionEvent event, String pageRoute) throws IOException {
        FXMLLoader loader = new FXMLLoader(Router.class.getResource(pageRoute));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setMain(this.main);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
