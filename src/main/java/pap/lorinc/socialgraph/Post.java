package pap.lorinc.socialgraph;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;

import static pap.lorinc.socialgraph.Time.TIME;
import static pap.lorinc.socialgraph.Time.durationToString;

public final class Post implements Comparable<Post> {
    private final User user;
    private final String message;
    private final ZonedDateTime time;
    public Post(User user, String message) {
        this.user = user;
        this.message = message;
        this.time = now();
    }
    private static @NotNull ZonedDateTime now() {return ZonedDateTime.now(TIME.CLOCK);}

    @Override public String toString() {
        return String.format("%s: `%s` (%s ago)",
                             user, message, durationToString(Duration.between(time, now())));
    }

    @Override public int compareTo(@NotNull Post that) { return that.time.compareTo(this.time); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;
        return Objects.equals(user, post.user)
               && Objects.equals(message, post.message)
               && Objects.equals(time, post.time);
    }
    @Override public int hashCode() { return Objects.hash(user, message, time); }
}
