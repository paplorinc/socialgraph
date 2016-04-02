package pap.lorinc.socialgraph

import pap.lorinc.socialgraph.commands.DisplayWallCommand
import pap.lorinc.socialgraph.commands.FollowCommand
import pap.lorinc.socialgraph.commands.PostCommand
import pap.lorinc.socialgraph.commands.ReadCommand
import spock.lang.Specification

import java.time.Duration
import java.time.ZonedDateTime

import static pap.lorinc.socialgraph.Time.TIME

class LoadTest extends Specification {
    static R = new Random(0)

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
        then:   'querying their timeline content is fast'
                timed {
                    users.each { read(it).count() }
                } < Duration.ofSeconds(1)
        and:   'querying their wall content is fast'
                timed {
                    users.each { displayWall(it).count() }
                } < Duration.ofSeconds(30)
    }
    /*@formatter:on*/

    static timed(Closure closure) {
        def start = ZonedDateTime.now()
        closure()
        def elapsed = Duration.between(start, ZonedDateTime.now())
        println Time.durationToString(elapsed)
        elapsed
    }

    static post(User user, String message) {
        new PostCommand(user, message).get()
        TIME.advanceSeconds(R.nextInt(10_0000))
    }
    static follow(User user, User followee) { new FollowCommand(user, followee).get() }
    static read(User user) { new ReadCommand(user).get() }
    static displayWall(User user) { new DisplayWallCommand(user).get() }
}
