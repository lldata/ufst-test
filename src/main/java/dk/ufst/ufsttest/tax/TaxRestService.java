package dk.ufst.ufsttest.tax;

import dk.ufst.ufsttest.rates.TaxRateFinder;
import dk.ufst.ufsttest.tax.TaxCalculator;
import dk.ufst.ufsttest.tax.TaxPayer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tax")
public class TaxRestService {

    private final TaxCalculator taxCalculator;
    private final TaxRateFinder taxRateFinder;

    public TaxRestService(TaxCalculator taxCalculator, TaxRateFinder taxRateFinder) {
        this.taxCalculator = taxCalculator;
        this.taxRateFinder = taxRateFinder;
    }

    @PostMapping("/calculate")
    public Response calculateTax(@RequestBody TaxPayer taxPayer) {
        var tax = taxCalculator.calculateTax(taxPayer.income());
        return new Response("tax", tax);
    }

    @GetMapping("/rate/{income}")
    public Response findRate(
        @PathVariable int income
    ) {
        double rate = taxRateFinder.findRate(income);
        return new Response("rate", rate);
    }

    record Response(String name, double value) {}
}