package exp101

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TransactionController {

    TransactionService transactionService
    ExpUserService expUserService
    FuncService funcService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond transactionService.list(params), model:[transactionCount: transactionService.count()]
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
            if (params.abs == '1') {
                transactionValue = Math.abs(Float.parseFloat(params.amountZAR))
            } else {
                transactionValue = Math.abs(Float.parseFloat(params.amountZAR)) * -1
            }
            def balance = funcService.getRunningBalance(expUser)
            def newBalance = balance + transactionValue
            def usd = funcService.getCurrency('USD', params.amountZAR)
            
            expUser.addToTransactions(new Transaction(amountZAR: transactionValue, amountUSD: usd, transactionRef: params.transactionRef, runningBalance: newBalance))
            expUserService.save(expUser)
        } catch (ValidationException e) {
            respond transaction.errors, view:'create'
            return
        }

        redirect(controller: "expUser", action: "show", id: expUser.id)
        
    }

    def edit(Long id) {
        respond transactionService.get(id)
    }

    def update(Transaction transaction) {
        if (transaction == null) {
            notFound()
            return
        }

        try {
            transactionService.save(transaction)
        } catch (ValidationException e) {
            respond transaction.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'transaction.label', default: 'Transaction'), transaction.id])
                redirect transaction
            }
            '*'{ respond transaction, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        transactionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'transaction.label', default: 'Transaction'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
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
