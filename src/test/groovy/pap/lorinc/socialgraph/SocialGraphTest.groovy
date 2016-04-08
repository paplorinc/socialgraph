package pap.lorinc.socialgraph

import spock.lang.Specification

import static pap.lorinc.socialgraph.SocialGraph.defaultFactory
import static pap.lorinc.socialgraph.SocialGraph.run

class SocialGraphTest extends Specification {
    /*@formatter:off*/
    def 'example input gives the same output?'() {
        when:   def input =
                    '''Alice -> I love the weather today
                       Bob -> Damn! We lost!
                       Bob -> Good game though.
                       Alice
                       Bob
                       Charlie -> I'm in New York today! Anyone wants to have a coffee?
                       Charlie follows Alice
                       Charlie wall
                       Charlie follows Bob
                       Charlie wall'''.readLines() 
                def output =
                    '''Alice: `I love the weather today`
                       Bob: `Good game though.`
                       Bob: `Damn! We lost!`
                       Charlie: `I'm in New York today! Anyone wants to have a coffee?`
                       Alice: `I love the weather today`
                       Charlie: `I'm in New York today! Anyone wants to have a coffee?`
                       Bob: `Good game though.`
                       Bob: `Damn! We lost!`
                       Alice: `I love the weather today`'''.readLines() as ArrayDeque<String> 
        
        then:   run(input,
                    { 
                        sleep(100)
                        assert "$it".startsWith(output.pop().trim())
                    },
                    defaultFactory()
                )
    }   
    /*@formatter:on*/
}
