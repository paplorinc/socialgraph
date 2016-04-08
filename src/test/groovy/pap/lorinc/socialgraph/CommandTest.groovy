package pap.lorinc.socialgraph

import pap.lorinc.socialgraph.commands.DisplayWallCommand
import pap.lorinc.socialgraph.commands.FollowCommand
import pap.lorinc.socialgraph.commands.PostCommand
import pap.lorinc.socialgraph.commands.ReadCommand
import pap.lorinc.socialgraph.posts.Timelines
import pap.lorinc.socialgraph.users.Subscriptions
import pap.lorinc.socialgraph.users.User
import spock.lang.Specification

import static pap.lorinc.socialgraph.posts.TimeProvider.TIME

class CommandTest extends Specification {
    static users = ['u0', 'u1', 'u2', 'u3'].collect { User.of(it) }
    static timelines = new Timelines()
    static subscriptions = new Subscriptions()

    /*@formatter:off*/
    def 'executing different commands?'() {
        // 1) posted messages appear on the wall
        when:   post(users[0], 'u0 m1')
                post(users[1], 'u1 m1')
                post(users[1], 'u1 m2')
                post(users[0], 'u0 m2')
                post(users[2], 'u2 m1')
                post(users[0], 'u0 m3')
        then:   userTimelines()*.size() == [3, 2, 1, 0]
                
        // 2) the timeline is sorted in a newest-on-top order
        expect: userTimelines().each { assert it.collect() == it.collect().toSorted() }

        // 3) users can follow other walls
        when:   follow(0, 0)
        then:   userWalls()*.size() == [3, 2, 1, 0]

        when:   follow(0, 1)
        then:   userWalls()*.size() == [3+2, 2, 1, 0]

        when:   follow(1, 0)
        then:   userWalls()*.size() == [3+2, 2+3, 1, 0]

        when:   follow(1, 2)
        then:   userWalls()*.size() == [3+2, 2+3+1, 1, 0]

        when:   follow(3, 1)
        then:   userWalls()*.size() == [3+2, 2+3+1, 1, 2]
                
        //4) adding new posts will update the follower walls
        when:   post(users[0], 'u0 m4')
                post(users[1], 'u1 m3')
        then:   userWalls()*.size() == [4+3, 3+4+1, 1, 3]
    
        // 5) the walls are sorted in a newest-on-top order?'() {
        expect: for (posts in userWalls()*.collect()) 
                    assert posts == posts.toSorted()
    } 
    /*@formatter:on*/

    static void post(User user, String message) {
        new PostCommand(timelines, user, message).apply()
        TIME.advanceSeconds(new Random().nextInt(10_0000))
    }
    static void follow(int userId, int followeeId) { new FollowCommand(subscriptions, users[userId], users[followeeId]).apply() }
    static userTimelines() { users.collect { new ReadCommand(timelines, it).apply() } }
    static userWalls() { users.collect { new DisplayWallCommand(timelines, subscriptions, it).apply() } }
}