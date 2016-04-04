package pap.lorinc.socialgraph.commands;

import javaslang.collection.Iterator;
import lombok.EqualsAndHashCode;
import lombok.Value;
import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import static javaslang.collection.Iterator.empty;

@Value @EqualsAndHashCode(callSuper = true)
public class FollowCommand extends Command {
    private final User followee;
    public FollowCommand(User user, User followee) {
        super(user);
        this.followee = followee;
    }

    @Override public Iterator<Post> apply() {
        user.subscribe(followee);
        return empty();
    }
}