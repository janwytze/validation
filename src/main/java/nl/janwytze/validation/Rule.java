package nl.janwytze.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Rule {
    /**
     * Message on validation failure.
     *
     * @return The fail message.
     */
    String message();

    /**
     * The rule to validate.
     *
     * @return THe rule.
     */
    Class<? extends AbstractRule> rule();

    /**
     * The parameters to parse to the rule constructor.
     *
     * @return The parameters.
     */
    String[] parameters() default {};
}
