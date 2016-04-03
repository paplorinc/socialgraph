package pap.lorinc.socialgraph.utils;

import lombok.experimental.UtilityClass;
import org.intellij.lang.annotations.RegExp;

import java.util.Optional;
import java.util.regex.Matcher;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.regex.Pattern.compile;

@UtilityClass
public class Matchers {
    public static Optional<Matcher> matcher(String line, @RegExp String pattern) {
        Matcher matcher = compile(pattern).matcher(line.trim());
        return (matcher.matches() && (matcher.groupCount() > 0)) ? of(matcher)
                                                                 : empty();
    }
}
