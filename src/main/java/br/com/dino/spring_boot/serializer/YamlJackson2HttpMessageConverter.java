package br.com.dino.spring_boot.serializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public final class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    protected YamlJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL),MediaType.parseMediaType("application/x-yaml"));
        //MediaType.parseMediaType(MediaType.APPLICATION_YAML_VALUE));
    }
}
