package dk.ufst.ufsttest.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.ufst.ufsttest.JsonConfiguration;
import dk.ufst.ufsttest.rates.restjoke.Joke;
import dk.ufst.ufsttest.rates.restjoke.JokeFetcher;
import dk.ufst.ufsttest.rates.restjoke.JokeTaxRateFinder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

/**
 * Example on how to use Spring Test with a local TestConfiguration, Importing other Spring Configurations and one way to Mock out a Rest call
 */
@ExtendWith(SpringExtension.class)
public class TaxCalculatorRestMockTest {
  static final String jokeSetup = "Did you hear about the submarine industry?";

  @Autowired
  JokeTaxRateFinder jokeTaxRateFinder;

  @Test
  public void setup() {
    assertThat(jokeSetup.length()).isEqualTo(42);
  }
  @Test
  public void mockRest() {
    var result = jokeTaxRateFinder.findRate(10000);
    assertThat(result).isEqualTo(42);
  }

  @TestConfiguration
  @Import({
      JokeTaxRateFinder.class,
      JsonConfiguration.class
  })
  static class TestConfig {
    @Bean
    JokeFetcher jokeFetcher(ObjectMapper objectMapper) throws IOException, InterruptedException {
      String dummyResponse = "{\"type\": \"general\", \"setup\": \""+jokeSetup+"\", \"punchline\": \"It really took a dive...\", \"id\": 96}";

      var httpResponse = Mockito.mock(HttpResponse.class);
      Mockito.when(httpResponse.body()).thenReturn(dummyResponse);

      HttpClient httpClient = Mockito.mock(HttpClient.class);
      Mockito.when(httpClient.send(any(), any())).thenReturn(httpResponse);

      JokeFetcher jokeFetcher = new JokeFetcher(httpClient, objectMapper);
      return jokeFetcher;
    }
  }
}
