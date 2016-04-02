package pap.lorinc.socialgraph

import pap.lorinc.socialgraph.commands.DisplayWallCommand
import pap.lorinc.socialgraph.commands.FollowCommand
import pap.lorinc.socialgraph.commands.PostCommand
import pap.lorinc.socialgraph.commands.ReadCommand
import spock.lang.Specification
import spock.lang.Unroll

import static pap.lorinc.socialgraph.Input.parse
import static pap.lorinc.socialgraph.SocialGraph.getParsers

@Unroll class InputTest extends Specification {
    def setupSpec() { Input.PARSERS.addAll(parsers); }

    /*@formatter:off*/
    def 'can parse Post input: "#command"?'() {
        expect: parse(command).get() == new PostCommand(User.of(user), message) 

        where:  user | message
                'u1' | 'u1 m1' 
                'u2' | 'u2 m1' 
                'u2' | 'u2 m2' 
                
                command = "$user -> $message" 
    } 

    def 'can parse Follow input: "#command"?'() {
        expect: parse(command).get() == new FollowCommand(User.of(follower), User.of(followee)) 

        where:  follower | followee
                'u1'     | 'u1' 
                'u1'     | 'u2' 
                'u2'     | 'u1' 
                
                command = "$follower follows $followee" 
    }
    
    def 'can parse Read input: "#command"?'() {
        expect: parse(command).get() == new ReadCommand(User.of(user)) 

        where:  user << ['u1', 'u2', 'u2'] 
                command = "$user" 
    } 
    
    def 'can parse DisplayWall input: "#command"?'() {
        expect: parse(command).get() == new DisplayWallCommand(User.of(user)) 

        where:  user << ['u1', 'u2', 'u2'] 
                command = "$user wall" 
    }
    /*@formatter:on*/
}