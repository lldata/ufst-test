package dk.ufst.ufsttest.testcontainers;

import dk.ufst.ufsttest.JsonConfiguration;
import dk.ufst.ufsttest.rates.TaxRateFinder;
import dk.ufst.ufsttest.rates.restjoke.JokeConfig;
import dk.ufst.ufsttest.rates.simple.SimpleTaxRateFinder;
import dk.ufst.ufsttest.reports.TaxPayerReport;
import dk.ufst.ufsttest.reports.TaxPayerReportRepository;
import dk.ufst.ufsttest.reports.TaxPayerReportRestService;
import dk.ufst.ufsttest.reports.TaxPayerReportService;
import dk.ufst.ufsttest.tax.TaxCalculator;
import dk.ufst.ufsttest.tax.TaxPayer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Demos how to setup a database test that runs on postgres in a docker container
 *
 * Also demos how to override a bean in spring when desperate ...
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
public class TaxReportsTestContainersDemoTest {
  @Container
  private static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:15");

  @DynamicPropertySource
  static void registerProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", CONTAINER::getUsername);
    registry.add("spring.datasource.password", CONTAINER::getPassword);
  }

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  TaxPayerReportRestService restService;

  @Test
  public void add3() {
    for (int i = 0; i < 3; i++) {
      restService.addReport(new TaxPayer("name", 1000));
    }

    // count the number of reports in the database
    long count = entityManager.getEntityManager().createQuery("select count(e) from TaxPayerReport e", Long.class).getSingleResult();
    assertThat(count).isEqualTo(3);
  }

  @TestConfiguration
  @Import({
      TaxPayerReportRestService.class,
      TaxPayerReportService.class,
      TaxCalculator.class,
      //SimpleTaxRateFinder.class, // <-- won't work with @DataJpaTest as this somehow enables classpath scanning
      JsonConfiguration.class
  })
  static class TestConfig {
    @Primary // <-- monkey way to override a bean in spring!
    @Bean
    TaxRateFinder taxRateFinder() {
      return new SimpleTaxRateFinder();
    }
  }
}
