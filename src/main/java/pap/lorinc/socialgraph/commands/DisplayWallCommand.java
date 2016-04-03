package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import java.util.stream.Stream;

public final class DisplayWallCommand extends Command {
    public DisplayWallCommand(User user) { super(user); }

    @Override public Stream<Post> get() {
        return user.subscriptions.stream()
                                 .flatMap(u -> u.timeline.stream())
                                 .sorted();
    }
}