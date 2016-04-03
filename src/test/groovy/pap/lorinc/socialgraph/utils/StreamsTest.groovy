package pap.lorinc.socialgraph.utils

import spock.lang.Specification
import spock.lang.Unroll

import static java.util.Optional.ofNullable
import static pap.lorinc.socialgraph.utils.Streams.stream

@Unroll class StreamsTest extends Specification {
    /*@formatter:off*/
    def 'Optional is steamable?'() {
        given:  def optionals = [1,2,null].collect { ofNullable(it) }
        expect: optionals.stream().flatMap { stream(it) }.collect() == [1,2] 
    }
    /*@formatter:on*/
}
