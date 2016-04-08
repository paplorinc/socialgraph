package pap.lorinc.socialgraph.actions.queries;

import pap.lorinc.socialgraph.posts.Post;
import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.Subscriptions;
import pap.lorinc.socialgraph.users.User;

public class ReadQuery extends Query {
    public ReadQuery(Timelines timelines, Subscriptions subscriptions, User user) { super(timelines, subscriptions, user); }

    @Override public Iterable<Post> get() { return timelines.get(user); }
}