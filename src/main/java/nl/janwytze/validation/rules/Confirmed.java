package nl.janwytze.validation.rules;

import nl.janwytze.validation.AbstractRule;
import nl.janwytze.validation.Validator;

public class Confirmed extends AbstractRule {

    public Confirmed(String[] parameters) {
        super(parameters);
    }

    protected void validateParameters() throws IllegalArgumentException {
        if (parameters.length != 1) {
            throw new IllegalArgumentException();
        }
    }

    public boolean validate(Validator validator, Object value) {
        return (validator.getValue(this.parameters[0]).equals(value));
    }
}
