package pap.lorinc.socialgraph.actions;

import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.Subscriptions;
import pap.lorinc.socialgraph.users.User;

public abstract class Action {
    protected final Timelines timelines;
    protected final Subscriptions subscriptions;
    protected final User user;
    protected Action(Timelines timelines, Subscriptions subscriptions, User user) {
        this.timelines = timelines;
        this.subscriptions = subscriptions;
        this.user = user;
    }
}