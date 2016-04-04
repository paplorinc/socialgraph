package pap.lorinc.socialgraph.utils;

import javaslang.collection.Stream;
import javaslang.control.Option;
import lombok.experimental.UtilityClass;

import java.time.Duration;

import static javaslang.control.Option.none;
import static javaslang.control.Option.of;

@UtilityClass
public class DateTimes {
    public static String durationToString(Duration duration) {
        return Stream.of(toString(duration.toDays(), "day", "days"),
                         toString(duration.toHours() % 24, "hour", "hours"),
                         toString(duration.toMinutes() % 60, "minute", "minutes"),
                         toString(duration.getSeconds() % 60, "second", "seconds"),
                         toString(duration.toMillis() % 1000, "milli", "millis"))
                     .flatMap(Option::toStream)
                     .take(2)
                     .mkString(", ");
    }
    private static Option<String> toString(long duration, String singular, String plural) {
        switch ((int) duration) {
            case 0: return none();
            case 1: return of(duration + " " + singular);
            default: return of(duration + " " + plural);
        }
    }
}