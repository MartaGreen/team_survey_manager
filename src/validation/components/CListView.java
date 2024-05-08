package validation.components;

import javafx.scene.control.ListView;

public class CListView extends ListView<String> implements ValidationComponent{
    private String name;
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void hightlight() {
        this.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
    }
    public void unhightlight() {
        this.setStyle("-fx-border-color: none; -fx-border-width: 2px;");
    }
}
