package org.apnplace;

import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.KeyDeserializers;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;

/**
 * Created by dlakshmi on 10/28/2016.
 */

public class CaseInsensitiveKeyDeserializers implements KeyDeserializers {

   public static final CaseInsensitiveKeyDeserializer DESERIALIZER = new CaseInsensitiveKeyDeserializer();


    @Override
    public KeyDeserializer findKeyDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc, BeanProperty property) throws JsonMappingException {
        if ((type.getRawClass() != String.class) && (type.getRawClass() != Object.class)) {
            throw new IllegalArgumentException("expected String or Object, found " + type.getRawClass().getName());
        }
        return DESERIALIZER;
    }
}
 class CaseInsensitiveKeyDeserializer
        extends KeyDeserializer
{
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt)
            throws IOException
    {
        return key.toLowerCase();
    }
}