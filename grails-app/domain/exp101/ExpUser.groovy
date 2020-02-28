package exp101

class ExpUser {
    String userName
    Date createDate = new Date()
    
    static hasMany = [transactions: Transaction]

    static constraints = {
        userName size: 2..64, blank: false, unique: true
    }

}
