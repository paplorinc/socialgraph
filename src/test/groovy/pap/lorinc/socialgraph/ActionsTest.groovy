package pap.lorinc.socialgraph

import pap.lorinc.socialgraph.actions.commands.FollowCommand
import pap.lorinc.socialgraph.actions.commands.PostCommand
import pap.lorinc.socialgraph.actions.queries.DisplayWallQuery
import pap.lorinc.socialgraph.actions.queries.ReadQuery
import pap.lorinc.socialgraph.posts.Timelines
import pap.lorinc.socialgraph.users.Subscriptions
import pap.lorinc.socialgraph.users.User
import spock.lang.Specification

import static pap.lorinc.socialgraph.posts.TimeProvider.TIME

class ActionsTest extends Specification {
    static users = ['u0', 'u1', 'u2', 'u3'].collect { User.of(it) }
    static timelines = new Timelines()
    static subscriptions = new Subscriptions()

    /*@formatter:off*/
    def 'executing different commands?'() {
        /* 1) posted messages appear on the wall */
        when:   post(users[0], 'u0 m1')
                post(users[1], 'u1 m1')
                post(users[1], 'u1 m2')
                post(users[0], 'u0 m2')
                post(users[2], 'u2 m1')
                post(users[0], 'u0 m3')
         
        then:   def expectedPostCounts = [3,2,1,0]
                userTimelines()*.size() ==  expectedPostCounts
                
        /* 2) the timeline is sorted in a newest-on-top order */
        expect: userTimelines().each { assert it.collect() == it.collect().toSorted() }

        /* 3) users can follow other walls*/ 
        when:   follow(users[0], users[0])
                def expectedWallSizes = expectedPostCounts.collect() 
        then:   userWalls()*.size() == expectedWallSizes

        when:   follow(users[0], users[1])
                expectedWallSizes[0] += expectedPostCounts[1]
        then:   userWalls()*.size() == expectedWallSizes

        when:   follow(users[1], users[0])
                expectedWallSizes[1] += expectedPostCounts[0]
        then:   userWalls()*.size() == expectedWallSizes

        when:   follow(users[1], users[2])
                expectedWallSizes[1] += expectedPostCounts[2]
        then:   userWalls()*.size() == expectedWallSizes

        when:   follow(users[3], users[1])
                expectedWallSizes[3] += expectedPostCounts[1] 
        then:   userWalls()*.size() == expectedWallSizes
                
        /*4) adding new posts will update the follower walls */
        when:   post(users[0], 'u0 m4')
                expectedWallSizes[0]++ 
                expectedWallSizes[1]++ 
                
        and:    post(users[1], 'u1 m3')
                expectedWallSizes[0]++ 
                expectedWallSizes[1]++ 
                expectedWallSizes[3]++ 
        then:   userWalls()*.size() == expectedWallSizes
    
        /* 5) the walls are sorted in a newest-on-top order? */
        expect: for (posts in userWalls()*.collect()) 
                    assert posts == posts.toSorted()
    } 
    /*@formatter:on*/

    static void post(User user, String message) {
        new PostCommand(timelines, subscriptions, user, message).run()
        TIME.advanceSeconds(new Random().nextInt(10_0000))
    }
    static void follow(User user, User followee) { new FollowCommand(timelines, subscriptions, user, followee).run() }
    static userTimelines() { users.collect { new ReadQuery(timelines, subscriptions, it).get() } }
    static userWalls() { users.collect { new DisplayWallQuery(timelines, subscriptions, it).get() } }
}