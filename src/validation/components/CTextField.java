package validation.components;

import javafx.scene.control.TextField;

public class CTextField extends TextField implements ValidationComponent {
    /** Custom component name. */
    private String name;

    /** {@inheritDoc} */
    @Override
    public void setName(String name) {
        this.name = name;
    }
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return this.name;
    }

    /** {@inheritDoc} */
    public void highlight() {
        this.setStyle("-fx-border-color: red");
    }
    /** {@inheritDoc} */
    public void unhighlight() {
        this.setStyle("-fx-border-color: none");
    }
}
