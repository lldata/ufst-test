package dk.ufst.ufsttest.reports;

import dk.ufst.ufsttest.tax.TaxPayer;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tax-reports")
public class TaxPayerReportRestService {

    private final TaxPayerReportService taxPayerReportService;

    public TaxPayerReportRestService(TaxPayerReportService taxPayerReportService) {
      this.taxPayerReportService = taxPayerReportService;
    }

  @PostMapping()
  public Map<String, Long> addReport(@RequestBody TaxPayer taxPayer) {
      var id = taxPayerReportService.yearlyReport(taxPayer);
      return Map.of("id", id);
  }

  @GetMapping()
  public Iterable<TaxPayerReport> listReports() {
    return taxPayerReportService.list();
  }


}
