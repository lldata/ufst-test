package dk.ufst.ufsttest.cucumber;

import dk.ufst.ufsttest.rates.TaxRateFinder;
import dk.ufst.ufsttest.rates.simple.SimpleTaxRateFinder;
import dk.ufst.ufsttest.tax.TaxCalculator;
import dk.ufst.ufsttest.tax.TaxPayer;
import io.cucumber.java.da.*;
import org.junit.jupiter.api.Assertions;

public class TaxCalculatorSteps {

    private TaxPayer taxpayer;
    private TaxCalculator taxCalculator;
    private double tax;

    private TaxRateFinder taxRateFinder = new SimpleTaxRateFinder();

    @Givet("en skatteyder med en indkomst på {double}")
    public void a_taxpayer_with_an_income_of(double income) {
        taxpayer = new TaxPayer("John Doe", income);
        taxCalculator = new TaxCalculator(taxRateFinder);
    }

    @Når("jeg beregner skatten")
    public void i_calculate_the_tax() {
        tax = taxCalculator.calculateTax(taxpayer.income());
    }

    @Så("skal skatten være {double}")
    public void the_tax_should_be(double expectedTax) {
        Assertions.assertEquals(expectedTax, tax, 0.01);
    }
}