package com.joshua.base.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by joshua on 3/29/17.
 */

@XmlType
public class Configuration {
    public static <T extends Configuration> T read(InputStream configStream, Class<T> configurationType) throws IOException {
        return getMapper().readValue(configStream,configurationType);
    }

    public static String dump(Configuration configuration) throws JsonProcessingException{
        return getMapper().writeValueAsString(configuration);
    }

//    public static

    private static ObjectMapper getMapper(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.registerModule(new GuavaModule());
        mapper.setAnnotationIntrospector(new AnnotationIntrospectorPair(new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()),new JacksonAnnotationIntrospector()));
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy());
        return mapper;
    }
}
