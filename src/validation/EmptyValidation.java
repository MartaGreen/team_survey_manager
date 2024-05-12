package validation;

import validation.components.CListView;
import validation.components.CTextField;
import validation.components.CVBox;

import java.util.ArrayList;

/**
 * Class for validating field for emptiness.
 *
 * @param <T> The type of field to be validated.
 */
public class EmptyValidation<T> implements ErrorHandler<T> {
    private ArrayList<T> validationFields;

    /**
     * Constructor for EmptyValidation
     */
    public EmptyValidation() {
        this.validationFields = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate() {
        validationFields.forEach(this::startValidation);
    }

    /**
     * Validates provided field for emptiness.
     * @param field field of custom type to be validated
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T field) {
        validationFields.add(field);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T field) {
        validationFields.remove(field);
    }
}