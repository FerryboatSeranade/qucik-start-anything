package shu.shen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseDTO<T> {

    private Integer rtn;
    private String message;
    private T data;
    private Long timestamp = System.currentTimeMillis();

    /**
     * Constructor
     * @param rtn 返回码
     * @param message 概要
     * @param data 数据
     */
    public ResponseDTO(Integer rtn, String message, T data){
        this.rtn = rtn;
        this.message = message;
        this.data = data;
    }

    public static ResponseDTO success(){
        return new ResponseDTO<>(0, "success", null);
    }

    public static <R> ResponseDTO<R> success(R data){
        return new ResponseDTO<>(0, "success", data);
    }

    public static ResponseDTO error(){
        return new ResponseDTO<>(1, "error", null);
    }

    public static ResponseDTO error(String message){
        return new ResponseDTO<>(1, message, null);
    }

    public static ResponseDTO error(Integer rtn, String message){
        return new ResponseDTO<>(rtn, message, null);
    }

    public static boolean isSuccess(ResponseDTO dto, boolean checkData){
        if(dto == null){
            return false;
        }
        if(checkData && dto.getData() == null){
            return false;
        }
        return true;
    }

    /**
     * 检查response dto是否是success返回
     * @param dto response dto
     * @return
     */
    public static boolean isSuccess(ResponseDTO dto){
        return ResponseDTO.isSuccess(dto, false);
    }

    /**
     * 获取错误信息
     * @param dto
     * @return
     */
    public static String getErrorMessage(ResponseDTO dto){
        return dto != null? dto.getMessage() : "";
    }

    /**
     * 获取rtn
     * @param dto
     * @return
     */
    public static Integer getRtn(ResponseDTO dto){
        return dto != null && dto.getRtn() != null ? dto.getRtn() : null;
    }
}
