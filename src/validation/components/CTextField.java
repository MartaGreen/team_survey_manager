package validation.components;

import javafx.scene.control.TextField;

public class CTextField extends TextField implements ValidationComponent {
    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    public void hightlight() {
        this.setStyle("-fx-border-color: red");
    }
    public void unhightlight() {
        this.setStyle("-fx-border-color: none");
    }
}
