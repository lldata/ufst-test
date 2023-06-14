package dk.ufst.ufsttest.styles;

import dk.ufst.ufsttest.config.AppConfig;
import dk.ufst.ufsttest.rates.TaxRateFinder;
import dk.ufst.ufsttest.rates.simple.ConfigurableTaxRateFinder;
import dk.ufst.ufsttest.tax.TaxCalculator;
import dk.ufst.ufsttest.rates.simple.SimpleTaxRateFinder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * demos many styles of making tests
 */
public class TaxCalculatorTest {
    /**
     * Classicist unit testing prefers using the real implementation of the dependencies, whenever possible
     */
    @Test
    public void classist() {
        // Arrange
        var taxRateFinder = new SimpleTaxRateFinder();
        var taxCalculator = new TaxCalculator(taxRateFinder);

        // Act
        double tax = taxCalculator.calculateTax(50000);

        // Assert
        assertEquals(25000, tax, 0.01, "Tax should be correctly calculated");
    }


    @Test
    public void classistConfig() {
        // Arrange
        var config = new AppConfig();
        config.setRate(0.2);
        var taxRateFinder = new ConfigurableTaxRateFinder(config);
        var taxCalculator = new TaxCalculator(taxRateFinder);

        // Act
        double tax = taxCalculator.calculateTax(50000);

        // Assert
        assertEquals(25000, tax, 0.01, "Tax should be correctly calculated");
    }
    /**
     * Using mockito to mock a dependency
     *
     * And mockist naming
     */
    @Test
    public void mockistMocking() {
        // Arrange
        TaxRateFinder mockTaxRateFinder = mock(TaxRateFinder.class);
        when(mockTaxRateFinder.findRate(anyDouble())).thenReturn(0.2);

        // sut = System Under Test
        var sut = new TaxCalculator(mockTaxRateFinder);

        // Act
        double actual = sut.calculateTax(50000);

        // Assert
        assertEquals(10000, actual, "Tax should be correctly calculated");
    }

    /**
     * Using mockito to verify (Spy!) on mock interactions
     */
    @Test
    public void mockistVerify() {
        // Arrange
        TaxRateFinder mockTaxRateFinder = mock(TaxRateFinder.class);
        when(mockTaxRateFinder.findRate(anyDouble())).thenReturn(0.2);

        TaxCalculator taxCalculator = new TaxCalculator(mockTaxRateFinder);

        // Act
        double tax = taxCalculator.calculateTax(50000);

        // Assert
        assertEquals(10000, tax, "Tax should be correctly calculated");
        // Verify that mock is called with correct argument
        verify(mockTaxRateFinder).findRate(50000);
    }

    /**
     * Example of stubbing with inline inheritance of implementation class
     */
    @Test
    public void stubClassic() {
        // Arrange
        TaxRateFinder stubTaxRateFinder = new SimpleTaxRateFinder() {
            @Override
            public double findRate(double income) {
                return 0.5;
            }
        };
        TaxCalculator taxCalculator = new TaxCalculator(stubTaxRateFinder);

        // Act
        double tax = taxCalculator.calculateTax(50000);

        // Assert
        assertEquals(25000, tax, 0.01, "Tax should be correctly calculated");
    }

    /**
     * Stubbing with lambda
     */
    @Test
    public void stubLambda() {
        // Arrange
        TaxCalculator taxCalculator = new TaxCalculator((income) -> 0.5);

        // Act
        double tax = taxCalculator.calculateTax(50000);

        // Assert
        assertEquals(25000, tax, 0.01, "Tax should be correctly calculated");
    }
}