package exp101

class Transaction {

    Date transactionDate = new Date()
    Float amountZAR = 0
    Float amountUSD = 0
    BigDecimal exchangeRateFactorUSD = 0   //USD exchange rate value for 1 ZAR at point in time when transaction is created
    Float runningBalance = 0
    String transactionRef = ''

    static belongsTo = [expUser: ExpUser]

    static constraints = {
       transactionRef size:0..16, nullable: true
       amountZAR scale: 2
       amountUSD scale: 2
       runningBalance scale: 2
       exchangeRateFactorUSD precision: 18, scale: 8
    }
}
