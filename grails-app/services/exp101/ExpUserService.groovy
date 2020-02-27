package exp101

import grails.gorm.services.Service

@Service(ExpUser)
interface ExpUserService {

    ExpUser get(Serializable id)

    List<ExpUser> list(Map args)

    Long count()

    void delete(Serializable id)

    ExpUser save(ExpUser expUser)

}