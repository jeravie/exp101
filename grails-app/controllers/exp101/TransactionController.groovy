package exp101

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TransactionController {

    TransactionService transactionService
    ExpUserService expUserService
    FuncService funcService

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    def index(Integer max) {
        //params.max = Math.min(max ?: 10, 100)
        //respond transactionService.list(params), model:[transactionCount: transactionService.count()]
        redirect (uri: "/")
    }

    def show(Long id) {
        respond transactionService.get(id)
    }

    def create() {
        respond new Transaction(params)
    }

    def save() {
        //render params
        def expUser = expUserService.get(params.id)
        if (expUser == null) {
            notFound()
            return
        }

        try {

            def transactionValue = 0
            def factor = funcService.getExchangeRateFactorUSD()
            def usd = new Float(new BigDecimal(factor) * new BigDecimal(params.amountZAR))

            if (params.abs == '1') { //deposit
                transactionValue = Math.abs(Float.parseFloat(params.amountZAR))
            } else { //expense
                transactionValue = Math.abs(Float.parseFloat(params.amountZAR)) * -1
                usd = usd * -1
            }
            def balance = funcService.getRunningBalance(expUser)
            def newBalance = balance + transactionValue
            
            expUser.addToTransactions(new Transaction(amountZAR: transactionValue, amountUSD: usd, exchangeRateFactorUSD: factor, transactionRef: params.transactionRef, runningBalance: newBalance))
            expUserService.save(expUser)
        } catch (ValidationException e) {
            respond transaction.errors, view:'create'
            return
        }

        redirect(controller: "expUser", action: "show", id: expUser.id)
        
    }

    def exportCSV(Long id) {
        if (id == null) {
            redirect (uri:"/")
            return
        }

        def transactions = expUserService.get(id).transactions as List<Transaction>
        transactions.sort{a,b->a.id.compareTo(b.id)}

        if (transactions.size() > 0) {
            def filename = 'Export-userid_'+id + '-timestamp.csv'

            def filecontent = "TransactionID,Transaction Date,Reference,Amount ZAR,Transient Amount USD,Transient USD Exchange Rate, Running Balance ZAR\r\n"
            
            transactions.each { Transaction t ->
                filecontent = filecontent + "${t.id},${t.transactionDate},${t.transactionRef},${t.amountZAR},${t.amountUSD},${t.runningBalance}\r\n"
            }
    
            response.setHeader "Content-disposition", "attachment; filename=${filename}"
            response.contentType = 'text/csv'
            response.outputStream << filecontent
            response.outputStream.flush()
        }
        
    }

    def edit(Long id) {
        def transaction = transactionService.get(id)
        if (transaction == null) {
            redirect (uri: "/")
            return
        }
        [transaction: transaction]
    }

    def update() {
        def transaction = transactionService.get(params.id)
        if (transaction == null) {
            notFound()
            return
        }

        try {

            def transactionValue = 0
            def balance = 0
            def usd = 0  
            def factor = transaction.exchangeRateFactorUSD  //exchange rate as it was at transaction creation

            if (params.abs == '1') { //deposit
                transactionValue = Math.abs(Float.parseFloat(params.amountZAR))
                usd = new Float(factor * transactionValue)
            } else { //expense
                transactionValue = Math.abs(Float.parseFloat(params.amountZAR)) * -1
                usd = new Float(factor * transactionValue)
            }
            usd.round(2)

            if (params.id != '1') {
                balance = funcService.getRunningBalanceUpUntilBeforeTransaction(transaction, transaction.expUser.transactions as List)
            }
            
            def newBalance = balance + transactionValue
            
            transaction.amountZAR = transactionValue
            transaction.amountUSD = usd
            transaction.transactionRef = params.transactionRef
            transaction.runningBalance = newBalance 
            transactionService.save(transaction)

            ////////// recalculate transaction running balances following this one
            funcService.recalculateFollowingTransactionRunningBalances(transaction, transaction.expUser.transactions as List)

            redirect(controller: "expUser", action: "show", id: transaction.expUser.id)
        } catch (ValidationException e) {
            respond transaction.errors, view:'create'
            return
        }

        
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        transactionService.delete(id)

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
