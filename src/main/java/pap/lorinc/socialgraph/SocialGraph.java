package pap.lorinc.socialgraph;

import org.intellij.lang.annotations.RegExp;
import pap.lorinc.socialgraph.commands.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class SocialGraph {
    public static void main(String... args) {
        System.out.println("This is a console-based social networking application with the following commands:\n" +
                           "posting:   <user name> -> <message>\n" +
                           "following: <user name> follows <another user>\n" +
                           "wall:      <user name> wall\n" +
                           "reading:   <user name>\n");

        run(System.in, System.out);
    }

    public static void run(InputStream in, PrintStream out) {
        Input.PARSERS.addAll(getParsers()); // TODO DI?

        for (Scanner scanner = new Scanner(in); scanner.hasNext(); )
            Input.parse(scanner.nextLine())
                 .ifPresent(c -> c.get().forEachOrdered(out::println));
    }

    public static List<Function<String, Optional<? extends Command<?>>>> getParsers() {
        return asList(line -> matcher(line, "(?<user>.+?) -> (?<message>.+?)")
                                      .map(m -> new PostCommand(User.of(m.group("user")), m.group("message"))),

                      line -> matcher(line, "(?<user>.+?) follows (?<followee>.+?)")
                                      .map(m -> new FollowCommand(User.of(m.group("user")), User.of(m.group("followee")))),

                      line -> matcher(line, "(?<user>.+?) wall")
                                      .map(m -> new DisplayWallCommand(User.of(m.group("user")))),

                      line -> matcher(line, "(?<user>.+?)")
                                      .map(m -> new ReadCommand(User.of(m.group("user"))))
                     );
    }

    private static Optional<Matcher> matcher(String line, @RegExp String pattern) {
        Matcher matcher = Pattern.compile("^" + pattern + "$").matcher(line.trim());
        return (matcher.find() && matcher.groupCount() > 0) ? Optional.of(matcher)
                                                            : Optional.empty();
    }
}