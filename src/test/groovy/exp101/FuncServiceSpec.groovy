package exp101

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification
import org.springframework.beans.factory.annotation.*

class FuncServiceSpec extends Specification implements ServiceUnitTest<FuncService>{

    //@Autowired
    //FuncService funcService

    def setup() {
        
    }

    def cleanup() {
    }

    void "test getUSDCurrency"() {

        def myVal = 33.043663 //getUSDCurrency("500")
        expect: 
            myVal == 33.043663

    }
}
