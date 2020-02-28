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

    def getUSDCurrency(String amountZAR) {
        
        //def jsonStr = '{"rates":{"USD":0.0660873264},"base":"ZAR","date":"2020-02-26"}' //
        def endPoint = 'https://api.exchangeratesapi.io/latest?'
        def qs = "base=ZAR&symbols=USD"
        def jsonStr = "$endPoint$qs".toURL().text
        
        //render jsonStr
        //JsonOutput.prettyPrint(jsonStr)
        def json = new JsonSlurper().parseText(jsonStr)
        def (rootKey, currencyValue) = json.findResult { k, v -> [k, v.USD] }

        //println rootKey
        //println currencyValue

        def myVal = new BigDecimal(currencyValue) * new BigDecimal(amountZAR)
        return new Float(myVal)
        //return currencyValue
    }
}
