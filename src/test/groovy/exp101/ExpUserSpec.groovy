package exp101

import grails.testing.gorm.DomainUnitTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

class ExpUserSpec extends Specification implements DomainUnitTest<ExpUser> {

    void "test ExpUser"() {
        setup:
        def x = new ExpUser(userName: 'Robert').save()

        expect:
            x.id > 0
    }
}
