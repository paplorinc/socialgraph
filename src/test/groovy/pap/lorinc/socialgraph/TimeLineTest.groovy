package pap.lorinc.socialgraph

import pap.lorinc.socialgraph.commands.DisplayWallCommand
import pap.lorinc.socialgraph.commands.FollowCommand
import pap.lorinc.socialgraph.commands.PostCommand
import pap.lorinc.socialgraph.commands.ReadCommand
import spock.lang.Specification
import spock.lang.Stepwise

import static pap.lorinc.socialgraph.Time.TIME

@Stepwise class TimeLineTest extends Specification {
    static users = ['u0', 'u1', 'u2', 'u3'].collect { User.of(it) }

    /*@formatter:off*/
    def '1) posted messages appear on the wall?'() {
        when:   post(0, 'u0 m1')
                post(1, 'u1 m1')
                post(1, 'u1 m2')
                post(0, 'u0 m2')
                post(2, 'u2 m1')
                post(0, 'u0 m3')
        then:   timelineContents()*.size() == [3, 2, 1, 0]
    }

    def '2) the timeline is sorted in a newest-on-top order?'() {
        expect: timelineContents().each { assert it.collect() == it.collect().toSorted() }
    }

    def '3) users can follow other walls?'() {
        when:   follow(0, 0)
        then:   wallContents()*.size() == [3, 2, 1, 0]

        when:   follow(0, 1)
        then:   wallContents()*.size() == [3+2, 2, 1, 0]

        when:   follow(1, 0)
        then:   wallContents()*.size() == [3+2, 2+3, 1, 0]

        when:   follow(1, 2)
        then:   wallContents()*.size() == [3+2, 2+3+1, 1, 0]

        when:   follow(3, 1)
        then:   wallContents()*.size() == [3+2, 2+3+1, 1, 2]
    }
    
    def '4) adding new posts will update the follower walls'() {
        when:   post(0, 'u0 m4')
                post(1, 'u1 m3')
        then:   wallContents()*.size() == [4+3, 3+4+1, 1, 3]
    }

    def '5) the walls are sorted in a newest-on-top order?'() {
        expect: wallContents().each { assert it.collect() == it.collect().toSorted() }
    } 
    /*@formatter:on*/

    static post(int userIndex, String message) {
        new PostCommand(users[userIndex], message).get()
        TIME.advanceSeconds(new Random().nextInt(10_0000))
    }
    static follow(int userId, int followeeId) { new FollowCommand(users[userId], users[followeeId]).get() }
    static timelineContents() { users.indices.collect { new ReadCommand(users[it]).get().collect() } }
    static wallContents() { users.indices.collect { new DisplayWallCommand(users[it]).get().collect() } }
}