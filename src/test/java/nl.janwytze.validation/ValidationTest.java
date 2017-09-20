package nl.janwytze.validation;

import nl.janwytze.validation.rules.Confirmed;
import nl.janwytze.validation.rules.NotNull;
import org.junit.Test;

public class ValidationTest {

    /**
     * Create a test case and validate the object.
     * The case given should be valid and not throw an exception.
     *
     * @throws ValidationFailedException Thrown on validate fail.
     */
    @Test
    public void validationTest() throws ValidationFailedException {
        TestCase testCase = new TestCase();
        testCase.name = "Jan Wytze Zuidema";
        testCase.password = "password";
        testCase.passwordConfirmation = "password";

        Validator.validateObject(testCase);
    }

    private class TestCase {
        @Validate(rules = @Rule(rule = NotNull.class, message = "Name is empty"))
        private String name;

        @Validate(rules = {
                @Rule(rule = NotNull.class, message = "Password is empty"),
                @Rule(rule = Confirmed.class, parameters = "passwordConfirmation", message = "Password confirmation is not the same"),
        })
        private String password;

        @Validate(rules = @Rule(rule = NotNull.class, message = "password confirmation is empty"))
        private String passwordConfirmation;
    }
}
