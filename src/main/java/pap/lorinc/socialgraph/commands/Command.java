package pap.lorinc.socialgraph.commands;

import javaslang.Function0;
import pap.lorinc.socialgraph.posts.Post;
import pap.lorinc.socialgraph.users.User;

public abstract class Command implements Function0<Iterable<Post>> {
    public final User user;
    Command(User user) { this.user = user; }
}