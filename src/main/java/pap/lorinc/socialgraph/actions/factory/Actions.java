package pap.lorinc.socialgraph.actions.factory;

import javaslang.Function1;
import javaslang.collection.LinkedHashSet;
import javaslang.collection.Set;
import javaslang.control.Option;
import pap.lorinc.socialgraph.actions.Action;

public class Actions {
    private final Set<Function1<String, Option<? extends Action>>> parsers;
    public @SafeVarargs Actions(Function1<String, Option<? extends Action>>... parsers) { this.parsers = LinkedHashSet.of(parsers); }

    public Option<? extends Action> create(String commandLine) {
        return parsers.flatMap(p -> p.apply(commandLine))
                      .getOption();
    }
}
