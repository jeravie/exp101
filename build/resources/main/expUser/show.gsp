<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="row justify-content-center">
                <div class="col-md-12">
                    <div class="card">
                            
                        <div class="card-header">
                            <i class="fa fa-money"></i> Welcome ${userInstance.userName}, your balance is <span style="color:#f90">ZAR [balance]</span>
                        </div>
                        <div class="card-body">

                            <h4 align="left">Your transactions</h4>
                            <p>&nbsp;</p>

                            <table>
                                <tr style="background: #cccccc;">
                                    <td>Date</td>
                                    <td>Type</td>
                                    <td>Reference</td>
                                    <td style="text-align:right">ZAR Amount</td>
                                    <td style="text-align:right">USD Amount</td>
                                    <td style="text-align:right">ZAR Running Balance</td>
                                </tr>    
                                <g:each var="t" in="${userInstance.transactions}">
                                    <tr>
                                        <td>${t.transactionDate}</td>
                                        <td>${t.transactionType == -1 ? "Expense" : ""}</td>
                                        <td>${t.transactionRef}</td>
                                        <td style="text-align:right">ZAR ${t.amountZAR}</td>
                                        <td style="text-align:right">USD ${t.amountUSD}</td>
                                        <td style="text-align:right">ZAR ${t.runningBalance}</td>
                                    </tr>
                                </g:each>
                            </table>
                            
                            <p>&nbsp;</p>
                            <button class="btn btn-primary" onclick="javascript:document.location='/transaction/create/${userInstance.id}'"><i class="fa fa-angle-double-down"></i> Add Expense</button>
                            <button class="btn btn-default"><i class="fa fa-file-excel-o"></i> Export CSV</button>
                        </div>
                    </div>
                </div>
            </div>

    </body>
</html>
