package nl.janwytze.validation;

import java.util.List;
import java.util.Map;

public class ValidationFailedException extends Exception {

    private Map<String, List<String>> failedList;

    public ValidationFailedException(Map<String, List<String>> failedList) {
        super("Validation rules failed: " + failedList.toString());
        this.failedList = failedList;
    }

    public Map<String, List<String>> getFailedList() {
        return this.failedList;
    }
}
