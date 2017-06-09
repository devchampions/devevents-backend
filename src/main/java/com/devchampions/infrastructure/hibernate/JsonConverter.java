package com.devchampions.infrastructure.hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.io.StringWriter;

public abstract class JsonConverter<T> implements AttributeConverter<T, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private final Class<T> entityType;

    public JsonConverter(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public String convertToDatabaseColumn(T entity) {
        if (entity == null) {
            return null;
        }
        StringWriter writer = new StringWriter();
        try {
            objectMapper.writeValue(writer, entity);
            return writer.toString();
        } catch (IOException e) {
            throw new JsonConversionFailed(e);
        }
    }

    @Override
    public T convertToEntityAttribute(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, entityType);
        } catch (IOException e) {
            throw new JsonConversionFailed(e);
        }
    }
}

