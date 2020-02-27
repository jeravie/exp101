package exp101

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.gorm.services.Service

class IndexController {

    ExpUserService expUserService

    def index() { 
        def users = expUserService.list()
        //render max_user.size()
        //if (max_user.size() > 0) {
        //    render max_user.id
        //    render(view:"/grails-app/views/indexAlt.gsp", params: [max_id: max_user.id])
        //} else {
            render view:"/grails-app/views/index.gsp", model: [users: users]
        //}
        //
    }
}
