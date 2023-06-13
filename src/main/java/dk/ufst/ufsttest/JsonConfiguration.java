package dk.ufst.ufsttest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// the default objectmapper in spring boot is a bit tricky to mock, so we create our own with code
@Configuration
public class JsonConfiguration {
  @Bean
  ObjectMapper objectMapper() {
    var objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule()); // supports Java 8 dates
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // nice formatted output
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // to avoid writing dates as timestamps
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); // ignore empty fields
    return objectMapper;
  }
}
