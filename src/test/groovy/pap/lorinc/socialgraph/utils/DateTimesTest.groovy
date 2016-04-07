package pap.lorinc.socialgraph.utils

import spock.lang.Specification
import spock.lang.Unroll

import static java.time.Duration.parse
import static pap.lorinc.socialgraph.utils.DateTimes.durationToString

@Unroll class DateTimesTest extends Specification {
    /*@formatter:off*/
    def 'durationToString #result?'() {
        when:   def duration = parse(durationString)
        then:   durationToString(duration) == result
        
        where:  durationString || result
                'PT0.001S'     || '1 milli' 
                'PT0.012S'     || '12 millis' 
                'PT1.123S'     || '1 second' 
                'PT62.012S'    || '1 minute' 
                
                'PT1S'         || '1 second' 
                'PT1.2S'       || '1 second' 
                'PT2.1234S'    || '2 seconds' 
                'PT82.1234S'   || '1 minute' 
                
                'PT1M12S'      || '1 minute' 
                'PT126M2S'     || '2 hours' 

                'PT1H4M'       || '1 hour' 
                'PT1H4M2S'     || '1 hour' 
                'PT2H2S'       || '2 hours' 
                'PT26H2S'      || '1 day' 
                
                'P1DT3H'       || '1 day' 
                'P2DT3H4M'     || '2 days' 
                'P2DT4M'       || '2 days' 
    }
    /*@formatter:on*/
} 