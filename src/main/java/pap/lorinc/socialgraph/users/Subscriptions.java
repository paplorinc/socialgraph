package pap.lorinc.socialgraph.users;

import javaslang.collection.HashMap;
import javaslang.collection.HashSet;
import javaslang.collection.Map;
import javaslang.collection.Set;

import static javaslang.collection.List.of;

public class Subscriptions {
    private Map<User, Set<User>> registry = HashMap.empty();

    public Set<User> get(User user) {
        return registry.get(user)
                       .getOrElse(() -> HashSet.of(user));
    }
    public void put(User user, User... followees) {
        Set<User> results = get(user).addAll(of(followees));
        registry = registry.put(user, results);
    }
}
