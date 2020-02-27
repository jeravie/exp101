package exp101

import grails.gorm.transactions.Transactional

@Transactional
class FuncService {

    def getRunningBalance(ExpUser expUser) {
        return expUser.transactions.amountZAR.sum()
    }

    def getCurrency(String currency, String amountZAR) {
        return 999
    }
}
