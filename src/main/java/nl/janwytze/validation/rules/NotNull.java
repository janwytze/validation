package nl.janwytze.validation.rules;

import nl.janwytze.validation.AbstractRule;
import nl.janwytze.validation.Validator;

public class NotNull extends AbstractRule {

    /**
     * Confirmed constructor.
     *
     * @param parameters The rule parameter.
     */
    public NotNull(String[] parameters) {
        super(parameters);
    }

    /**
     * Validate the rule parameters.
     *
     * @throws IllegalArgumentException Thrown when the rule parameters are invalid.
     */
    protected void validateParameters() throws IllegalArgumentException {
        if (parameters.length != 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Validate a certain value.
     *
     * @param validator The validator, this instance can be used to for cross-field validation.
     * @param value The value to validate.
     * @return False when failed.
     */
    public boolean validate(Validator validator, Object value) {
        return (value != null && value != "");
    }
}
