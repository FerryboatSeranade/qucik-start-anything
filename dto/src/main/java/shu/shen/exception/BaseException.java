package shu.shen.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基本异常,本质上就是给运行时异常包装了一个errorCode属性
 *
 * @author jwyang
 * 2019-03-14 下午4:40
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class BaseException extends RuntimeException{

    /**
     * 错误状态码
     */
    private int errorCode;

    /**
     * 构造一个基本异常.
     *
     * @param message 信息描述
     */
    public BaseException(int errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 构造一个基本异常.
     *
     * @param message 信息描述
     * @param cause   根异常类（可以存入任何异常）
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
