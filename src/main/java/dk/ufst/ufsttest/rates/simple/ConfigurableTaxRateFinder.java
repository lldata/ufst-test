package dk.ufst.ufsttest.rates.simple;

import dk.ufst.ufsttest.config.AppConfig;
import dk.ufst.ufsttest.rates.TaxRateFinder;
import org.springframework.beans.factory.annotation.Value;

public class ConfigurableTaxRateFinder implements TaxRateFinder {
    final double rate;

    public ConfigurableTaxRateFinder(AppConfig appConfig) {
        this.rate = appConfig.getRate();
    }

    public double findRate(double income) {
        if (income < 10000) {
            return 0.1;  // 10% tax rate
        } else if (income < 50000) {
            return 0.2;  // 20% tax rate
        } else {
            return 0.5;  // 50% tax rate
        }
    }
}