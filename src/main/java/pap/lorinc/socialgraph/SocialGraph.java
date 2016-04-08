package pap.lorinc.socialgraph;

import pap.lorinc.socialgraph.commands.DisplayWallCommand;
import pap.lorinc.socialgraph.commands.FollowCommand;
import pap.lorinc.socialgraph.commands.PostCommand;
import pap.lorinc.socialgraph.commands.ReadCommand;
import pap.lorinc.socialgraph.commands.factory.Commands;
import pap.lorinc.socialgraph.posts.Post;
import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.Subscriptions;
import pap.lorinc.socialgraph.users.User;

import java.util.Scanner;
import java.util.function.Consumer;

import static pap.lorinc.socialgraph.commands.factory.Matchers.matcher;

public class SocialGraph {
    public static void main(String... args) {
        System.out.println("This is a console-based social networking application with the following commands:\n" +
                           "posting:   <user name> -> <message>\n" +
                           "following: <user name> follows <another user>\n" +
                           "wall:      <user name> wall\n" +
                           "reading:   <user name>\n");

        run(() -> new Scanner(System.in),
            System.out::println,
            defaultFactory());
    }

    public static void run(Iterable<String> in, Consumer<Post> out, Commands commands) {
        for (String commandLine : in)
            commands.create(commandLine)
                    .peek(c -> c.apply().forEach(out));
    }

    public static Commands defaultFactory() {
        Timelines timelines = new Timelines();
        Subscriptions subscriptions = new Subscriptions();

        return new Commands(line -> matcher(line, "(?<user>.+?) -> (?<message>.+?)")
                                                  .map(m -> new PostCommand(timelines, User.of(m.group("user")), m.group("message"))),

                                  line -> matcher(line, "(?<user>.+?) follows (?<followee>.+?)")
                                                  .map(m -> new FollowCommand(subscriptions, User.of(m.group("user")), User.of(m.group("followee")))),

                                  line -> matcher(line, "(?<user>.+?) wall")
                                                  .map(m -> new DisplayWallCommand(timelines, subscriptions, User.of(m.group("user")))),

                            line -> matcher(line, "(?<user>.+?)")
                                                  .map(m -> new ReadCommand(timelines, User.of(m.group("user"))))
        );
    }
}