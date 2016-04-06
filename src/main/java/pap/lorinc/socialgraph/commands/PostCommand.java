package pap.lorinc.socialgraph.commands;

import lombok.EqualsAndHashCode;
import lombok.Value;
import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import static javaslang.collection.Iterator.empty;

@Value @EqualsAndHashCode(callSuper = true)
public class PostCommand extends Command {
    private final String message;
    public PostCommand(User user, String message) {
        super(user);
        this.message = message;
    }

    @Override public Iterable<Post> apply() {
        user.post(new Post(user, message));
        return empty();
    }
} 