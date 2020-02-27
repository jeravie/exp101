package exp101

class ExpUser {
    String userName
    Date createDate = new Date()
    //Date updateDate = new Date()
    String comments
    
    static hasMany = [transactions: Transaction]

    static constraints = {
        userName size: 2..64, blank: false, unique: true
        comments nullable: true
    }

}
