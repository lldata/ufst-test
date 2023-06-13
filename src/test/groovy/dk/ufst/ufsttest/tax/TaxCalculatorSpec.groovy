package dk.ufst.ufsttest.tax

import dk.ufst.ufsttest.rates.simple.SimpleTaxRateFinder
import spock.lang.Specification

class TaxCalculatorSpec extends Specification {

    TaxCalculator taxCalculator = new TaxCalculator(new SimpleTaxRateFinder());

    def "calculate tax based on income"() {
        given:
        def taxRateFinder = new SimpleTaxRateFinder()
        def taxCalculator = new TaxCalculator(taxRateFinder)

        when:
        def tax = taxCalculator.calculateTax(50000)

        then:
        assert tax == 25000
    }
}