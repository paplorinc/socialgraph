package pap.lorinc.socialgraph;

import java.util.*;

import static java.util.Arrays.asList;

public class User {
    public final Deque<Post> timeline = new ArrayDeque<>();
    public final Set<User> subscriptions = new HashSet<>(asList(this));

    public final String name;
    private User(String name) { this.name = name; }

    private static final Map<String, User> USERS = new HashMap<>();
    public static User of(String name) { return USERS.computeIfAbsent(name, User::new); }

    public void post(Post post) { timeline.addFirst(post); }
    public void subscribe(User followee) { subscriptions.add(followee); }

    @Override public String toString() { return name; }
}
