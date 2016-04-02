package pap.lorinc.socialgraph;

import pap.lorinc.socialgraph.commands.Command;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Input {
    public static final Collection<Function<String, Optional<? extends Command<?>>>> PARSERS = new LinkedHashSet<>(); // TODO DI?

    public static Optional<? extends Command<?>> parse(String commandLine) {
        return PARSERS.stream()
                      .flatMap(p -> stream(p.apply(commandLine)))
                      .findFirst();
    }

    static <T> Stream<T> stream(Optional<T> opt) { return opt.map(Stream::of).orElseGet(Stream::empty); } /* TODO Added in JDK 9 only */
}
