package pap.lorinc.socialgraph.actions.factory

import pap.lorinc.socialgraph.actions.commands.FollowCommand
import pap.lorinc.socialgraph.actions.commands.PostCommand
import pap.lorinc.socialgraph.actions.queries.DisplayWallQuery
import pap.lorinc.socialgraph.actions.queries.ReadQuery
import pap.lorinc.socialgraph.users.User
import spock.lang.Specification
import spock.lang.Unroll

import static pap.lorinc.socialgraph.SocialGraph.defaultFactory

@Unroll class ActionFactoryTest extends Specification {
    static factory = defaultFactory()

    /*@formatter:off*/
    def 'can parse Post input: "#command"?'() {
        when:   def post = factory.create("$user -> $message").get() as PostCommand
        then:   [post.user, post.message] == [User.of(user), message] 

        where:  user | message
                'u1' | 'u1 m1' 
                'u2' | 'u2 m1' 
                'u2' | 'u2 m2' 
    } 

    def 'can parse Follow input: "#command"?'() {
        when:   def post = factory.create("$follower follows $followee").get() as FollowCommand
        then:   [post.user, post.followee] == [User.of(follower), User.of(followee)] 

        where:  follower | followee
                'u1'     | 'u1' 
                'u1'     | 'u2' 
                'u2'     | 'u1' 
    }
    
    def 'can parse Read input: "#command"?'() {
        when:   def post = factory.create("$user").get() as ReadQuery
        then:   post.user == User.of(user) 

        where:  user << ['u1', 'u2', 'u2'] 
    } 
    
    def 'can parse DisplayWall input: "#command"?'() {
        when:   def post = factory.create("$user wall").get() as DisplayWallQuery
        then:   post.user == User.of(user) 

        where:  user << ['u1', 'u2', 'u2'] 
    }
    /*@formatter:on*/
}