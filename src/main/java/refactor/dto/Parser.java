package refactor.dto;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static int convertStringToNumber(String input) {
        return Integer.parseInt(input);
    }

    public static List<Integer> splitInputBySeparator(String input, String separator) {
        String[] splitInput = input.split(separator);
        return Arrays.stream(splitInput)
                .map(inputNumber -> convertStringToNumber(inputNumber))
                .toList();
    }
}
