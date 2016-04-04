package pap.lorinc.socialgraph.utils

import spock.lang.Specification
import spock.lang.Unroll

import static pap.lorinc.socialgraph.utils.Matchers.matcher

@Unroll class MatchersTest extends Specification {
    /*@formatter:off*/
    def 'matchers'() {
        expect: matcher(line, regexp).defined == matched 

        where:  line   | regexp      || matched
                'abc'  | 'abc'       || false
                                        
                ''     | 'a(b)c'     || false
                'a'    | 'a(b)c'     || false
                'ab'   | 'a(b)c'     || false
                'abcd' | 'a(b)c'     || false
        
                'abc'  | 'a(b)c'     || true
                'abc'  | '(a)(b)(c)' || true 
    }
    /*@formatter:on*/
}
