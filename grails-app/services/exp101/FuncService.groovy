package exp101

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import java.math.BigDecimal

@Transactional
class FuncService {

    def getRunningBalance(ExpUser expUser) {
        /*
            Sum all amountZAR to get runningBalance for new transaction.
            This function is used for creating a new transaction.
        */
        return expUser.transactions.amountZAR.sum()
    }

    def getRunningBalanceUpUntilBeforeTransaction(Transaction thisTransaction, List<Transaction> expUserTransactions) {
        /*
            Sum all amountZAR to get runningBalance up until previous transaction, 
            in order to use in calculation of this transaction's new running balance.
            This function is used for UPDATING transaction.
        */

        def runningBalance = 0
        def lastIndex = (expUserTransactions.size()-1)
        def transactionSubList = []

        expUserTransactions.sort{a,b->a.id.compareTo(b.id)}

        transactionSubList = expUserTransactions.findAll{thisTransaction.id > it.id} 
        
        transactionSubList.each { t ->
            runningBalance = runningBalance + t.amountZAR
            //println runningBalance
        }

        return runningBalance
    }

    def recalculateFollowingTransactionRunningBalances(Transaction thisTransaction, List<Transaction> expUserTransactions) {
        /*
            Recalculation of running balances from after this.
            This function is used after UPDATING the directly preceding transaction.
        */
        
        def previousRunningBalance = thisTransaction.runningBalance
        def transactionSubList = []

        expUserTransactions.sort{a,b->a.id.compareTo(b.id)}
        transactionSubList = expUserTransactions.findAll{thisTransaction.id < it.id}

        transactionSubList.each { t ->
            t.runningBalance = previousRunningBalance + t.amountZAR
            previousRunningBalance = t.runningBalance
            t.save()
        }
        
    }

    def getExchangeRateFactorUSD() {
        
        def endPoint = 'https://api.exchangeratesapi.io/latest?base=ZAR&symbols=USD'
        def jsonStr = "$endPoint".toURL().text
        
        def json = new JsonSlurper().parseText(jsonStr)
        def (rootKey, currencyValue) = json.findResult { k, v -> [k, v.USD] }

        if (rootKey == "rates") {
            return new BigDecimal(currencyValue)
        } else {
            return 0
        }
        
    }
}
