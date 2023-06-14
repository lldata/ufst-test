package dk.ufst.ufsttest.reports;

import dk.ufst.ufsttest.rates.TaxRateFinder;
import dk.ufst.ufsttest.tax.TaxCalculator;
import dk.ufst.ufsttest.tax.TaxPayer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaxPayerReportService {
  final TaxRateFinder taxRateFinder;
  final TaxCalculator taxCalculator;
  final TaxPayerReportRepository taxPayerReportRepository;

  public TaxPayerReportService(TaxRateFinder taxRateFinder, TaxCalculator taxCalculator, TaxPayerReportRepository taxPayerReportRepository) {
    this.taxRateFinder = taxRateFinder;
    this.taxCalculator = taxCalculator;
    this.taxPayerReportRepository = taxPayerReportRepository;
  }

  public Long yearlyReport(TaxPayer taxPayer) {
    var rate = taxRateFinder.findRate(taxPayer.income());
    var tax = taxCalculator.calculateTax(taxPayer.income());
    var report = new TaxPayerReport(taxPayer.name(), taxPayer.income(), rate, tax);
    var saved = taxPayerReportRepository.save(report);
    return saved.getId();
  }


  public Iterable<TaxPayerReport> list() {
    return taxPayerReportRepository.findAll();
  }
}
