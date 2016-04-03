package pap.lorinc.socialgraph;

import pap.lorinc.socialgraph.commands.Command;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Collections.addAll;
import static pap.lorinc.socialgraph.utils.Streams.stream;

public class Parser {
    private final Collection<Function<String, Optional<? extends Command<?>>>> parsers = new LinkedHashSet<>();
    public @SafeVarargs Parser(Function<String, Optional<? extends Command<?>>>... parsers) { addAll(this.parsers, parsers); }

    public Optional<? extends Command<?>> parse(String commandLine) {
        return parsers.stream()
                      .flatMap(p -> stream(p.apply(commandLine)))
                      .findFirst();
    }
}
