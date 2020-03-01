package exp101

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import java.math.BigDecimal

@Transactional
class FuncService {

    def getRunningBalance(ExpUser expUser) {
        return expUser.transactions.amountZAR.sum()
    }

    def getRunningBalanceUpUntilBeforeTransaction(Transaction thisTransaction, List<Transaction> expUserTransactions) {
        //assuming transaction ids are chronological and correspond to transactions occurrence
        
        def newBalance = 0
        
        expUserTransactions.sort{a,b->a.id.compareTo(b.id)}

        expUserTransactions.each { t ->
            if (t.id < thisTransaction.id) {
                newBalance = newBalance + t.amountZAR
                //println t.id + " | " + t.amountZAR + "<BR>"
            }
        }

        return newBalance
    }

    def recalculateFollowingTransactionRunningBalances(Transaction thisTransaction, List<Transaction> expUserTransactions) {
        //assuming transaction ids are chronological and correspond to transactions occurrence
        
        expUserTransactions.sort{a,b->a.id.compareTo(b.id)}

        def previousRunningBalance = thisTransaction.runningBalance

        expUserTransactions.each { t ->
            if (t.id > thisTransaction.id) {
                t.runningBalance = previousRunningBalance + t.amountZAR
                previousRunningBalance = t.runningBalance
                t.save()
            }
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
