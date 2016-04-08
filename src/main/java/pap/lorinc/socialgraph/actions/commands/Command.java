package pap.lorinc.socialgraph.actions.commands;

import pap.lorinc.socialgraph.actions.Action;
import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.Subscriptions;
import pap.lorinc.socialgraph.users.User;

public abstract class Command extends Action implements Runnable {
    Command(Timelines timelines, Subscriptions subscriptions, User user) { super(timelines, subscriptions, user); }
}