package nl.janwytze.validation;

import java.util.List;

public class FieldFailedException extends Exception {

    /**
     * The field name.
     */
    private String name;

    /**
     * The validation error messages.
     */
    private List<String> messages;

    /**
     * FieldFailedException constructor.
     *
     * @param name The field name.
     * @param messages The field error messages.
     */
    public FieldFailedException(String name, List<String> messages) {
        super(String.format("Validation failed for %s: %s", name, messages));
        this.name = name;
        this.messages = messages;
    }

    /**
     * Get the field name.
     *
     * @return The field name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the validation error messages.
     *
     * @return The error messages.
     */
    public List<String> getMessages() {
        return this.messages;
    }
}
