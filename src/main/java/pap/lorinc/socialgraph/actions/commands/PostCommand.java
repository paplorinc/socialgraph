package pap.lorinc.socialgraph.actions.commands;

import pap.lorinc.socialgraph.posts.Post;
import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.Subscriptions;
import pap.lorinc.socialgraph.users.User;

public class PostCommand extends Command {
    private final String message;
    public PostCommand(Timelines timelines, Subscriptions subscriptions, User user, String message) {
        super(timelines, subscriptions, user);
        this.message = message;
    }

    @Override public void run() { timelines.put(user, new Post(user, message)); }
} 