package pap.lorinc.socialgraph.commands;

import lombok.EqualsAndHashCode;
import lombok.Value;
import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

@Value @EqualsAndHashCode(callSuper = true)
public class ReadCommand extends Command {
    public ReadCommand(User user) { super(user); }

    @Override public Iterable<Post> apply() { return user.getPosts(); }
}