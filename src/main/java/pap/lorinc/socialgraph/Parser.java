package pap.lorinc.socialgraph;

import javaslang.Function1;
import javaslang.collection.LinkedHashSet;
import javaslang.collection.Set;
import javaslang.control.Option;
import pap.lorinc.socialgraph.commands.Command;

public class Parser {
    private final Set<Function1<String, Option<? extends Command>>> parsers;
    @SafeVarargs Parser(Function1<String, Option<? extends Command>>... parsers) { this.parsers = LinkedHashSet.of(parsers); }

    public Option<? extends Command> parse(String commandLine) {
        return parsers.flatMap(p -> p.apply(commandLine))
                      .getOption();
    }
}
