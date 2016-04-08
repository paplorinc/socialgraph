package pap.lorinc.socialgraph.users

import spock.lang.Specification
import spock.lang.Unroll

@Unroll class UserTest extends Specification {
    /*@formatter:off*/
    def 'users are created once?'() {
        given:  def (name1, name2) = ['u1', 'u2']
        
        when:   def user1  = User.of(name1)
                def user1b = User.of(name1)
        then:   user1.name == name1
                user1b.is(user1)
        
        when:   def user2 = User.of(name2)
        then:   user2.name == name2
                user1 != user2 
    }
    /*@formatter:on*/
}