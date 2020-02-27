<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                            
                        <div class="card-header" style="background-color:${params.abs == "0" ? "#F60" : ""}"">
                            <i class="fa fa-angle-double-${params.abs == "1" ? "up" : "down"}"></i> Add ${params.abs == "1" ? "Funds" : "Expense"}
                        </div>
                        <div class="card-body">
                            <form action="/transaction/save/${params.id}?abs=${params.abs}" method="POST">
                                <div class="form-group">
                                    <input type="text" required placeholder="Your reference?" name="transactionRef" class="form-control">
                                </div>
                                <div class="form-group">
                                    <input type="number" required placeholder="ZAR amount?" name="amountZAR" class="form-control" step="0.01">
                                </div>
                                <div class="form-group">
                                    <button type="button" class="btn btn-default" onclick="document.location='/expUser/show/${params.id}'">Back</button>
                                    <button type="submit" name="create" class="btn btn-primary">Save</button>
                                </div>
                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
