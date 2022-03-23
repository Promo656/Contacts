package app;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private final static Pattern phoneNumberPattern = Pattern.compile("\\+?((\\([0-9A-Za-z]+\\)|[0-9A-Za-z]+)|" +
            "([0-9A-Za-z]+[ -]\\([0-9A-Za-z]{2,}\\))|" +
            "[0-9A-Za-z]+[ -][0-9A-Za-z]{2,})([ -][0-9A-Za-z]{2,}[ -]?)*");

    public static String checkGender(String gender) {
        String regexp = "(M|F)";
        if (gender.matches(regexp)) {
            return gender;
        } else {
            System.out.println("Bad gender!");
            return "[no data]";
        }
    }

    public static String checkPhoneNumber(String phoneNumber) {
        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
        if (matcher.matches()) {
            return phoneNumber;
        } else {
            System.out.println("Bad phone number!");
            return "[no data]";
        }
    }

    public static LocalDate checkBirthDate(String birthDate) {
        try {
            return LocalDate.parse(birthDate);
        } catch (Exception e) {
            System.out.println("Bad birth date!");
            return null;
        }
    }

    public static String checkOrganizationField(String field) {
        String regexp = "(name|address|number)";
        if (field.matches(regexp)) {
            return field;
        } else {
            return null;
        }
    }

    public static String checkPersonField(String field) {
        String regexp = "(name|surname|birth|gender|number)";
        if (field.matches(regexp)) {
            return field;
        } else {
            return null;
        }
    }
}
