package pap.lorinc.socialgraph;

import java.util.*;

import static java.util.Arrays.asList;

public final class User {
    public final Deque<Post> timeline = new ArrayDeque<>();
    public final Set<User> subscriptions = new HashSet<>(asList(this));

    public final String name;
    private User(String name) { this.name = name; }

    private static final Map<String, User> CACHE = new HashMap<>();
    public static User of(String name) { return CACHE.computeIfAbsent(name, User::new); }

    @Override public String toString() { return name; }
}
