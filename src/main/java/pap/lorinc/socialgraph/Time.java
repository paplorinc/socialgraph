package pap.lorinc.socialgraph;

import java.time.Clock;
import java.time.ZonedDateTime;

import static java.time.Clock.systemDefaultZone;
import static java.time.Duration.ofSeconds;

public enum Time {
    TIME;

    private int offsetSeconds = 0;
    public void advanceSeconds(int seconds) { offsetSeconds += seconds; }
    public ZonedDateTime now() {
        Clock clock = Clock.offset(systemDefaultZone(), ofSeconds(offsetSeconds));
        return ZonedDateTime.now(clock);
    }
} 