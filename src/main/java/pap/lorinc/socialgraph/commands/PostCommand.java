package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.posts.Post;
import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.User;

import static javaslang.collection.Iterator.empty;

public class PostCommand extends Command {
    private final Timelines timelines;
    private final String message;
    public PostCommand(Timelines timelines, User user, String message) {
        super(user);
        this.timelines = timelines;
        this.message = message;
    }

    @Override public Iterable<Post> apply() {
        timelines.put(user, new Post(user, message));
        return empty();
    }
} 