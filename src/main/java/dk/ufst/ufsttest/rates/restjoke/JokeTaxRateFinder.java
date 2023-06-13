package dk.ufst.ufsttest.rates.restjoke;

import dk.ufst.ufsttest.rates.TaxRateFinder;

/**
 * Our new minister of taxation is a former comedian and as part of his election program tax rates should be based on a random joke.
 */
public class JokeTaxRateFinder implements TaxRateFinder {
  private final JokeFetcher jokeFetcher;

  public JokeTaxRateFinder(JokeFetcher jokeFetcher) {
    this.jokeFetcher = jokeFetcher;
  }

  @Override
  public double findRate(double income) {
    var joke = jokeFetcher.joke();
    return joke.setup().length();
  }
}
