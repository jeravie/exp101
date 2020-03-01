package exp101

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ExpUserController {

    ExpUserService expUserService
    TransactionService transactionService
    FuncService funcService

    static allowedMethods = [save: "POST", update: "PUT", delete: "POST"] 

    def index(Integer max) {
        redirect(uri:"/")
    }

    def show(Long id) {
        def userInstance = expUserService.get(id)
        
        if (userInstance == null) {
            redirect(uri: "/")
        }
        else {
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
            def factor = funcService.getExchangeRateFactorUSD()
            def amountUSD = new Float(new BigDecimal(factor) * new BigDecimal(params.amountZAR))

            expUser.addToTransactions(new Transaction(transactionRef: 'Opening balance', amountZAR: params.amountZAR, amountUSD: amountUSD, exchangeRateFactorUSD: factor, runningBalance: params.amountZAR))
            expUserService.save(expUser)
        } catch (ValidationException e) {
            respond expUser.errors, view:'create'
            return
        }

        redirect(controller: "expUser", action: "show", id: expUser.id)

    }

    def edit(Long id) {
        respond expUserService.get(id)
    }

    def delete(Long id) {
        
        if (request.method == 'GET') {
            redirect(uri: "/")
        } else {
            if (id == null) {
            notFound()
            return
            }
            expUserService.delete(id)
            redirect(uri: "/")
        }

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
