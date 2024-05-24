package validation.components;

import javafx.scene.layout.VBox;

public class CVBox extends VBox implements ValidationComponent {
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
    @Override
    public void highlight() {
        this.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
    }

    /** {@inheritDoc} */
    @Override
    public void unhighlight() {
        this.setStyle("-fx-border-color: none; -fx-border-width: 0px;");
    }
}
