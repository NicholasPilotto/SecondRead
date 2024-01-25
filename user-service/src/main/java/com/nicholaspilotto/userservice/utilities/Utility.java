package com.nicholaspilotto.userservice.utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Represents a class with all utilities methods.
 */
public final class Utility {
  public Utility() { }

  /**
   * Calculate the hash of a string using MD5 algorithm.
   *
   * @param input string of which to compute hash.
   *
   * @return String representing MD5 hash of the input.
   * @throws NoSuchAlgorithmException if MD5 algorithm cannot be found.
   */
  public static String hashMd5(String input) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    messageDigest.reset();
    messageDigest.update(input.getBytes());
    byte[] digest = messageDigest.digest();

    // Convert byte array into sign-um representation
    BigInteger integerRepresentation = new BigInteger(1, digest);

    // Convert message digest into hex value
    String output = integerRepresentation.toString(16);
    while (output.length() < 32) {
      output = "0%s".formatted(output);
    }

    return output;
  }

  /**
   * Generate password using {@link BCrypt} algorithm.
   *
   * @param input string of which to compute the algorithm.
   *
   * @return BCrypt-ed string.
   */
  public static String bcrypt(String input) {

    return BCrypt.hashpw(input, BCrypt.gensalt());
  }
}
