package dk.ufst.ufsttest.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JUnitDemo {
  @Test
  public void assertion() {
    assertEquals(2+2, 4, "math is broken");
  }
  @Test
  public void exceptionClassic() {
    try {
      var div0 = 100/0;
      fail("you should never enter this line");
    } catch (ArithmeticException expected) {
      // ok
    }
  }

  @Test
  // JUnit 4: @Test(expected = ArithmeticException.class)
  public void exceptionJunit() {
    Assertions.assertThrows(ArithmeticException.class, () -> {
      var div0 = 100/0;
    });
  }

  @Test
  @Disabled
  public void disabled() {
    assertEquals(2+2, 4, "math is broken");
  }
}
