package pap.lorinc.socialgraph.commands;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import java.util.function.Supplier;
import java.util.stream.Stream;

@ToString @EqualsAndHashCode
public abstract class Command implements Supplier<Stream<Post>> {
    public final User user;
    Command(User user) { this.user = user; }
}