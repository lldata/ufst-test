package dk.ufst.ufsttest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import java.time.Duration;

@ConfigurationProperties(prefix = "ufst")
public class AppConfig {
  private double rate;
  private DataSize size;

  private Duration duration;

  public DataSize getSize() {
    return size;
  }

  public void setSize(DataSize size) {
    this.size = size;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }
}
