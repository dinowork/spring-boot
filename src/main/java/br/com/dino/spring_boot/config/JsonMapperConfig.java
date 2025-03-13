package br.com.dino.spring_boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonMapperConfig {


    /* EXPLICAÇÃO


    */

     @Bean
    public ObjectMapper objectMapper(){
         ObjectMapper mapper = new ObjectMapper();
         SimpleFilterProvider filters = new SimpleFilterProvider().
                 addFilter("PersonFilter", SimpleBeanPropertyFilter.serializeAllExcept("sensitiveData","firstName"));  //,"firstName"));
         mapper.setFilterProvider(filters);
         return mapper;
     }

}
