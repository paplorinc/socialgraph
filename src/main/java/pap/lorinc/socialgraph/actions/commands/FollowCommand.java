package pap.lorinc.socialgraph.actions.commands;

import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.Subscriptions;
import pap.lorinc.socialgraph.users.User;

public class FollowCommand extends Command {
    private final User followee;
    public FollowCommand(Timelines timelines, Subscriptions subscriptions, User user, User followee) {
        super(timelines, subscriptions, user);
        this.followee = followee;
    }

    @Override public void run() { subscriptions.put(user, followee); }
}