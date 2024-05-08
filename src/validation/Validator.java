package validation;

public class Validator<T> {
    ErrorHandler validator;
    public Validator(ErrorHandler handler) {
        this.validator = handler;
    }

    public void validate() {
        validator.validate();
    }

    public void add(T field) {
        this.validator.add(field);
    }
    public void delete(T field) {
        this.validator.delete(field);
    }
}
