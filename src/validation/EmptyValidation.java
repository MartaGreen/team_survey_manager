package validation;

import validation.components.CListView;
import validation.components.CTextField;
import validation.components.CVBox;

import java.util.ArrayList;

public class EmptyValidation<T> implements ErrorHandler<T> {
    private ArrayList<T> validationFields;

    public EmptyValidation() {
        this.validationFields = new ArrayList<>();
    }

    @Override
    public void validate() {
        validationFields.forEach(this::startValidation);
    }

    private void startValidation(T field) {
        if (field instanceof CTextField textField) {
            textField.unhightlight();
            String fieldText = textField.getText();
            if (fieldText.isEmpty()) throw new EmptyException(textField.getName(), textField);
        }
        else if (field instanceof CListView listView) {
            listView.unhightlight();
            boolean isFieldEmpty = listView.getSelectionModel().getSelectedIndices().isEmpty();
            if (isFieldEmpty) throw new EmptyException(listView.getName(), listView);
        }
        else if (field instanceof CVBox vBox) {
            vBox.unhightlight();
            boolean isFieldEmpty = vBox.getChildren().isEmpty();
            if (isFieldEmpty) throw new EmptyException(vBox.getName(), vBox);
        }
    }

    @Override
    public void add(T field) {
        validationFields.add(field);
    }

    @Override
    public void delete(T field) {
        validationFields.remove(field);
    }
}
