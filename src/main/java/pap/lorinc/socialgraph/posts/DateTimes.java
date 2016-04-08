package pap.lorinc.socialgraph.posts;

import javaslang.collection.Stream;
import javaslang.control.Option;

import java.time.Duration;
import java.util.function.Supplier;

import static javaslang.API.*;
import static javaslang.control.Option.none;
import static javaslang.control.Option.of;

public class DateTimes {
    private static final int HOURS_PER_DAY = 24, MINUTES_PER_HOUR = 60, SECONDS_PER_MINUTE = 60, MILLIS_PER_SECOND = 1000;

    public static String durationToString(Duration duration) {
        return Stream.<Supplier<Option<String>>>of(() -> toString(duration.toDays(), "day", "days"),
                                                   () -> toString(duration.toHours() % HOURS_PER_DAY, "hour", "hours"),
                                                   () -> toString(duration.toMinutes() % MINUTES_PER_HOUR, "minute", "minutes"),
                                                   () -> toString(duration.getSeconds() % SECONDS_PER_MINUTE, "second", "seconds"),
                                                   () -> toString(duration.toMillis() % MILLIS_PER_SECOND, "milli", "millis"))
                       .flatMap(Supplier::get)
                       .take(1)
                       .mkString(", "); // ... in case you take more than 1 time unit
    }
    private static Option<String> toString(long duration, String singular, String plural) {
        return Match(duration).of(Case(0L, none()),
                                  Case(1L, of(duration + " " + singular)),
                                  Case($(), of(duration + " " + plural)));
    }
}