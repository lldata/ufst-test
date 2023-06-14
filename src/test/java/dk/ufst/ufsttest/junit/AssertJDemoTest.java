package dk.ufst.ufsttest.junit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class AssertJDemoTest {
  @Test
  public void assertion() {
    assertThat(2 + 2).isEqualTo(4);
  }

  @Test
  public void assertionWithMessage() {
    assertThat(2 + 2).isEqualTo(4).withFailMessage("math is broken");
  }

  @Test
  public void exceptionClassic() {
    try {
      var div0 = 100 / 0;
      fail("you should never enter this line");
    } catch (ArithmeticException expected) {
      // ok
    }
  }

  @Test
  public void exceptionCatch() {
    // when
    Throwable thrown = catchThrowable(() -> {
      var div0 = 100 / 0;
    });

    // then
    assertThat(thrown)
        .isInstanceOf(ArithmeticException.class)
        .hasMessageContaining("/ by zero");
  }

  @Test
  @Disabled
  public void disabled() {
    assertThat("that").contains("hat");
  }
}
