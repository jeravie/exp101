package exp101

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ExpUserController {

    ExpUserService expUserService
    TransactionService transactionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET"] 

    def index(Integer max) {
        //params.max = Math.min(max ?: 10, 100)
        //respond expUserService.list(params), model:[expUserCount: expUserService.count()]
        redirect(uri:"/")
    }

    def show(Long id) {
        //respond expUserService.get(id)
        def userInstance = expUserService.get(id)
        
        if (userInstance == null) {
            redirect(uri: "/")
        }
        else {
            //def userTransactions = userInstance.transactions.listOrderByTransactionDate()
            [userInstance: userInstance]
        }
        
    }

    def create() {
        respond new ExpUser(params)
    }

    def save(ExpUser expUser) {
        if (expUser == null) {
            notFound()
            return
        }

        try {
            expUser.addToTransactions(new Transaction(transactionRef: 'Opening balance', amountZAR: params.amountZAR, runningBalance: params.amountZAR))
            expUserService.save(expUser)
        } catch (ValidationException e) {
            respond expUser.errors, view:'create'
            return
        }

        redirect(controller: "expUser", action: "show", id: expUser.id)
        /*
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'expUser.label', default: 'ExpUser'), expUser.id])
                redirect expUser
            }
            '*' { respond expUser, [status: CREATED] }
        }
        */
    }

    def edit(Long id) {
        respond expUserService.get(id)
    }

    def update(ExpUser expUser) {
        if (expUser == null) {
            notFound()
            return
        }

        try {
            expUser.addToTransactions(new Transaction(amountZAR: 500, runningBalance: 500))
            expUserService.save(expUser)
        } catch (ValidationException e) {
            respond expUser.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'expUser.label', default: 'ExpUser'), expUser.id])
                redirect expUser
            }
            '*'{ respond expUser, [status: OK] }
        }
    }

    def delete(Long id) {
        if (params.confirm == null) {
            redirect(uri: "/")
        } else {
            if (id == null) {
            notFound()
            return
            }
            if (params.confirm == '1') {
                expUserService.delete(id)
            }
            redirect(uri: "/")
        }
        
        /*
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'expUser.label', default: 'ExpUser'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
        */
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'expUser.label', default: 'ExpUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
