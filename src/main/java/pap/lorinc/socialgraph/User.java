package pap.lorinc.socialgraph;

import javaslang.Function1;
import javaslang.collection.HashSet;
import javaslang.collection.List;
import javaslang.collection.Set;

public class User {
    private List<Post> timeline = List.empty();
    private Set<User> subscriptions = HashSet.of(this);
    private final String name;

    private User(String name) { this.name = name; }
    private static final Function1<String, User> OF = Function1.of(User::new).memoized();
    static User of(String name) { return OF.apply(name); }

    public List<Post> getPosts() { return timeline; }
    public void post(Post post) { timeline = timeline.prepend(post); }

    public Set<User> getSubscriptions() { return subscriptions; }
    public void subscribe(User followee) { subscriptions = subscriptions.add(followee); }

    @Override public String toString() { return name; }
}
