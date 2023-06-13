package dk.ufst.ufsttest;

import dk.ufst.ufsttest.rates.restjoke.JokeConfig;
import dk.ufst.ufsttest.rates.restjoke.JokeTaxRateFinder;
import dk.ufst.ufsttest.rates.simple.SimpleTaxRateFinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class UfstTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(UfstTestApplication.class, args);
  }

  @Configuration
  @Import({
      JokeConfig.class
  })
  class ProductionConfiguration {

  }
}
