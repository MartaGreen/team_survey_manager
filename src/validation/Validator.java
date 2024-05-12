package validation;

public class Validator<T> {
    private final ErrorHandler<T> validator;
    public Validator(ErrorHandler<T> handler) {
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
