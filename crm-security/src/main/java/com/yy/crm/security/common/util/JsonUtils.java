package com.yy.crm.security.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.yy.crm.utils.YYUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luyuanyuan on 2017/11/7.
 */
@Slf4j
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    static{
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);

        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);

        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(YYUtil.STANDARD_FORMAT));

        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(YYUtil.STANDARD_FORMAT)));
//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(YYUtil.STANDARD_FORMAT)));
        objectMapper.registerModule(javaTimeModule);
    }



    public static <T> String obj2String(T obj){
        if(obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String)obj :  objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error",e);
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj){
        if(obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String)obj :  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error",e);
            return null;
        }
    }


    public static <T> T string2Obj(String str,Class<T> clazz){
        if(StringUtils.isEmpty(str) || clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class)? (T)str : objectMapper.readValue(str,clazz);
        } catch (Exception e) {
            log.warn("Parse String to Object error",e);
            return null;
        }
    }



    public static <T> T string2Obj(String str, TypeReference<T> typeReference){
        if(StringUtils.isEmpty(str) || typeReference == null){
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class)? str : objectMapper.readValue(str,typeReference));
        } catch (Exception e) {
            log.warn("Parse String to Object error",e);
            return null;
        }
    }


    public static <T> T string2Obj(String str,Class<?> collectionClass,Class<?>... elementClasses){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass,elementClasses);
        try {
            return objectMapper.readValue(str,javaType);
        } catch (Exception e) {
            log.warn("Parse String to Object error",e);
            return null;
        }
    }

    public static <T> List<T> string2List(String jsonData, Class<T> beanType) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return objectMapper.readValue(jsonData, javaType);
        } catch (Exception e) {
            log.warn("Parse String to List error",e);
            return null;
        }
    }


    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put("id",23);
        map.put("name","greely");
        map.put("email","geely@happymmall.com");
        map.put("mobile","15675504080");
        map.put("age",18);
        map.put("operatorTime","2018-2-18 14:21");
        String userJsonPretty = JsonUtils.obj2StringPretty(map);
        log.info("userJson:{}",userJsonPretty);

        String a = "{\n" +
                "  \"operatorTime\" : \"2018-2-18 14:21\",\n" +
                "  \"name\" : \"greely\",\n" +
                "  \"mobile\" : \"15675504080\",\n" +
                "  \"id\" : 23,\n" +
                "  \"email\" : \"geely@happymmall.com\",\n" +
                "  \"age\" : 18\n" +
                "}";
//        User u2 = new User();
//        u2.setId(2);
//        u2.setEmail("geelyu2@happymmall.com");
//
//
//
//        String user1Json = JsonUtil.obj2String(u1);
//
//        String user1JsonPretty = JsonUtil.obj2StringPretty(u1);
//
//        log.info("user1Json:{}",user1Json);
//
//        log.info("user1JsonPretty:{}",user1JsonPretty);
//
//
//        User user = JsonUtil.string2Obj(user1Json,User.class);
//
//
//        List<User> userList = Lists.newArrayList();
//        userList.add(u1);
//        userList.add(u2);
//
//        String userListStr = JsonUtil.obj2StringPretty(userList);
//
//        log.info("==================");
//
//        log.info(userListStr);
//
//
//        List<User> userListObj1 = JsonUtil.string2Obj(userListStr, new TypeReference<List<User>>() {
//        });
//
//
//        List<User> userListObj2 = JsonUtil.string2Obj(userListStr,List.class,User.class);

        System.out.println("end");

    }

}
