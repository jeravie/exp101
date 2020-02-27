package exp101

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ExpUserServiceSpec extends Specification {

    ExpUserService expUserService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ExpUser(...).save(flush: true, failOnError: true)
        //new ExpUser(...).save(flush: true, failOnError: true)
        //ExpUser expUser = new ExpUser(...).save(flush: true, failOnError: true)
        //new ExpUser(...).save(flush: true, failOnError: true)
        //new ExpUser(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //expUser.id
    }

    void "test get"() {
        setupData()

        expect:
        expUserService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ExpUser> expUserList = expUserService.list(max: 2, offset: 2)

        then:
        expUserList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        expUserService.count() == 5
    }

    void "test delete"() {
        Long expUserId = setupData()

        expect:
        expUserService.count() == 5

        when:
        expUserService.delete(expUserId)
        sessionFactory.currentSession.flush()

        then:
        expUserService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ExpUser expUser = new ExpUser()
        expUserService.save(expUser)

        then:
        expUser.id != null
    }
}
