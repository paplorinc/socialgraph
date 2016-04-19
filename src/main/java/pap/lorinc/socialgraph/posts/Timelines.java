package pap.lorinc.socialgraph.posts;

import javaslang.collection.HashMap;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.collection.Seq;
import pap.lorinc.socialgraph.users.User;

import static javaslang.collection.List.of;

public class Timelines {
    private Map<User, Seq<Post>> registry = HashMap.empty();

    public Seq<Post> get(User user) {
        return registry.get(user)
                       .getOrElse(List::empty);
    }
    public void put(User user, Post... posts) {
        Seq<Post> results = get(user).prependAll(of(posts));
        registry = registry.put(user, results);
    }
}
