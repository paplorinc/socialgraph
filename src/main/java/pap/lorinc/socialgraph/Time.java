package pap.lorinc.socialgraph;

import java.time.Clock;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import static java.time.Clock.systemDefaultZone;
import static java.time.Duration.ofSeconds;
import static java.util.stream.Collectors.joining;

public enum Time {
    TIME;

    private int offsetSeconds = 0;
    public void advanceSeconds(int seconds) { offsetSeconds += seconds; }
    public ZonedDateTime now() { // TODO DI?
        Clock clock = Clock.offset(systemDefaultZone(), ofSeconds(offsetSeconds));
        return ZonedDateTime.now(clock);
    }

    static String durationToString(Duration duration) {
        return Stream.of(toStringIfValid(duration.toDays(), "day", "days"),
                         toStringIfValid(duration.toHours() % 24, "hour", "hours"),
                         toStringIfValid(duration.toMinutes() % 60, "minute", "minutes"),
                         toStringIfValid(duration.getSeconds() % 60, "second", "seconds"),
                         toStringIfValid(duration.toMillis() % 1000, "milli", "millis"))
                     .flatMap(Input::stream)
                     .limit(2)
                     .collect(joining(", "));
    }
    private static Optional<String> toStringIfValid(long duration, String singular, String plural) {
        switch ((int) duration) {
            case 0: return Optional.empty();
            case 1: return Optional.of(duration + " " + singular);
            default: return Optional.of(duration + " " + plural);
        }
    }
} 