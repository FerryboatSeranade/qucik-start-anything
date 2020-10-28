import java.time.format.DateTimeFormatter;

public class TimeConstant {

    private TimeConstant() {
        throw new IllegalStateException("Constant class");
    }

    public static final DateTimeFormatter RFC3339Formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z'");

    public static final DateTimeFormatter DateFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
