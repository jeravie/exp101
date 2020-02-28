package exp101

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.gorm.services.Service

class IndexController {

    ExpUserService expUserService

    def index() { 
        def users = expUserService.list()
        render view:"/grails-app/views/index.gsp", model: [users: users]

    }
}
