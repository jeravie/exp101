<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>

        <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                            
                        <div class="card-header"><i class="fa fa-angle-double-down"></i> Add Expense</div>
                        <div class="card-body">
                            <form action="/transaction/save/${params.id}" method="POST">
                                <div class="form-group">
                                    <input type="text" required placeholder="Expense reference" name="transactionRef" class="form-control">
                                </div>
                                <div class="form-group">
                                    <input type="text" required placeholder="ZAR expense amount" name="amountZAR" class="form-control">
                                </div>
                                <div class="form-group">
                                    <button type="submit" name="create" class="btn btn-primary">Save</button>
                                </div>
                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        
    </body>
</html>
