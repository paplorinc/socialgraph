package pap.lorinc.socialgraph.users;

import javaslang.collection.*;

public class Subscriptions {
    private Map<User, Set<User>> registry = HashMap.empty();

    public Set<User> get(User user) {
        return registry.get(user)
                       .getOrElse(HashSet.of(user));
    }
    public void put(User user, User... followees) {
        Set<User> results = get(user).addAll(List.of(followees));
        registry = registry.put(user, results);
    }
}
