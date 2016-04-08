package pap.lorinc.socialgraph.users

import javaslang.collection.HashSet
import spock.lang.Specification

class SubscriptionsTest extends Specification {
    static users = ['u0', 'u1', 'u2', 'u3'].collect { User.of(it) }

    /*@formatter:off*/
    def 'users can subscribe to walls?'() {
        when:   def follower = users[0]
                def subscriptions = new Subscriptions() 
        then:   subscriptions.get(follower) == HashSet.of(follower) 
         
        when:   def followees = users[1..2]
                subscriptions.put(follower, *followees) 
        then:   subscriptions.get(follower) == HashSet.of(*(followees+follower)) 
                users[1..3].every { subscriptions.get(it) == HashSet.of(it)} 
         
        when:   def newFollowee = users[3]
                subscriptions.put(follower, newFollowee) 
        then:   subscriptions.get(follower) == HashSet.of(*(followees+follower+newFollowee)) 
    }
    
    def 'different users are separated?'() {
        when:   def subscriptions = new Subscriptions() 
                subscriptions.put(users[0],  users[2]) 
                subscriptions.put(users[1],  users[3]) 
        then:   subscriptions.get(users[0]) != subscriptions.get(users[1]) 
    } 
    /*@formatter:on*/
}
