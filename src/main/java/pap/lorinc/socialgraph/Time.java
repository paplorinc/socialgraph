package pap.lorinc.socialgraph;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Clock;
import java.time.Duration;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public enum Time {
    TIME;
    public Clock CLOCK = Clock.systemDefaultZone();

    static @NotNull String durationToString(@NotNull Duration duration) {
        return Stream.of(toStringIfValid(duration.toDays(), "day", "days"),
                         toStringIfValid(duration.toHours() % 24, "hour", "hours"),
                         toStringIfValid(duration.toMinutes() % 60, "minute", "minutes"),
                         toStringIfValid(duration.getSeconds() % 60, "second", "seconds"),
                         toStringIfValid(duration.toMillis() % 1000, "milli", "millis"))
                     .filter(Objects::nonNull)
                     .limit(2)
                     .collect(joining(", "));
    }
    private static @Nullable String toStringIfValid(long duration, @NotNull String singular, @NotNull String plural) {
        switch ((int) duration) {
            case 0: return null;
            case 1: return duration + " " + singular;
            default: return duration + " " + plural;
        }
    }
} 