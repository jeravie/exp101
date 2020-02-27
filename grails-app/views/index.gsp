<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Expense Sheet System</title>
</head>
<body>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">  
                <div class="card-header"><i class="fa fa-money"></i> Expense Sheets</div>
                <div class="card-body">
                    <p align=left>
                        <button onclick="document.location='/expUser/create'"  class="btn btn-primary">Create New</button>
                    </p>
                    <div style='display:${users.size > 0 ? "block":"none"}'>&nbsp;</div>
                    <div style='display:${users.size > 0 ? "block":"none"}'>
                        <h4 align=left style="font-weight:bold">Existing Sheets:</h4>
                    </div>
                    <p>&nbsp;</p>
                    <p align=left>
                        <g:each var="u" in="${users}">
                            <button onclick="document.location='/expUser/show/${u.id}'" class="btn btn-default">${u.userName}</button>
                        </g:each>
                    </p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
