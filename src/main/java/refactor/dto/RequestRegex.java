package refactor.dto;

import java.util.regex.Pattern;

public class RequestRegex {
    public static final Pattern LOTTO_PATTERN = Pattern.compile("(\\d+)(,\\d+)+");
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d)+$");
}
