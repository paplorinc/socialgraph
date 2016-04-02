package pap.lorinc.socialgraph;

import org.jetbrains.annotations.Nullable;
import pap.lorinc.socialgraph.commands.Command;
import pap.lorinc.socialgraph.commands.CommandParser;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

import static java.util.Collections.addAll;

public class Input {
    private static final Collection<CommandParser<?>> COMMAND_PARSERS = new LinkedHashSet<>();

    @SafeVarargs public static <T extends CommandParser<?>> void register(T... parsers) { addAll(COMMAND_PARSERS, parsers); }

    public static @Nullable Command<Object> parse(String commandLine) {
        return COMMAND_PARSERS.stream()
                              .map(p -> p.parse(commandLine)).filter(Objects::nonNull)
                              .findFirst().orElse(null);
    }
}
