package shu.shen.exception;

/**
 * 重复添加
 **/
public class ExistException extends BaseException {

    public ExistException(int errorCode, String message) {
        super(errorCode, message);
    }

    public ExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
