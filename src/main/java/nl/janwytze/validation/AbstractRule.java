package nl.janwytze.validation;

abstract public class AbstractRule {

    /**
     * The rule parameters.
     */
    protected String[] parameters;

    /**
     * AbstractRule constructor.
     *
     * @param parameters The rule parameters.
     */
    public AbstractRule(String[] parameters) {
        this.parameters = parameters;

        // Validate the Rule parameters.
        this.validateParameters();
    }

    /**
     * Validate the rule parameters.
     *
     * @throws IllegalArgumentException Thrown when the rule parameters are invalid.
     */
    protected abstract void validateParameters() throws IllegalArgumentException;

    /**
     * Validate a certain value.
     *
     * @param validator The validator, this instance can be used to for cross-field validation.
     * @param value The value to validate.
     * @return False when failed.
     */
    public abstract boolean validate(Validator validator, Object value);
}
