package pap.lorinc.socialgraph.actions.queries;

import pap.lorinc.socialgraph.actions.Action;
import pap.lorinc.socialgraph.posts.Post;
import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.Subscriptions;
import pap.lorinc.socialgraph.users.User;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Query extends Action implements Supplier<Iterable<Post>> {
    Query(Timelines timelines, Subscriptions subscriptions, User user) { super(timelines, subscriptions, user); }

    public void forEach(Consumer<Post> consumer) { get().forEach(consumer); }
}