package pap.lorinc.socialgraph.actions.factory;

import javaslang.control.Option;
import org.intellij.lang.annotations.RegExp;

import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

public class Matchers {
    public static Option<Matcher> matcher(String line, @RegExp String pattern) {
        Matcher matcher = compile(pattern).matcher(line.trim());
        return Option.when(matcher.matches() && (matcher.groupCount() > 0),
                           () -> matcher);
    }
}
