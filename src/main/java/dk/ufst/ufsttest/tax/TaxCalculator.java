package dk.ufst.ufsttest.tax;

import dk.ufst.ufsttest.rates.TaxRateFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TaxCalculator {
    private TaxRateFinder taxRateFinder;

    public TaxCalculator(TaxRateFinder taxRateFinder) {
        this.taxRateFinder = taxRateFinder;
    }

    public double calculateTax(double income) {
        double taxRate = taxRateFinder.findRate(income);
        return income * taxRate;
    }
}