package exp101

import grails.testing.services.ServiceUnitTest
import spock.lang.*

class FuncServiceSpec extends Specification implements ServiceUnitTest<FuncService> {

    /*
    void "test getExchangeRateFactorUSD"() {
        expect: 
            service.getExchangeRateFactorUSD() >= 0

    }
    
    void "test getRunningBalance[ExpUser expUser]"() {
        def expUser = new ExpUser(userName: 'Gerry')
        def tx = new Transaction(amountZAR: 500)
        expUser.addToTransactions(tx)
        expUser.save()

        expect: 
            service.getRunningBalance(expUser) == 500

    }

    void "test getRunningBalanceUpUntilBeforeTransaction"() {
        def expUser = new ExpUser(userName: 'Gerry')
        def tx = new Transaction(amountZAR: 250)
        def tx2 = new Transaction(amountZAR: 500)
        expUser.addToTransactions(tx)
        expUser.addToTransactions(tx2)
        expUser.save()

        expUserTransactions = expUser.transactions

        expect: 
            service.getRunningBalanceUpUntilBeforeTransaction(tx2, expUserTransactions) == 250
    }

    void "test recalculateFollowingTransactionRunningBalances"() {
        def expUser = new ExpUser(userName: 'Gerry')
        def tx = new Transaction(amountZAR: 250)
        def tx2 = new Transaction(amountZAR: 500)
        expUser.addToTransactions(tx)
        expUser.addToTransactions(tx2)
        expUser.save()

        expUserTransactions = expUser.transactions

        service.recalculateFollowingTransactionRunningBalances(tx, expUserTransactions)
        expect: 
            tx2.runningBalance == 500
    }
    */
    
    
}
