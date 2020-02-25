package exp101

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TransactionController {

    def index() { }

    def create() {
        respond new Transaction(params)
    }

    def save() {
        render params
        def user = ExpUser.get(params.id)
        //render "***["+user.id+"]***"
        //user.userName = 'Santie'
        //user.addToTransactions(new Transaction(transactionType: -1, transactionDate: new Date(), amountZAR: 1, amountUSD: 1, runningBalance: 1, transactionRef: 'niks2'))
        //user.addToTransactions(new Transaction(amountZAR: params.amountZAR))
        //user.save()
        //render user.errors
        if (user == null) {
            redirect(uri: "/") 
            return
        }

        try {
            //def tr = new Transaction(amountZAR: params.amountZAR)
            //user.addToTransactions(new Transaction(amountZAR: 0))
            //user.addToTransactions(new Transaction(runningBalance: params.amountZAR))
            //def user2 = user //new User(userName: 'Gerry')
            user.updateDate = new Date()
            
            //user.userName = 'Pietertjie'
            //user.addToTransactions(new Transaction(transactionType: -1, transactionDate: new Date(), amountZAR: 1, amountUSD: 1, runningBalance: 1, transactionRef: 'niks2'))
            //user.addToTransactions(new Transaction(amountZAR: params.amountZAR))
            user.save()
            render user.updateDate + " | " +user.createDate
            render user.errors
            render '<BR>'
            render user.id
            render '<BR>'
            //render user.transactions
            //render user.errors
            //render user.transactions
            //redirect(controller: "user", action: "show", id: user.id)

        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }
    }
}
