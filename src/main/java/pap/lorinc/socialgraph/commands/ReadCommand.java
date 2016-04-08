package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.posts.Post;
import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.User;

public class ReadCommand extends Command {
    private final Timelines timelines;
    public ReadCommand(Timelines timelines, User user) {
        super(user);
        this.timelines = timelines;
    }

    @Override public Iterable<Post> apply() { return timelines.get(user); }
}