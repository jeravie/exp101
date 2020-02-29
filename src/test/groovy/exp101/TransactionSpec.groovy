package exp101

import grails.testing.gorm.DomainUnitTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

class TransactionSpec extends Specification implements DomainUnitTest<Transaction> {

    void "test Transaction belonging to a user"() {
        setup:
        def us = new ExpUser(userName: 'Robert').save()
        def tx = new Transaction(amountZAR: 0, amountUSD: 0, runningBalance: 0)
        us.addToTransactions(tx)
        us.save()

        expect:
            us.transactions.size() > 0
    }
}
