package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.posts.Post;
import pap.lorinc.socialgraph.users.Subscriptions;
import pap.lorinc.socialgraph.users.User;

import static javaslang.collection.Iterator.empty;

public class FollowCommand extends Command {
    private final Subscriptions subscriptions;
    private final User followee;
    public FollowCommand(Subscriptions subscriptions, User user, User followee) {
        super(user);
        this.subscriptions = subscriptions;
        this.followee = followee;
    }

    @Override public Iterable<Post> apply() {
        subscriptions.put(user, followee);
        return empty();
    }
}