package exp101

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BootStrap {

    ExpUserService expUserService
    TransactionService transactionService
    FuncService funcService

    def init = { servletContext ->

        def amountUSD = 0
        def expUser = new ExpUser(userName: 'James')
        
        amountUSD = funcService.getUSDCurrency('50000')
        expUser.addToTransactions(new Transaction(transactionRef: 'Opening balance', amountZAR: 50000, amountUSD: amountUSD, runningBalance: 50000))
        
        amountUSD = -1 * funcService.getUSDCurrency('6500')
        expUser.addToTransactions(new Transaction(transactionRef: 'Rent', amountZAR: -6500, amountUSD: amountUSD, runningBalance: (50000-6500)))
        
        amountUSD = -1 * funcService.getUSDCurrency('50')
        expUser.addToTransactions(new Transaction(transactionRef: 'Coffee', amountZAR: -50, amountUSD: amountUSD, runningBalance: (50000-6500-50)))
        
        amountUSD = funcService.getUSDCurrency('1500')
        expUser.addToTransactions(new Transaction(transactionRef: 'Sold TV', amountZAR: 1500, amountUSD: amountUSD, runningBalance: (50000-6500-50+1500)))
        
        amountUSD = -1 * funcService.getUSDCurrency('1350')
        expUser.addToTransactions(new Transaction(transactionRef: 'Fuel', amountZAR: -1350, amountUSD: amountUSD, runningBalance: (50000-6500-50+1500-1350)))

        expUserService.save(expUser)

    }
    def destroy = {
        def expUser = expUserService.get(1)
        if (expUser != null) {
            expUserService.delete(expUser)
        }
    }
}
