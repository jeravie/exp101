package exp101

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ExpUserController {

    def index() { }

    def create() {
        respond new ExpUser()
    }

    def save() {
        render params
        if (params == null) {
            redirect(uri: "/") 
            return
        }

        try {
            def user = new ExpUser(userName: params.userName)
            //user.addToTransactions(new Transaction(runningBalance: params.runningBalance))
            user.save()
            redirect(controller: "expUser", action: "show", id: user.id)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }
        
    }

    def show(Long id) {
        def userInstance = ExpUser.get(id)
        if (userInstance == null) {
            redirect(uri: "/") 
        } else {
            [userInstance: userInstance]
        }
        
    }

    def test() {
        def user = new ExpUser(userName: 'Sue')
        user.comments = 'nothing'
        user.save()
        render user.comments + "<BR>"
        user.comments = 'zip'
        user.save()
        render user.comments + "<BR>"

        def user2 = new ExpUser(userName: 'Peter')
        user2.comments = 'nada'
        user2.save()
        render user2.comments + "<BR>"
        user2.comments = 'rien'
        user2.save()
        render user2.comments + "<BR>"
    }

}
