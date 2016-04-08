package pap.lorinc.socialgraph.users;

import static pap.lorinc.socialgraph.users.UserRegistry.USER_REGISTRY;

public final class User {
    private final String name;

    User(String name) { this.name = name; }
    public static User of(String name) { return USER_REGISTRY.apply(name); }

    @Override public String toString() { return name; }
}
