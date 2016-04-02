package pap.lorinc.socialgraph;

import pap.lorinc.socialgraph.commands.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static pap.lorinc.socialgraph.commands.CommandParser.parse;

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
        Input.register(getParsers());

        Scanner scanner = new Scanner(in);
        while (true) {
            Command<Object> command = Input.parse(scanner.nextLine());
            if (command == null) return;

            command.get().forEachOrdered(out::println);
        }
    }

    public static CommandParser[] getParsers() {
        return new CommandParser[]{
                line -> parse(line, "(?<user>.+?) -> (?<message>.+?)",
                              m -> new PostCommand(User.of(m.group("user")), m.group("message"))),

                line -> parse(line, "(?<user>.+?) follows (?<followee>.+?)",
                              m -> new FollowCommand(User.of(m.group("user")), User.of(m.group("followee")))),

                line -> parse(line, "(?<user>.+?) wall",
                              m -> new DisplayWallCommand(User.of(m.group("user")))),

                line -> parse(line, "(?<user>.+?)",
                              m -> new ReadCommand(User.of(m.group("user"))))
        };
    }
}