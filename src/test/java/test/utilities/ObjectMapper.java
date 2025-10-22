package test.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class ObjectMapper {

    public static <T> T convertJsonToJava(String json, Class<T> cls) {//Generic Method
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public static JsonNode getJsonNode(String fileName) {

        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().readTree(new File("src/test/resources/" + fileName + ".json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateJsonNode(JsonNode payload, String fieldName, String value) {
        ObjectNode objectNode = (ObjectNode) payload;
        objectNode.put(fieldName, value);
    }

    public static void updateJsonNode(JsonNode payload, String fieldName, int value) {
        ObjectNode objectNode = (ObjectNode) payload;
        objectNode.put(fieldName, value);
    }

    public static void updateJsonNode(JsonNode payload, String fieldName, double value) {
        ObjectNode objectNode = (ObjectNode) payload;
        objectNode.put(fieldName, value);
    }

    public static void removeFieldJsonNode(JsonNode payload, String fieldName) {
        ObjectNode objectNode = (ObjectNode) payload;
        objectNode.remove(fieldName);
    }

}