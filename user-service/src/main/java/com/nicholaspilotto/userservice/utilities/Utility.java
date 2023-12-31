package com.nicholaspilotto.userservice.utilities;

import com.github.javafaker.Faker;
import com.nicholaspilotto.userservice.models.entities.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

  /**
   * Generate {@code number} fake {@link User}s.
   *
   * @param number number of fake {@link User}s.
   *
   * @return {@code List<User>} of {@code number} {@link User}s.
   */
  public static List<User> generateFakeUsers(int number) {
    List<User> result = new ArrayList<>();
    Faker faker = new Faker(Locale.ITALIAN);

    for (int i = 0; i < number; ++i) {
      User user = new User();
      user.setFirstName(faker.name().firstName());
      user.setLastName(faker.name().lastName());
      user.setPhoneNumber(faker.phoneNumber().cellPhone());

      String email = "%s.%s.%d@test.com".formatted(
        user.getFirstName().toLowerCase(),
        user.getLastName().toLowerCase(),
        i
      );

      user.setEmail(email);
      user.setPassword(bcrypt(faker.internet().password()));
      user.setBirthDate(faker.date().birthday(18, 70));
      result.add(user);
    }

    return result;
  }
}
