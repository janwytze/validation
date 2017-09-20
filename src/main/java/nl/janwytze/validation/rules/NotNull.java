package nl.janwytze.validation.rules;

import nl.janwytze.validation.AbstractRule;
import nl.janwytze.validation.Validator;

public class NotNull extends AbstractRule {

    public NotNull(String[] parameters) {
        super(parameters);
    }

    protected void validateParameters() throws IllegalArgumentException {
        if (parameters.length != 0) {
            throw new IllegalArgumentException();
        }
    }

    public boolean validate(Validator validator, Object value) {
        return (value != null && value != "");
    }
}
