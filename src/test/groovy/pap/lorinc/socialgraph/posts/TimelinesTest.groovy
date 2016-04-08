package pap.lorinc.socialgraph.posts

import javaslang.collection.List
import pap.lorinc.socialgraph.users.User
import spock.lang.Specification

class TimelinesTest extends Specification {
    static users = ['u0', 'u1', 'u2', 'u3'].collect { User.of(it) }

    /*@formatter:off*/
    def 'users can post?'() {
        when:   def user = users[0]
                def timelines = new Timelines() 
        then:   timelines.get(user).empty 
         
        when:   def messages = ['m1','m2'].collect { sleep(10); new Post(user, it) }
                timelines.put(user, *messages) 
        then:   timelines.get(user) == List.of(*messages).reversed() 
                users[1..3].every { timelines.get(it).empty } 
         
        when:   def newMessage = new Post(user, 'm3')
                timelines.put(user, newMessage) 
        then:   timelines.get(user) == List.of(*([newMessage]+messages)).reversed() 
    }
    
    def 'different users are separated?'() {
        when:   def timelines = new Timelines() 
                users.each { timelines.put(it, new Post(it, "${it}m1")) } 
        then:   users.every { timelines.get(it).size() == 1 } 
    }
    /*@formatter:on*/
}
