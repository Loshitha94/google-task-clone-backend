package lk.ijse.dep8.tasks.service.exception;

public class FailedToExecuteException extends RuntimeException{

    public FailedToExecuteException() {
        super();
    }

    public FailedToExecuteException(String message) {
        super(message);
    }

    public FailedToExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToExecuteException(Throwable cause) {
        super(cause);
    }
}
