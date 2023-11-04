package com.nicholaspilotto.userservice.utilities;

import com.github.javafaker.Faker;
import com.nicholaspilotto.userservice.models.entities.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public final class Utility {
    public Utility () { }

  /**
   * Calculate the hash of a string using MD5 algorithm.
   * @param input string of which to compute hash.
   * @return String representing MD5 hash of the input.
   * @throws NoSuchAlgorithmException if MD5 algorithm cannot be found.
   */
  public static String hashMD5(String input) throws NoSuchAlgorithmException {
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

  public static List<User> generateFakeUsers(int number) {
    List<User> result = new ArrayList<>();
    Faker faker = new Faker(Locale.ITALIAN);

    for (int i = 0; i < number; ++i) {
      User user = new User();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      user.setFirstName(faker.name().firstName());
      user.setLastName(faker.name().lastName());
      user.setPhoneNumber(faker.phoneNumber().cellPhone());
      String email = user.getFirstName() + "." + user.getLastName() + "." + i + "@test.com";
      user.setEmail(email);
      try {
        user.setPassword(hashMD5(faker.internet().password()));
      } catch (NoSuchAlgorithmException exception) {
        return result;
      }
      user.setBirthDate(faker.date().birthday(18, 70));

      result.add(user);
    }
    return result;
  }
}
