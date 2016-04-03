package pap.lorinc.socialgraph.commands;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import java.util.stream.Stream;
import static java.util.stream.Stream.empty;

@ToString @EqualsAndHashCode(callSuper = true)
public final class PostCommand extends Command {
    private final String message;
    public PostCommand(User user, String message) {
        super(user);
        this.message = message;
    }

    @Override public Stream<Post> get() {
        user.post(new Post(user, message));
        return empty();
    }
} 