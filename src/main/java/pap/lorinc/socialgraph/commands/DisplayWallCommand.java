package pap.lorinc.socialgraph.commands;

import lombok.EqualsAndHashCode;
import lombok.Value;
import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

@Value @EqualsAndHashCode(callSuper = true)
public class DisplayWallCommand extends Command {
    public DisplayWallCommand(User user) { super(user); }

    /* TODO merge manually with priority queue */
    @Override public Iterable<Post> apply() {
        return user.getSubscriptions().flatMap(User::getPosts)
                   .toList().sorted();
    }
}