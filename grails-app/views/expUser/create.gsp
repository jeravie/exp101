<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'expUser.label', default: 'ExpUser')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>

        <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                            
                        <div class="card-header"><i class="fa fa-user"></i> Create user</div>
                        <div class="card-body">

                                    <g:form resource="${this.expUser}" method="POST">
                                                        <div class="form-group">
                                                            <input type="text" required placeholder="Type your name" name="userName" class="form-control">
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="number" required placeholder="Opening ZAR balance" name="amountZAR" class="form-control" step=".01">
                                                        </div>
                                                        <div class="form-group">
                                                            <g:submitButton name="create" class="btn btn-primary" value="Save" /></div>
                                                        </div>
                                                        
                                    </g:form>

                        </div>
                    </div>
                </div>
        </div>
    </body>
</html>
