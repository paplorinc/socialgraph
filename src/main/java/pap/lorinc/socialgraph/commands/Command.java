package pap.lorinc.socialgraph.commands;

import javaslang.Function0;
import javaslang.collection.Iterator;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

@ToString @EqualsAndHashCode
public abstract class Command implements Function0<Iterator<Post>> {
    public final User user;
    Command(User user) { this.user = user; }
}