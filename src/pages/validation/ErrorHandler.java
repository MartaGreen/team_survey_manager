package pages.validation;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public interface ErrorHandler {
    public void validate(TextField field, String fieldName);
}
