package shu.shen.exception;

/**
 * 重复删除
 **/
public class DuplicateException extends BaseException {

    public DuplicateException(int errorCode, String message) {
        super(errorCode, message);
    }

}
