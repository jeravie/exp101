<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'expUser.label', default: 'ExpUser')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                            
                        <div class="card-header"><i class="fa fa-money"></i> Expenses</div>
                        <div class="card-body">
                            <g:form resource="${this.expUser}" method="POST">
                                <div class="form-group">
                                    <input type="text" required placeholder="Your name?" name="userName" class="form-control">
                                </div>
                                <div class="form-group">
                                    <input type="text" required placeholder="Opening ZAR balance" name="runningBalance" class="form-control">
                                </div>
                                <div class="form-group">
                                    <g:submitButton name="create" class="btn btn-primary" value="Save" /></div>
                                </div>
                                
                            </g:form>
                        </div>
                    </div>
                </div>
            </div>

       <!--

            <a href="#create-expUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-expUser" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.expUser}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.expUser}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.expUser}" method="POST">
                <fieldset class="form">
                    <f:all bean="expUser"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
       --> 
    </body>
</html>
