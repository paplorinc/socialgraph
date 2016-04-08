package pap.lorinc.socialgraph.commands.factory;

import javaslang.control.Option;
import org.intellij.lang.annotations.RegExp;

import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;
import static javaslang.control.Option.none;
import static javaslang.control.Option.of;

public class Matchers {
    public static Option<Matcher> matcher(String line, @RegExp String pattern) {
        Matcher matcher = compile(pattern).matcher(line.trim());
        return (matcher.matches() && (matcher.groupCount() > 0)) ? of(matcher)
                                                                 : none();
    }
}
