package validation;

public interface ErrorHandler<T> {
    void validate();
    void add(T field);
    void delete(T field);
}