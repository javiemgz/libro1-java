package com.javi.Libro1.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.javi.Libro1.domain.User;

import java.io.IOException;

public class ShortUserSerializer extends StdSerializer<User> {

    public ShortUserSerializer() {
        this(null);
    }

    public ShortUserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(
            User value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("name", value.getName());
        jgen.writeStringField("lastName", value.getLastName());
        jgen.writeStringField("email", value.getEmail());


        jgen.writeEndObject();
    }
}