package pap.lorinc.socialgraph.utils;

import java.time.Duration;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;

public class DateTimes {
    public static String durationToString(Duration duration) {
        return Stream.of(toString(duration.toDays(), "day", "days"),
                         toString(duration.toHours() % 24, "hour", "hours"),
                         toString(duration.toMinutes() % 60, "minute", "minutes"),
                         toString(duration.getSeconds() % 60, "second", "seconds"),
                         toString(duration.toMillis() % 1000, "milli", "millis"))
                     .flatMap(Streams::stream)
                     .limit(2)
                     .collect(joining(", "));
    }
    private static Optional<String> toString(long duration, String singular, String plural) {
        switch ((int) duration) {
            case 0: return empty();
            case 1: return of(duration + " " + singular);
            default: return of(duration + " " + plural);
        }
    }
}