package dk.ufst.ufsttest.rates.restjoke;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.IOException;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Rest client for fetching jokes from https://official-joke-api.appspot.com/random_joke */
public class JokeFetcher {

  static final Logger logger = LoggerFactory.getLogger(JokeFetcher.class);

  final HttpClient client;
  final ObjectMapper mapper;

  public JokeFetcher(HttpClient client, ObjectMapper mapper) {
    this.client = client;
    this.mapper = mapper;
  }

  public Joke joke() {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("https://official-joke-api.appspot.com/random_joke"))
          .timeout(Duration.ofSeconds(5))
          .build();

      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        var joke = mapper.readValue(response.body(), Joke.class);
        logger.debug("Joke: {}: {}", joke.setup(), joke.punchline());
        return joke;
      } else if (response.statusCode() == 429) {
        logger.warn("Too many requests - this is not a joke!");
        return new Joke(429, "Too many requests", response.body(), "Parse this then!");
      } else {
        throw new IllegalStateException("Unexpected response: " + response.statusCode() + " " + response.body());
      }
    } catch (IOException | InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
