package pap.lorinc.socialgraph;

import java.util.HashMap;
import java.util.Map;

public final class User {
    public final String name;
    private User(String name) { this.name = name; }

    private static final Map<String, User> CACHE = new HashMap<>();
    public static User of(String name) { return CACHE.computeIfAbsent(name, User::new); }

    @Override public String toString() { return name; }
}
