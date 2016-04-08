package pap.lorinc.socialgraph;

import pap.lorinc.socialgraph.actions.commands.Command;
import pap.lorinc.socialgraph.actions.commands.FollowCommand;
import pap.lorinc.socialgraph.actions.commands.PostCommand;
import pap.lorinc.socialgraph.actions.factory.Actions;
import pap.lorinc.socialgraph.actions.queries.DisplayWallQuery;
import pap.lorinc.socialgraph.actions.queries.Query;
import pap.lorinc.socialgraph.actions.queries.ReadQuery;
import pap.lorinc.socialgraph.posts.Post;
import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.Subscriptions;
import pap.lorinc.socialgraph.users.User;

import java.util.Scanner;
import java.util.function.Consumer;

import static javaslang.API.*;
import static javaslang.Predicates.instanceOf;
import static pap.lorinc.socialgraph.actions.factory.Matchers.matcher;

public class SocialGraph {
    public static void main(String... args) {
        System.out.println("This is a console-based social networking application with the following commands:\n" +
                           "posting:   <user name> -> <message>\n" +
                           "following: <user name> follows <another user>\n" +
                           "wall:      <user name> wall\n" +
                           "reading:   <user name>\n");

        main(() -> new Scanner(System.in),
             System.out::println,
             defaultFactory());
    }

    static void main(Iterable<String> in, Consumer<Post> out, Actions actions) {
        for (String actionString : in)
            actions.create(actionString)
                   .peek(action -> Match(action).of(
                           Case(instanceOf(Command.class), c -> run(() -> c.run())),
                           Case(instanceOf(Query.class), q -> run(() -> q.get().forEach(out))))
                        );
    }

    static Actions defaultFactory() {
        Timelines timelines = new Timelines();
        Subscriptions subscriptions = new Subscriptions();

        return new Actions(line -> matcher(line, "(?<user>.+?) -> (?<message>.+?)")
                                           .map(m -> new PostCommand(timelines, subscriptions, User.of(m.group("user")), m.group("message"))),

                           line -> matcher(line, "(?<user>.+?) follows (?<followee>.+?)")
                                           .map(m -> new FollowCommand(timelines, subscriptions, User.of(m.group("user")), User.of(m.group("followee")))),

                           line -> matcher(line, "(?<user>.+?) wall")
                                           .map(m -> new DisplayWallQuery(timelines, subscriptions, User.of(m.group("user")))),

                           line -> matcher(line, "(?<user>.+?)")
                                           .map(m -> new ReadQuery(timelines, subscriptions, User.of(m.group("user"))))
        );
    }
}