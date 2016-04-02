package pap.lorinc.socialgraph

import spock.lang.Specification
import spock.lang.Unroll

import java.time.Duration

@Unroll class TimeTest extends Specification {
    /*@formatter:off*/
    def 'durationToString #result?'() {
        when:   def duration = Duration.parse(durationString)
        then:   Time.durationToString(duration) == result
        
        where:  durationString || result
                'PT0.001S'     || '1 milli' 
                'PT0.012S'     || '12 millis' 
                'PT1.123S'     || '1 second, 123 millis' 
                'PT62.012S'    || '1 minute, 2 seconds' 
                
                'PT1S'         || '1 second' 
                'PT1.2S'       || '1 second, 200 millis' 
                'PT2.1234S'    || '2 seconds, 123 millis' 
                'PT82.1234S'   || '1 minute, 22 seconds' 
                
                'PT1M12S'      || '1 minute, 12 seconds' 
                'PT126M2S'     || '2 hours, 6 minutes' 

                'PT1H4M'       || '1 hour, 4 minutes' 
                'PT1H4M2S'     || '1 hour, 4 minutes' 
                'PT2H2S'       || '2 hours, 2 seconds' 
                'PT26H2S'      || '1 day, 2 hours' 
                
                'P1DT3H'       || '1 day, 3 hours' 
                'P2DT3H4M'     || '2 days, 3 hours' 
                'P2DT4M'       || '2 days, 4 minutes' 
    }
    /*@formatter:on*/
} 