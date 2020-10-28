import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
//[Java 8实现BASE64编解码_chszs的专栏-CSDN博客 ](https://blog.csdn.net/chszs/article/details/17027649)
public class Base64Test {
    @Test
    public void test() throws UnsupportedEncodingException {
        // 编码
        String asB64 = Base64.getEncoder().encodeToString("some string".getBytes("utf-8"));
        System.out.println(asB64); // 输出为: c29tZSBzdHJpbmc=

// 解码
        byte[] asBytes = Base64.getDecoder().decode("c29tZSBzdHJpbmc=");
        System.out.println(new String(asBytes, "utf-8")); // 输出为: some string
    }

}
