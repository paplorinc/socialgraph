package pap.lorinc.socialgraph.posts;

import pap.lorinc.socialgraph.users.User;

import java.time.ZonedDateTime;

import static java.lang.String.format;
import static java.time.Duration.between;
import static pap.lorinc.socialgraph.posts.DateTimes.durationToString;
import static pap.lorinc.socialgraph.posts.TimeProvider.TIME;

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
}
