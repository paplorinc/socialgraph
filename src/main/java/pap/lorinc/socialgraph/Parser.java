package pap.lorinc.socialgraph;

import pap.lorinc.socialgraph.commands.Command;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static pap.lorinc.socialgraph.utils.Streams.stream;

public class Parser {
    private final Set<Function<String, Optional<? extends Command>>> parsers;
    @SafeVarargs Parser(Function<String, Optional<? extends Command>>... parsers) { this.parsers = new LinkedHashSet<>(asList(parsers)); }

    public Optional<? extends Command> parse(String commandLine) {
        return parsers.stream()
                      .flatMap(p -> stream(p.apply(commandLine)))
                      .findFirst();
    }
}
