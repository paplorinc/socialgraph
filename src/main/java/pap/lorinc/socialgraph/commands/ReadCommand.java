package pap.lorinc.socialgraph.commands;

import javaslang.collection.Iterator;
import lombok.EqualsAndHashCode;
import lombok.Value;
import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

@Value @EqualsAndHashCode(callSuper = true)
public class ReadCommand extends Command {
    public ReadCommand(User user) { super(user); }

    @Override public Iterator<Post> apply() { return user.getPosts().iterator(); }
}