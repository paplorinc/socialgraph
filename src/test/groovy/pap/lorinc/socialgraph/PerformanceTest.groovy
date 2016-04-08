package pap.lorinc.socialgraph

import pap.lorinc.socialgraph.commands.DisplayWallCommand
import pap.lorinc.socialgraph.commands.FollowCommand
import pap.lorinc.socialgraph.commands.PostCommand
import pap.lorinc.socialgraph.commands.ReadCommand
import pap.lorinc.socialgraph.posts.Timelines
import pap.lorinc.socialgraph.users.Subscriptions
import pap.lorinc.socialgraph.users.User
import spock.lang.Specification

import java.time.Duration
import java.time.ZonedDateTime

import static pap.lorinc.socialgraph.posts.DateTimes.durationToString
import static pap.lorinc.socialgraph.posts.TimeProvider.TIME

class PerformanceTest extends Specification {
    static R = new Random(0)
    static timelines = new Timelines()
    static subscriptions = new Subscriptions()

    /*@formatter:off*/
    def 'stress-test'() {
        given:  'lots of users and deterministic random order'
                def users = (1..10_000).collect { User.of("User_${it.toString().padLeft(5, '0')}") }
                def messageCounter = [:].withDefault { 0 }
        
        when:   'they send messages and subscribe to walls'
                1_000_000.times {
                    def userIndex = R.nextInt(users.size())
                    if (R.nextBoolean()) { /* send*/
                        def counter = messageCounter[userIndex]++
                        def message = "Message #$counter for user #$userIndex!"
                        post(users[userIndex], message)
                    } else { /* follow */
                        def followeeIndex = R.nextInt(users.size())
                        follow(users[userIndex], users[followeeIndex])
                    }
                }
        then:   'querying their List<Post> content is fast'
                timed {
                    for (u in users) 
                        read(u).collect()
                } < Duration.ofSeconds(1)
        and:   'querying their wall content is fast'
                timed {
                    for (u in users) 
                        displayWall(u).collect()
                } < Duration.ofMinutes(1)
    }
    /*@formatter:on*/

    static timed(Closure closure) {
        def start = ZonedDateTime.now()
        closure()
        def elapsed = Duration.between(start, ZonedDateTime.now())
        println durationToString(elapsed)
        elapsed
    }

    static void post(User user, String message) {
        new PostCommand(timelines, user, message).apply()
        TIME.advanceSeconds(R.nextInt(10_0000))
    }
    static void follow(User user, User followee) { new FollowCommand(subscriptions, user, followee).apply() }
    static read(User user) { new ReadCommand(timelines, user).apply() }
    static displayWall(User user) { new DisplayWallCommand(timelines, subscriptions, user).apply() }
}
