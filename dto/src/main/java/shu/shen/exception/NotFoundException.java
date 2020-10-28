package shu.shen.exception;

/**
 * 修改的东西必须要存在
 **/
public class NotFoundException extends BaseException {

    public NotFoundException(int errorCode, String message) {
        super(errorCode, message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
