package exp101

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TransactionServiceSpec extends Specification {

    TransactionService transactionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        new Transaction(amountZAR: 100, amountUSD: 7, runningBalance: 100, transactionRef: 'test').save(failOnError: true)
        new Transaction(amountZAR: 100, amountUSD: 7, runningBalance: 100, transactionRef: 'test').save(failOnError: true)
        Transaction transaction = new Transaction(amountZAR: 100, amountUSD: 7, runningBalance: 100, transactionRef: 'test').save(failOnError: true)
        new Transaction(amountZAR: 100, amountUSD: 7, runningBalance: 100, transactionRef: 'test').save(failOnError: true)
        new Transaction(amountZAR: 100, amountUSD: 7, runningBalance: 100, transactionRef: 'test').save(failOnError: true)
        //assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        transaction.id
    }

    void "test get"() {
        setupData()

        expect:
        transactionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Transaction> transactionList = transactionService.list(max: 2, offset: 2)

        then:
        transactionList.size() == 2
        //assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        transactionService.count() == 5
    }

    void "test delete"() {
        Long transactionId = setupData()

        expect:
        transactionService.count() == 5

        when:
        transactionService.delete(transactionId)
        sessionFactory.currentSession.flush()

        then:
        transactionService.count() == 4
    }

    void "test save"() {
        when:
        //assert false, "TODO: Provide a valid instance to save"
        Transaction transaction = new Transaction()
        transactionService.save(transaction)

        then:
        transaction.id != null
    }
}
