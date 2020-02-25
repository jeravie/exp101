package exp101

class Transaction {

    Integer transactionType = 0
    Date transactionDate = new Date()
    Float amountZAR = 0
    Float amountUSD = 0
    Float runningBalance = 0
    String transactionRef = ''

    static belongsTo = [expUser: ExpUser]

    static constraints = {
       transactionRef size:0..16, nullable: true
    }
}
