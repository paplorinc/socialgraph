package pap.lorinc.socialgraph.commands;

import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FunctionalInterface
public interface CommandParser<T extends Command> {
    @Nullable T parse(@NotNull String command);

    static <R> R parse(@NotNull String line, @RegExp String pattern, Function<Matcher, R> ifMatched) {
        Matcher matcher = Pattern.compile("^" + pattern + "$").matcher(line.trim());
        return (matcher.find() && matcher.groupCount() > 0) ? ifMatched.apply(matcher)
                                                            : null;
    }
}                                 
