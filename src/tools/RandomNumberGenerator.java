package tools;

import java.util.Random;

/**
 * The RandomNumberGenerator class provides a method to generate a random number.
 */
public class RandomNumberGenerator {
  /**
   * The method returns a random number within a limit.
   *
   * @param max is the integer limit.
   * @return an integer that is within the limit.
   */
  public static int getRand(int max) {
    Random rand = new Random();
    return rand.nextInt(max);
  }
}
