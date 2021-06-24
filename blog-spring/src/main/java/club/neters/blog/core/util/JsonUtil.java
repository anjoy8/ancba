package club.neters.blog.core.util;

import club.neters.blog.core.exception.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * Json Util
 *
 * @author wuare
 * @date 2021/6/16
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil() {
    }

    /**
     * json to bean
     */
    public static <T> T toBean(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * json to map
     */
    public static <K, V> Map<K, V> toMap(String content) {
        try {
            return objectMapper.readValue(content, new TypeReference<Map<K, V>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * json to list
     */
    public static <E> List<E> toList(String content) {
        try {
            return objectMapper.readValue(content, new TypeReference<List<E>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * object to json
     */
    public static <T> String toJson(T value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new ServiceException(e);
        }
    }
}
