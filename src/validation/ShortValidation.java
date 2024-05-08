package validation;

import validation.components.CTextField;

import java.util.ArrayList;

public class ShortValidation<T> implements ErrorHandler<T>{
    private ArrayList<T> validationFields;

    public ShortValidation() {
        this.validationFields = new ArrayList<>();
    }

    @Override
    public void validate() {
        validationFields.forEach(this::startValidation);
    }

    private void startValidation(T field) {
        System.out.print(field + "\n");

        if (field instanceof CTextField textField) {
            String fieldText = textField.getText();
            if (fieldText.length() < 4) throw new ShortException(textField.getName(), textField);
        }
//        else if (field instanceof TextArea) {
//            String fieldText = ((TextArea) field).getText();
//            if (fieldText.length() < 25) throw new ShortFieldException(fieldText, field);
//        }
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
