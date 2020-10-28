
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static ObjectMapper objectMapper;

    private static ObjectMapper objectMapperWithNull;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(new LocalDateTimeSerializer());
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        /*
        simpleModule.addSerializer(new LocalDateSerializer());
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
         */
        objectMapper.registerModule(simpleModule);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        objectMapperWithNull = new ObjectMapper();
        objectMapperWithNull.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapperWithNull.registerModule(javaTimeModule);
        objectMapperWithNull.registerModule(simpleModule);
        objectMapperWithNull.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public JsonUtils() {
//        throw new IllegalStateException("Utility class");
    }

    public static <T> String serialize(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> String serializeWithNull(T t) {
        try {
            return objectMapperWithNull.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T deserialize(String string, Class<T> type) {
        try {
            return objectMapper.readValue(string, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T deserialize(String string, CollectionType type) {
        try {
            return objectMapper.readValue(string, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T deserialize(String string, TypeReference<T> type) {
        try {
            return objectMapper.readValue(string, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static <T> T deserialize(Map<String, Object> map, Class<T> type) {
        return objectMapper.convertValue(map, type);
    }

    public static <T> List<T> deserializeList(String string, Class<T> type) {
        try {
            CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
            return objectMapper.readValue(string, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static <T> CollectionType getCollectionType(Class<T> type) {
        return objectMapper.getTypeFactory().constructCollectionType(List.class, type);
    }

    public static <T> List<T> deserializeList(String string, JavaType type) {
        try {
            CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
            return objectMapper.readValue(string, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static class LocalDateSerializer extends StdSerializer<LocalDate> {

        public LocalDateSerializer() {
            super(LocalDate.class);
        }

        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeString(value.format(TimeConstant.DateFormatter));
        }

    }

    public static class LocalDateDeserializer extends StdDeserializer<LocalDate> {

        protected LocalDateDeserializer() {
            super(LocalDate.class);
        }

        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            return LocalDate.parse(p.getValueAsString(), TimeConstant.DateFormatter);
        }

    }

    public static class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

        public LocalDateTimeSerializer() {
            super(LocalDateTime.class);
        }

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeString(value.atZone(ZoneOffset.systemDefault()).withZoneSameInstant(ZoneOffset.UTC)
                    .format(TimeConstant.RFC3339Formatter));
        }

    }

    public static class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

        protected LocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            return LocalDateTime.parse(p.getValueAsString(), TimeConstant.RFC3339Formatter).atZone(ZoneOffset.UTC)
                    .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        }

    }

    /**
     * ObjectMapper类是Jackson库的主要类。它提供了一些功能将Java对象匹配JSON结构，反之亦然。它使用JsonParser和JsonGenerator的实例实现JSON实际的读/写。
     * 这个例子: Json str 和 POJO 的转换
     * 参考[(1条消息) Jackson快速入门_燃烧的小宇宙-CSDN博客 ](https://blog.csdn.net/u014745069/article/details/88699019)
     */
    @Test
    public void jsonStrToJavaBean() throws JsonParseException, JsonMappingException, IOException {
        String stuJsonStr = "{\"name\" : \"Tom\", \"age\" : 25}";
        ObjectMapper mapper = new ObjectMapper();
        Student stu = mapper.readValue(stuJsonStr, Student.class);
        System.out.println(stu);

        Student shushen = new Student("shushen", 24);
        String shushenStr = mapper.writeValueAsString(shushen);//pojo -> json str
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Student {

        private String name;

        private Integer age;

    }

    /**
     * 反序列化json文件为流,然后可以直接转成POJO
     * POJO和json文件的转化
     */
    @Test
    public void jsonFileToBean() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream resourceAsStream = this.getClass().getResourceAsStream("Milly.json");
        Student stu = mapper.readValue(resourceAsStream, Student.class);
        System.out.println(stu);
//        URL resource3 = this.getClass().getResource("/");
//        URL resource2 = this.getClass().getResource("Milly.json");
//        URL resource = this.getClass().getResource("resources");
        Student shushen = new Student("shushen", 24);
        String resourcesPath = new File(this.getClass().getResource("/").getPath()).getParent() + "/resources/";
        File file = new File(resourcesPath + "/shushen.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        mapper.writeValue(file, shushen);
    }

    @Test
    public void simpleDataBind() throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        boolean isStudent = true;
        int[] nums = {1, 3, 5, 7, 9};
        Student Jerry = new Student("Jerry", 26);
        Map<String, Student> stuMap = new HashMap<>();
        stuMap.put("studentObj", Jerry);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("studentName", Jerry.getName());
        dataMap.put("studentAge", Jerry.getAge());
        dataMap.put("Jerry", Jerry);
        dataMap.put("stuMap", stuMap);
        dataMap.put("nums", nums);
        dataMap.put("isStudent", isStudent);
        // -----------------序列化为json文件------------------
        mapper.writeValue(new File("dataMap.json"), dataMap);
        // -------------------从json文件中读出各个对象----------------
        Map<String, Object> readDataMap = mapper.readValue(new File("dataMap.json"), Map.class);
        System.out.println(readDataMap);
        System.out.println(readDataMap.get("Jerry"));
        System.out.println(readDataMap.get("stuMap"));
        System.out.println(readDataMap.get("nums"));
        System.out.println(readDataMap.get("isStudent"));
    }

    /**
     * 不打印null值可以节省空间
     */
    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper shortMapper = new ObjectMapper();
        shortMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//null值不打印
        ObjectMapper allMapper = new ObjectMapper();
        Student stu = new Student(null, 20);
        String shortStr = shortMapper.writeValueAsString(stu);
        String longStr = allMapper.writeValueAsString(stu);
        Student student1 = shortMapper.readValue(shortStr, Student.class);
        Student student2 = shortMapper.readValue(longStr, Student.class);
        Student student3 = allMapper.readValue(longStr, Student.class);
        Student student4 = allMapper.readValue(shortStr, Student.class);
    }
}
