package pap.lorinc.socialgraph.commands;

import pap.lorinc.socialgraph.User;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class Command<T> implements Supplier<Stream<T>> {
    public final User user;
    protected Command(User user) { this.user = user; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;
        return Objects.equals(user, command.user);
    }
    @Override public int hashCode() { return Objects.hash(user); }
}