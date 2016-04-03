package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Stream.empty;

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

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass() || !super.equals(o)) return false;

        PostCommand that = (PostCommand) o;
        return Objects.equals(message, that.message);
    }
    @Override public int hashCode() { return Objects.hash(super.hashCode(), message); }
} 