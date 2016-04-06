package pap.lorinc.socialgraph;

import pap.lorinc.socialgraph.commands.DisplayWallCommand;
import pap.lorinc.socialgraph.commands.FollowCommand;
import pap.lorinc.socialgraph.commands.PostCommand;
import pap.lorinc.socialgraph.commands.ReadCommand;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static pap.lorinc.socialgraph.utils.Matchers.matcher;

public class SocialGraph {
    public static void main(String... args) {
        System.out.println("This is a console-based social networking application with the following commands:\n" +
                           "posting:   <user name> -> <message>\n" +
                           "following: <user name> follows <another user>\n" +
                           "wall:      <user name> wall\n" +
                           "reading:   <user name>\n");

        run(System.in, System.out, defaultParser());
    }

    public static void run(InputStream in, PrintStream out, Parser parser) {
        for (Scanner scanner = new Scanner(in); scanner.hasNext(); )
            parser.parse(scanner.nextLine())
                  .peek(c -> c.apply().forEach(out::println));
    }

    public static Parser defaultParser() {
        return new Parser(line -> matcher(line, "(?<user>.+?) -> (?<message>.+?)")
                                          .map(m -> new PostCommand(User.of(m.group("user")), m.group("message"))),

                          line -> matcher(line, "(?<user>.+?) follows (?<followee>.+?)")
                                          .map(m -> new FollowCommand(User.of(m.group("user")), User.of(m.group("followee")))),

                          line -> matcher(line, "(?<user>.+?) wall")
                                          .map(m -> new DisplayWallCommand(User.of(m.group("user")))),

                          line -> matcher(line, "(?<user>.+?)")
                                          .map(m -> new ReadCommand(User.of(m.group("user"))))
        );
    }
}