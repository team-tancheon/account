package com.tancheon.account.api.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
@Builder
@JsonSerialize(using = WrapperResponseMessage.Serializer.class)
public class WrapperResponseMessage<T> implements ResponseMessage {

    private T data;

    public static final class Serializer extends StdSerializer<WrapperResponseMessage<?>> {

        public Serializer() {
            this(null);
        }

        public Serializer(Class<WrapperResponseMessage<?>> t) {
            super(t);
        }

        @Override
        public void serialize(WrapperResponseMessage<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {

            gen.writeStartObject();
            gen.writeFieldName("data");

            if (value.data == null) {
                gen.writeStartObject();
                gen.writeEndObject();
            } else {
                gen.writeObject(value.data);
            }
            gen.writeEndObject();
        }
    }
}
