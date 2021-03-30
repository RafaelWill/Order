package be.willekens.template.infrastructure.utils;

import java.util.regex.Pattern;

public class EmailUtils {

    private static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValidEmail(String email){
        return  VALID_EMAIL_REGEX.matcher(email).matches();
    }
}
