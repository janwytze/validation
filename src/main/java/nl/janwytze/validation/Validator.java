package nl.janwytze.validation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    /**
     * The object to validate.
     */
    private Object object;

    /**
     * Validator constructor.
     *
     * @param object The object to validate.
     */
    public Validator(Object object) {
        this.object = object;
    }

    /**
     * Validate the object.
     *
     * @throws ValidationFailedException Thrown when validation failed.
     */
    public void validate() throws ValidationFailedException {
        // List with all failed fields/rules.
        Map<String, List<String>> failedList = new HashMap<String, List<String>>();

        for (Field field: this.object.getClass().getDeclaredFields()) {
            // If the Validate annotation is preset try validating the field.
            if (field.isAnnotationPresent(Validate.class)) {
                // Set accessible to true so private/protected fields are accessible.
                field.setAccessible(true);

                try {
                    this.validateField(field);
                } catch (FieldFailedException exception) {
                    // Add the message to the failed list when the validation failed.
                    failedList.put(exception.getName(), exception.getMessages());
                }
            }
        }

        // Throw exception when rules have failed.
        if (failedList.size() > 0) {
            throw new ValidationFailedException(failedList);
        }
    }

    /**
     * Validate a field.
     *
     * @param field The field to validate.
     * @throws FieldFailedException Thrown
     */
    private void validateField(Field field) throws FieldFailedException {
        Validate validateAnnotation = field.getAnnotation(Validate.class);
        List<String> messages = new ArrayList<String>();

        for (Rule rule: validateAnnotation.rules()) {
            try {
                Constructor<?> constructor = rule.rule().getConstructor(String[].class);
                AbstractRule validationRule = (AbstractRule) constructor.newInstance(new Object[]{rule.parameters()});

                if (!validationRule.validate(this, field.get(this.object))) {
                    // Throw exception so the next catch will process the validation failure.
                    throw new Exception();
                }
            } catch (Exception exception) {
                messages.add(rule.message());
            }
        }

        if (messages.size() > 0) {
            // Throw exception when validation errors are found.
            throw new FieldFailedException(field.getName(), messages);
        }
    }

    /**
     * Validate an object.
     *
     * @param object The object to validation.
     * @throws ValidationFailedException Thrown when validation failed.
     */
    public static void validateObject(Object object) throws ValidationFailedException {
        (new Validator(object)).validate();
    }

    /**
     * Get a value from the object to validate.
     * With this method cross-field validation is possible.
     *
     * @param fieldName The field to find.
     * @return The field value.
     */
    public Object getValue(String fieldName) {
        try {
            Field field = this.object.getClass().getDeclaredField(fieldName);

            // Set accessible to true so private/protected fields are accessible.
            field.setAccessible(true);

            return field.get(this.object);
        } catch (Exception exception) {
            return null;
        }
    }
}
