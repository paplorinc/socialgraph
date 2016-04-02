package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import java.util.Objects;
import java.util.stream.Stream;

public final class PostCommand extends Command<Void> {
    private final String message;
    public PostCommand(User user, String message) {
        super(user);
        this.message = message;
    }

    @Override public Stream<Void> get() {
        user.timeline.addFirst(new Post(user, message));
        return Stream.empty();
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass() || !super.equals(o)) return false;

        PostCommand that = (PostCommand) o;
        return Objects.equals(message, that.message);
    }
    @Override public int hashCode() { return Objects.hash(super.hashCode(), message); }
} 