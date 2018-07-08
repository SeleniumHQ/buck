package jlib;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class JTestWithPernicious {
  @Test
  public void testGetValue() {
    assertThat(JLib.getValue(), equalTo(3));
  }

  @Test
  public void testHasPernicious() {
    System.loadLibrary("pernicious");
  }
}
