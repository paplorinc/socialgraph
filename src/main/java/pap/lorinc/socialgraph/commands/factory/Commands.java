package pap.lorinc.socialgraph.commands.factory;

import javaslang.Function1;
import javaslang.collection.LinkedHashSet;
import javaslang.collection.Set;
import javaslang.control.Option;
import pap.lorinc.socialgraph.commands.Command;

public class Commands {
    private final Set<Function1<String, Option<? extends Command>>> parsers;
    public @SafeVarargs Commands(Function1<String, Option<? extends Command>>... parsers) { this.parsers = LinkedHashSet.of(parsers); }

    public Option<? extends Command> create(String commandLine) {
        return parsers.flatMap(p -> p.apply(commandLine))
                      .getOption();
    }
}
