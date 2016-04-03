package pap.lorinc.socialgraph.commands;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import java.util.stream.Stream;
import static java.util.stream.Stream.empty;

@ToString @EqualsAndHashCode(callSuper = true)
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
}