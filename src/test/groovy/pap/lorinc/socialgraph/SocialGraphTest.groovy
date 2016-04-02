package pap.lorinc.socialgraph

import org.spockframework.util.StringMessagePrintStream
import spock.lang.Specification

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
                       Charlie wall
                    '''.bytes 
                def expectedOutput = new ArrayDeque<String>(
                    '''Alice: `I love the weather today`
                       Bob: `Good game though.`
                       Bob: `Damn! We lost!`
                       Charlie: `I'm in New York today! Anyone wants to have a coffee?`
                       Alice: `I love the weather today`
                       Charlie: `I'm in New York today! Anyone wants to have a coffee?`
                       Bob: `Good game though.`
                       Bob: `Damn! We lost!`
                       Alice: `I love the weather today`
                    '''.trim().split('\n').toList())
        
        then:   run(new ByteArrayInputStream(input),
                    { assert it.startsWith(expectedOutput.pop().trim()) } as StringMessagePrintStream 
                )
    }   
    /*@formatter:on*/
}
