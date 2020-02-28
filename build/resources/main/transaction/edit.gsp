<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                            
                        <div class="card-header">
                            <i class="fa fa-edit"></i> Edit Transaction
                        </div>
                        <div class="card-body">
                            <form action="/transaction/update/${transaction.id}" method="POST">
                                <div class="form-group">
                                    <input type="text" required placeholder="Your reference?" name="transactionRef" value="${transaction.transactionRef}" class="form-control">
                                </div>
                                <div class="form-group">
                                    <input type="number" required placeholder="ZAR amount?" name="amountZAR" min=0 value="${Math.abs(transaction.amountZAR)}" class="form-control" step="0.01">
                                </div>
                                <div class="form-group" style="text-align:left">
                                    <input type="radio" name="abs" value="1" ${(transaction.amountZAR > 0) ? "checked" : ""}> Deposit<BR>
                                    <input type="radio" name="abs" value="0" ${(transaction.amountZAR < 0) ? "checked" : ""}> Expense
                                </div>
                                <div class="form-group">
                                    <button type="button" class="btn btn-default" onclick="document.location='/expUser/show/${transaction.expUser.id}'">Back</button>
                                    <button type="submit" name="create" class="btn btn-primary">Save</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
