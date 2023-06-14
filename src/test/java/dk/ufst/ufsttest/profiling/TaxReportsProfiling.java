package dk.ufst.ufsttest.profiling;

import dk.ufst.ufsttest.JsonConfiguration;
import dk.ufst.ufsttest.rates.restjoke.JokeConfig;
import dk.ufst.ufsttest.reports.TaxPayerReportRestService;
import dk.ufst.ufsttest.reports.TaxPayerReportService;
import dk.ufst.ufsttest.tax.TaxCalculator;
import dk.ufst.ufsttest.tax.TaxPayer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * This test is used to profile the application
 * <p>
 * Also demos how to unit test with an in memory database and spring test configuration pattern
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TaxReportsProfiling {
  @Autowired
  TaxPayerReportRestService restService;

  //@Disabled // enable when you need to profile ... :)
  @Test
  public void addSome() {
    for (int i = 0; i < 10; i++) {
      restService.addReport(new TaxPayer("name", 1000));
    }
  }

  @TestConfiguration
  @Import({
      TaxPayerReportRestService.class,
      TaxPayerReportService.class,
      TaxCalculator.class,
      JokeConfig.class,
      JsonConfiguration.class
  })
  static class TestConfig {

  }
}
