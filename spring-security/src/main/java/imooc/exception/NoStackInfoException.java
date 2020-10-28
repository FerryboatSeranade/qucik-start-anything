package imooc.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;

/**
 * NoStackInfoException
 *
 * @title: NoStackInfoException
 * @Author shu.shen
 * @Date: 2020/10/24 2:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoStackInfoException extends RuntimeException {
    private Integer retCd;
    private String msgDes;

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]",this.retCd,this.msgDes);
    }
}
