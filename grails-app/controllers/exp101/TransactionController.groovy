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
        render params
        def expUser = expUserService.get(params.id)
        if (expUser == null) {
            notFound()
            return
        }

        try {

            def transactionValue = 0
            def usd = funcService.getUSDCurrency(params.amountZAR)

            if (params.abs == '1') {
                transactionValue = Math.abs(Float.parseFloat(params.amountZAR))
            } else {
                transactionValue = Math.abs(Float.parseFloat(params.amountZAR)) * -1
                usd = usd * -1
            }
            def balance = funcService.getRunningBalance(expUser)
            def newBalance = balance + transactionValue
            
            expUser.addToTransactions(new Transaction(amountZAR: transactionValue, amountUSD: usd, transactionRef: params.transactionRef, runningBalance: newBalance))
            expUserService.save(expUser)
        } catch (ValidationException e) {
            respond transaction.errors, view:'create'
            return
        }

        redirect(controller: "expUser", action: "show", id: expUser.id)
        
    }

    def currency() {
        //def usd = funcService.getUSDCurrency(params.id)
        //render "ZAR"+params.id+" => USD" + usd
    }

    def exportCSV(Long id) {
        if (id == null) {
            redirect (uri:"/")
            return
        }

        def transactions = ExpUser.get(id).transactions as List<Transaction>
        
        if (transactions.size() > 0) {
            def filename = 'Export_'+user.id + '_timestamp.csv'

            def filecontent = ""
            transactions.each { Transaction t ->
                filecontent << "${t.transactionDate},${t.transactionRef},${t.amountZAR},${t.amountUSD},${t.runningBalance}\n"
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
        //render params
        def transaction = transactionService.get(params.id)
        if (transaction == null) {
            notFound()
            return
        }

        try {

            def transactionValue = 0
            def balance = 0
            def usd = funcService.getUSDCurrency(params.amountZAR)

            if (params.abs == '1') {
                transactionValue = Math.abs(Float.parseFloat(params.amountZAR))
            } else {
                transactionValue = Math.abs(Float.parseFloat(params.amountZAR)) * -1
                usd = usd * -1
            }

            if (params.id != '1') {
                balance = funcService.getRunningBalanceUpUntilBeforeTransaction(transaction, transaction.expUser.transactions as List)
                //render balance
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
