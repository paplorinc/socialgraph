package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Stream.empty;

public final class FollowCommand extends Command {
    private final User followee;
    public FollowCommand(User user, User followee) {
        super(user);
        this.followee = followee;
    }

    @Override public Stream<Post> get() {
        user.subscribe(followee);
        return empty();
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass() || !super.equals(o)) return false;

        FollowCommand that = (FollowCommand) o;
        return Objects.equals(followee, that.followee);
    }
    @Override public int hashCode() { return Objects.hash(super.hashCode(), followee); }
}