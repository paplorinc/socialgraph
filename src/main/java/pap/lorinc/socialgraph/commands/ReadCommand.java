package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import java.util.stream.Stream;

public final class ReadCommand extends Command<Post> {
    public ReadCommand(User user) { super(user); }

    @Override public Stream<Post> get() { return Stream.empty(); }
}