package hello;

public class MongoConnectionException extends Exception {
    private static final long serialVersionUUID = 1l;
    private String errorMessage;

    public MongoConnectionException(String message, Throwable cause) {
        super(message, cause);
        this.errorMessage = message;
    }

    public MongoConnectionException() {
    }
}
