package pap.lorinc.socialgraph.posts;

import javaslang.collection.HashMap;
import javaslang.collection.List;
import javaslang.collection.Map;
import pap.lorinc.socialgraph.users.User;

public class Timelines {
    private Map<User, List<Post>> registry = HashMap.empty();

    public List<Post> get(User user) {
        return registry.get(user)
                       .getOrElse(List.empty());
    }
    public void put(User user, Post... posts) {
        List<Post> results = get(user).prependAll(List.of(posts));
        registry = registry.put(user, results);
    }
}
