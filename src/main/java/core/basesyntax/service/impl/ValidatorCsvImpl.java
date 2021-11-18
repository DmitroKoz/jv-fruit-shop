package core.basesyntax.service.impl;

import core.basesyntax.service.Validator;
import core.basesyntax.service.activitiy.ActivityHandler;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ValidatorCsvImpl implements Validator {
    private static final String CSV_LINE_PATTERN = "[a-z],[a-z]+,[0-9]+";
    private final Map<String, ActivityHandler> activityHandlerMap;

    public ValidatorCsvImpl(Map<String, ActivityHandler> activityHandlerMap) {
        this.activityHandlerMap = activityHandlerMap;
    }

    public boolean validate(List<String> fileData) {
        if (fileData.isEmpty() || !fileData.get(0).equals("type,fruit,quantity")) {
            throw new RuntimeException("INPUT DATA IS INVALID");
        }
        Predicate<String> csvLinePredicate = line -> Pattern.matches(CSV_LINE_PATTERN, line)
                && activityHandlerMap
                .containsKey(String.valueOf(line.charAt(0)))
                && line.charAt(line.lastIndexOf(',') + 1) != '0';
        long validLinesNumber = Stream.iterate(1, i -> i + 1).limit(fileData.size() - 1)
                .map(fileData::get)
                .filter(csvLinePredicate)
                .count();
        if (!(validLinesNumber + 1 == fileData.size())) {
            throw new RuntimeException("INPUT DATA IS INVALID");
        }
        return true;
    }
}