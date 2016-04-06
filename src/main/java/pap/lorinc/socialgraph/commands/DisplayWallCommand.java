package pap.lorinc.socialgraph.commands;

import javaslang.collection.List;
import javaslang.collection.Traversable;
import lombok.EqualsAndHashCode;
import lombok.Value;
import pap.lorinc.socialgraph.Post;
import pap.lorinc.socialgraph.User;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

@Value @EqualsAndHashCode(callSuper = true)
public class DisplayWallCommand extends Command {
    public DisplayWallCommand(User user) { super(user); }

    @Override public Iterable<Post> apply() {
        PriorityQueue<List<Post>> queue = new PriorityQueue<>((l1, l2) -> l1.head().compareTo(l2.head()));
        user.getSubscriptions()
            .map(User::getPosts)
            .filter(Traversable::nonEmpty)
            .forEach(queue::add);

        return () -> new PostIterator(queue);
    }

    private static class PostIterator implements Iterator<Post> {
        private final Queue<List<Post>> queue;
        PostIterator(Queue<List<Post>> queue) { this.queue = queue; }

        @Override public boolean hasNext() { return !queue.isEmpty(); }
        @Override public Post next() {
            List<Post> minList = queue.remove();
            Post next = minList.head();

            List<Post> tail = minList.tail();
            if (tail.nonEmpty())
                queue.add(tail);

            return next;
        }
    }
}