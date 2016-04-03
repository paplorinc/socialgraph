package pap.lorinc.socialgraph;

import java.time.ZonedDateTime;
import java.util.Objects;

import static java.lang.String.format;
import static java.time.Duration.between;
import static pap.lorinc.socialgraph.Time.TIME;
import static pap.lorinc.socialgraph.utils.DateTimes.durationToString;

public final class Post implements Comparable<Post> {
    private final ZonedDateTime time = TIME.now();
    private final User user;
    private final String message;
    public Post(User user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override public String toString() {
        return format("%s: `%s` (%s ago)",
                      user, message, durationToString(between(time, TIME.now())));
    }

    @Override public int compareTo(Post that) { return that.time.compareTo(this.time); }

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
