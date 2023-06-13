package dk.ufst.ufsttest.rates.restjoke;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.net.http.HttpClient;

// example of how to create a configuration class for a module, that keeps the classes decoupled from spring
@Configuration
@Import({
    JokeFetcher.class,
    JokeTaxRateFinder.class
})
public class JokeConfig {
  @Bean
  HttpClient httpClient() {
    return HttpClient.newHttpClient();
  }
}
