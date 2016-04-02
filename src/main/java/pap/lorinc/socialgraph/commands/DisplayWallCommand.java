package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import java.util.Collection;
import java.util.stream.Stream;

public final class DisplayWallCommand extends Command<Post> {
    public DisplayWallCommand(User user) { super(user); }

    @Override public Stream<Post> get() {
        return user.wall.parallelStream()
                        .flatMap(Collection::stream)
                        .sorted(); // linear for all subscribed posts
    }
}