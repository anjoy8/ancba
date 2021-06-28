package club.neters.core.exception;

/**
 * custom exception
 *
 * @author maven plugin
 * @date 2021/6/16
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
