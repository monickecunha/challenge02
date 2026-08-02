package test;

public class TestCase {
    private String message;
    private boolean expectedFraud;

    public TestCase(String message, boolean expectedFraud) {
        this.message = message;
        this.expectedFraud = expectedFraud;
    }

    public String getMessage() {
        return message;
    }

    public boolean isExpectedFraud() {
        return expectedFraud;
    }
}
