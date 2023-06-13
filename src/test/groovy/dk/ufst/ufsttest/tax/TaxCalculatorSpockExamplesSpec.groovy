package dk.ufst.ufsttest.tax;

import dk.ufst.ufsttest.rates.simple.SimpleTaxRateFinder;
import spock.lang.*

class TaxCalculatorSpockExamplesSpec extends Specification {

    @Unroll
    def "calculate #tax for income #income"() {
        given:
        def taxRateFinder = new SimpleTaxRateFinder()
        def taxCalculator = new TaxCalculator(taxRateFinder)

        when: "tax is calculated"
        double result = taxCalculator.calculateTax(income)

        then: "correct tax is returned"
        double delta = 0.01
        assert Math.abs(result - tax) < delta

        where:
        income    ||   tax
          1000    ||   100
          9999    ||   999.9
         10000    ||  2000
         49999    ||  9999.8
         50000    || 25000
        100000    || 50000
    }
}
