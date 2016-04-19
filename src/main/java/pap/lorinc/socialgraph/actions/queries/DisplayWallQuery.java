package pap.lorinc.socialgraph.actions.queries;

import javaslang.collection.Seq;
import pap.lorinc.socialgraph.posts.Post;
import pap.lorinc.socialgraph.posts.Timelines;
import pap.lorinc.socialgraph.users.Subscriptions;
import pap.lorinc.socialgraph.users.User;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DisplayWallQuery extends Query {
    public DisplayWallQuery(Timelines timelines, Subscriptions subscriptions, User user) { super(timelines, subscriptions, user); }

    @Override public Iterable<Post> get() {
        PriorityQueue<Seq<Post>> queue = new PriorityQueue<>((l1, l2) -> l1.head().compareTo(l2.head()));
        subscriptions.get(user)
                     .map(timelines::get)
                     .filter(Seq::nonEmpty)
                     .forEach(queue::add);

        return () -> new MergingTimelineIterator(queue);
    }

    private static class MergingTimelineIterator implements Iterator<Post> {
        private final Queue<Seq<Post>> queue;
        MergingTimelineIterator(Queue<Seq<Post>> queue) { this.queue = queue; }

        @Override public boolean hasNext() { return !queue.isEmpty(); }
        @Override public Post next() {
            Seq<Post> minList = queue.remove();
            Post next = minList.head();

            Seq<Post> tail = minList.tail();
            if (tail.nonEmpty())
                queue.add(tail);

            return next;
        }
    }
}