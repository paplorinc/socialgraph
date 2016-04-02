package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.User;

import java.util.Objects;
import java.util.stream.Stream;

public final class FollowCommand extends Command<Void> {
    private final User followee;
    public FollowCommand(User user, User followee) {
        super(user);
        this.followee = followee;
    }

    @Override public Stream<Void> get() {
        user.subscriptions.add(followee);
        return Stream.empty();
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass() || !super.equals(o)) return false;

        FollowCommand that = (FollowCommand) o;
        return Objects.equals(followee, that.followee);
    }
    @Override public int hashCode() { return Objects.hash(super.hashCode(), followee); }
}